package br.com.gabriel.butka.backend.rest;

import br.com.gabriel.butka.backend.entity.Usuario;
import br.com.gabriel.butka.backend.enums.TipoUsuario;
import br.com.gabriel.butka.backend.rest.dto.UsuarioDTO;
import br.com.gabriel.butka.backend.rest.input.UsuarioInput;
import br.com.gabriel.butka.backend.rest.res.DataResponse;
import br.com.gabriel.butka.backend.service.UsuarioService;
import br.com.gabriel.butka.backend.utils.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioRestController extends BaseRestController {

    private final UsuarioService usuarioService;

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DataResponse<UsuarioDTO>> getUsuario(@PathVariable("id") Long id) {
        var usuario = usuarioService.findByIdOrThrowException(id);
        return ok(UsuarioDTO.build(usuario));
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping(value = "/todos")
    public ResponseEntity<DataResponse<List<UsuarioDTO>>> getTodos() {
        return ok(usuarioService.findAll().stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .map(UsuarioDTO::build)
                .collect(Collectors.toList()));
    }

    @Secured("ROLE_TRIADOR")
    @GetMapping(value = "/finalizadores")
    public ResponseEntity<DataResponse<List<UsuarioDTO>>> getFinalizadores() {
        return ok(usuarioService.findAllByTipo(TipoUsuario.FINALIZADOR).stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .map(UsuarioDTO::build)
                .collect(Collectors.toList()));
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping(value = "/ativar/{id}")
    public ResponseEntity<DataResponse<UsuarioDTO>> ativar(@PathVariable("id") Long id) {
        var usuario = usuarioService.ativarById(id);
        var message = Translator.get("usuario.sucesso.ativacao");
        return ok(UsuarioDTO.build(usuario), message);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping(value = "/inativar/{id}")
    public ResponseEntity<DataResponse<UsuarioDTO>> inativar(@PathVariable("id") Long id) {
        var usuario = usuarioService.inativarById(id);
        var message = Translator.get("usuario.sucesso.inativacao");
        return ok(UsuarioDTO.build(usuario), message);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping(value = "/adicionar")
    public ResponseEntity<DataResponse<UsuarioDTO>> adicionar(@RequestBody UsuarioInput input) {
        var usuario = usuarioService.adicionarByInput(input);
        var message = Translator.get("usuario.sucesso.adicao");
        return ok(UsuarioDTO.build(usuario), message);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping(value = "/atualizar")
    public ResponseEntity<DataResponse<UsuarioDTO>> atualizar(@RequestBody UsuarioInput input) {
        var usuario = usuarioService.atualizarByInput(input);
        var message = Translator.get("usuario.sucesso.atualizacao");
        return ok(UsuarioDTO.build(usuario), message);
    }

}
