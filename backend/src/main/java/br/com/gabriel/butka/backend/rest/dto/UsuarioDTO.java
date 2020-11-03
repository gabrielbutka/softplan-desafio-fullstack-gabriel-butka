package br.com.gabriel.butka.backend.rest.dto;

import br.com.gabriel.butka.backend.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private EnumDTO tipo;
    private boolean editavel;
    private boolean ativo;

    public static UsuarioDTO build(Usuario usuario) {
        var dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTipo(EnumDTO.build(usuario.getTipo()));
        dto.setAtivo(usuario.isAtivo());
        return dto;
    }

    public static UsuarioDTO build(Optional<Usuario> usuario) {
        return usuario.isPresent() ? build(usuario.get()) : null;
    }

    public static List<UsuarioDTO> build(List<Usuario> usuarios) {
        return usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .map(UsuarioDTO::build)
                .collect(Collectors.toList());
    }

}
