package br.com.gabriel.butka.backend.rest.dto;

import br.com.gabriel.butka.backend.entity.Processo;
import br.com.gabriel.butka.backend.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class InteressadoDTO {

    private UsuarioDTO usuario;
    private boolean parecerPendente;

    public static InteressadoDTO build(Usuario usuario, Processo processo) {
        var dto = new InteressadoDTO();
        dto.setUsuario(UsuarioDTO.build(usuario));
        dto.setParecerPendente(!processo.temParecer(usuario));
        return dto;
    }

    public static List<InteressadoDTO> build(Processo processo) {
        return processo.getInteressados().stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .map(interessado -> InteressadoDTO.build(interessado, processo))
                .collect(Collectors.toList());
    }

}
