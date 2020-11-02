package br.com.gabriel.butka.backend.service;

import br.com.gabriel.butka.backend.entity.Parecer;
import br.com.gabriel.butka.backend.entity.Processo;
import br.com.gabriel.butka.backend.entity.Usuario;
import br.com.gabriel.butka.backend.enums.StatusProcesso;
import br.com.gabriel.butka.backend.exception.BackendException;
import br.com.gabriel.butka.backend.repository.ProcessoRepository;
import br.com.gabriel.butka.backend.rest.input.ParecerInput;
import br.com.gabriel.butka.backend.rest.input.ProcessoInput;
import br.com.gabriel.butka.backend.utils.Translator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessoService extends BaseService<Processo, ProcessoRepository> {

    @Getter
    private final ProcessoRepository repository;
    private final UsuarioService usuarioService;

    public Processo findByIdOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            var message = Translator.get("processo.inexistente");
            return new BackendException(message);
        });
    }

    public List<Processo> findAllByUsuarioLogado() {
        var usuarioLogado = usuarioService.getUsuarioLogado();
        if (usuarioLogado.getTipo().isTriador()) {
            return repository.findAllByUsuario(usuarioLogado);
        }
        return findAllPendentesByUsuario(usuarioLogado);
    }

    public List<Processo> findAllPendentesByUsuario(Usuario usuario) {
        return repository.findAllByStatus(StatusProcesso.PENDENTE).stream()
                .filter(p -> p.temInteressado(usuario) && !p.temParecer(usuario))
                .collect(Collectors.toList());
    }

    public Processo adicionarByInput(ProcessoInput input) {
        return atualizarByInput(input);
    }

    public Processo atualizarByInput(ProcessoInput input) {
        var processo = findById(input.getProcessoId()).orElse(new Processo());
        var interessados = usuarioService.findAllById(input.getInteressados());
        var usuario = usuarioService.getUsuarioLogado();

        processo.setAssunto(input.getAssunto());
        processo.setDescricao(input.getDescricao());
        processo.setUsuario(usuario);
        processo.setInteressados(interessados);

        return save(processo);
    }

    public Parecer adicionarParecerByInput(ParecerInput input) {
        var processo = findByIdOrThrowException(input.getProcessoId());
        var usuarioLogado = usuarioService.getUsuarioLogado();

        var parecer = new Parecer();
        parecer.setProcesso(processo);
        parecer.setDescricao(input.getDescricao());
        parecer.setUsuario(usuarioLogado);

        processo.addParecer(parecer);
        save(processo);

        return parecer;
    }

}
