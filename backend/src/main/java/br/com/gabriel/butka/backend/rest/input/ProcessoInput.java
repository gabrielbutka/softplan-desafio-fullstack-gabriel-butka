package br.com.gabriel.butka.backend.rest.input;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProcessoInput {

    private Long processoId;
    private String assunto;
    private String descricao;
    private List<Long> interessados;

}
