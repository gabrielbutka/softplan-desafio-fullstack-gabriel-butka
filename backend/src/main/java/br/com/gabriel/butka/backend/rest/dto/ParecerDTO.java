package br.com.gabriel.butka.backend.rest.dto;

import br.com.gabriel.butka.backend.entity.Parecer;
import br.com.gabriel.butka.backend.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ParecerDTO {

    private Long id;
    private String descricao;
    private String criacao;
    private UsuarioDTO usuario;

    public static ParecerDTO build(Parecer parecer) {
        var dto = new ParecerDTO();
        dto.setId(parecer.getId());
        dto.setDescricao(parecer.getDescricao());
        dto.setCriacao(Utils.formatDate(parecer.getCriacao()));
        dto.setUsuario(UsuarioDTO.build(parecer.getUsuario()));
        return dto;
    }

    public static List<ParecerDTO> build(List<Parecer> pareceres) {
        return pareceres.stream()
                .sorted(Comparator.comparing(Parecer::getCriacao).reversed())
                .map(ParecerDTO::build)
                .collect(Collectors.toList());
    }

}
