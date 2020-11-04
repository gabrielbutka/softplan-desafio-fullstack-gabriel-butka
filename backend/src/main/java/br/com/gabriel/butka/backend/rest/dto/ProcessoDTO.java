package br.com.gabriel.butka.backend.rest.dto;

import br.com.gabriel.butka.backend.entity.Processo;
import br.com.gabriel.butka.backend.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProcessoDTO {

    private Long id;
    private String assunto;
    private String descricao;
    private EnumDTO status;
    private String criacao;
    private String nomesInteressados;
    private UsuarioDTO usuario;
    private boolean editavel;
    private List<InteressadoDTO> interessados;

    public static ProcessoDTO build(Processo processo) {
        var dto = new ProcessoDTO();
        dto.setId(processo.getId());
        dto.setAssunto(processo.getAssunto());
        dto.setDescricao(processo.getDescricao());
        dto.setStatus(EnumDTO.build(processo.getStatus()));
        dto.setCriacao(Utils.formatDate(processo.getCriacao()));
        dto.setNomesInteressados(processo.getNomesInteressados());
        dto.setUsuario(UsuarioDTO.build(processo.getUsuario()));
        dto.setInteressados(InteressadoDTO.build(processo));
        dto.setEditavel(processo.getStatus().isPendente());
        return dto;
    }

}
