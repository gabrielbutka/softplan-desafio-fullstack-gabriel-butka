package br.com.gabriel.butka.backend.rest.input;

import br.com.gabriel.butka.backend.enums.TipoUsuario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UsuarioInput {

    private Long usuarioId;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;

}
