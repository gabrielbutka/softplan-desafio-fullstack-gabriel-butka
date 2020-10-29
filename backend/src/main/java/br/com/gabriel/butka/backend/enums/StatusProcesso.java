package br.com.gabriel.butka.backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusProcesso implements BaseEnum {

    PENDENTE("Pendente"),
    FINALIZADO("Finalizado");

    @Getter
    private final String descricao;

    @Override
    public String getChave() {
        return name();
    }

}
