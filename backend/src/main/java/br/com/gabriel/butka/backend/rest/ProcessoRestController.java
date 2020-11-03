package br.com.gabriel.butka.backend.rest;

import br.com.gabriel.butka.backend.entity.Processo;
import br.com.gabriel.butka.backend.rest.dto.ParecerDTO;
import br.com.gabriel.butka.backend.rest.dto.ProcessoDTO;
import br.com.gabriel.butka.backend.rest.input.ParecerInput;
import br.com.gabriel.butka.backend.rest.input.ProcessoInput;
import br.com.gabriel.butka.backend.rest.res.DataResponse;
import br.com.gabriel.butka.backend.service.ProcessoService;
import br.com.gabriel.butka.backend.service.UsuarioService;
import br.com.gabriel.butka.backend.utils.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/processo")
@RequiredArgsConstructor
public class ProcessoRestController extends BaseRestController {

    private final ProcessoService processoService;
    private final UsuarioService usuarioService;

    @Secured({"ROLE_TRIADOR", "ROLE_FINALIZADOR"})
    @GetMapping(value = "/{id}")
    public ResponseEntity<DataResponse<ProcessoDTO>> getProcesso(@PathVariable("id") Long id) {
        var processo = processoService.findByIdOrThrowException(id);
        return ok(ProcessoDTO.build(processo));
    }

    @Secured("ROLE_TRIADOR")
    @GetMapping(value = "/pareceres/{id}")
    public ResponseEntity<DataResponse<List<ParecerDTO>>> getPareceres(@PathVariable("id") Long id) {
        var processo = processoService.findByIdOrThrowException(id);
        return ok(ParecerDTO.build(processo.getPareceres()));
    }

    @Secured({"ROLE_TRIADOR", "ROLE_FINALIZADOR"})
    @GetMapping(value = "/todos")
    public ResponseEntity<DataResponse<List<ProcessoDTO>>> getTodos() {
        return ok(processoService.findAllByUsuario(usuarioService.getUsuarioLogado()).stream()
                .sorted(Comparator.comparing(Processo::getCriacao).reversed())
                .map(ProcessoDTO::build)
                .collect(Collectors.toList()));
    }

    @Secured("ROLE_TRIADOR")
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<DataResponse<Boolean>> deletar(@PathVariable("id") Long id) {
        processoService.deleteById(id);
        var message = Translator.get("processo.sucesso.exclusao");
        return ok(Boolean.TRUE, message);
    }

    @Secured("ROLE_TRIADOR")
    @PostMapping(value = "/atualizar", consumes = "application/json")
    public ResponseEntity<DataResponse<ProcessoDTO>> atualizar(@RequestBody ProcessoInput input) {
        var processo = processoService.atualizarByInput(input);
        var message = Translator.get("processo.sucesso.atualizacao");
        return ok(ProcessoDTO.build(processo), message);
    }

    @Secured("ROLE_TRIADOR")
    @PostMapping(value = "/adicionar", consumes = "application/json")
    public ResponseEntity<DataResponse<ProcessoDTO>> adicionar(@RequestBody ProcessoInput input) {
        var processo = processoService.adicionarByInput(input);
        var message = Translator.get("processo.sucesso.adicao");
        return ok(ProcessoDTO.build(processo), message);
    }

    @Secured("ROLE_FINALIZADOR")
    @PostMapping(value = "/adicionar/parecer", consumes = "application/json")
    public ResponseEntity<DataResponse<ParecerDTO>> adicionar(@RequestBody ParecerInput input) {
        var parecer = processoService.adicionarParecerByInput(input);
        var message = Translator.get("parecer.sucesso.adicao");
        return ok(ParecerDTO.build(parecer), message);
    }

}
