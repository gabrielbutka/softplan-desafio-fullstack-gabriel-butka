package br.com.gabriel.butka.backend.rest.dto;

import br.com.gabriel.butka.backend.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        dto.setEditavel(true);
        dto.setAtivo(usuario.isAtivo());
        return dto;
    }

}
