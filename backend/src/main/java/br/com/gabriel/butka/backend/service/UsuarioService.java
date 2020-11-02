package br.com.gabriel.butka.backend.service;

import br.com.gabriel.butka.backend.entity.Usuario;
import br.com.gabriel.butka.backend.enums.TipoUsuario;
import br.com.gabriel.butka.backend.exception.BackendException;
import br.com.gabriel.butka.backend.repository.UsuarioRepository;
import br.com.gabriel.butka.backend.rest.input.UsuarioInput;
import br.com.gabriel.butka.backend.utils.Translator;
import br.com.gabriel.butka.backend.utils.Utils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> implements UserDetailsService {

    @Getter
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Usuario getUsuarioLogado() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var email = ((UserDetails) auth.getPrincipal()).getUsername();
        return findByEmail(email).orElse(null);
    }

    public List<Usuario> findAllByTipo(TipoUsuario tipo) {
        return repository.findAllByTipo(tipo);
    }

    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<Usuario> findByAtivoIsTrueAndEmail(String email) {
        return repository.findByAtivoIsTrueAndEmail(email);
    }

    public Usuario findByAtivosIsTrueAndEmailOrThrowException(String email) {
        return findByAtivoIsTrueAndEmail(email).orElseThrow(() -> {
            var message = Translator.get("usuario.inexistente");
            return new BackendException(message);
        });
    }

    public Usuario findByIdOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            var message = Translator.get("usuario.inexistente");
            return new BackendException(message);
        });
    }

    public Usuario adicionarByInput(UsuarioInput input) {
        return atualizarByInput(input);
    }

    public Usuario atualizarByInput(UsuarioInput input) {
        var usuario = findById(input.getUsuarioId()).orElse(new Usuario());
        var existente = findByEmail(input.getEmail());

        if (existente.isPresent() && !existente.get().equals(usuario)) {
            var mensagem = Translator.get("usuario.email.existente");
            throw new BackendException(mensagem);
        }

        usuario.setNome(input.getNome());
        usuario.setEmail(input.getEmail());
        usuario.setTipo(input.getTipo());

        if (Utils.nonNullOrEmpty(input.getSenha())) {
            var encodedSenha = passwordEncoder.encode(input.getSenha());
            usuario.setSenha(encodedSenha);
        }

        return save(usuario);
    }

    public Usuario ativarById(Long id) {
        var usuario = findByIdOrThrowException(id);
        return save(usuario.ativar());
    }

    public Usuario inativarById(Long id) {
        var usuario = findByIdOrThrowException(id);
        return save(usuario.inativar());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByAtivosIsTrueAndEmailOrThrowException(email).toUserDetails();
    }
}
