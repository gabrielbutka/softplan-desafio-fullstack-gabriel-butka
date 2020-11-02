package br.com.gabriel.butka.backend.enums;

import br.com.gabriel.butka.backend.utils.Translator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusProcesso implements BaseEnum {

    PENDENTE("status.processo.pendente"),
    FINALIZADO("status.processo.finalizado");

    private final String bundleKey;

    @Override
    public String getChave() {
        return name();
    }

    @Override
    public String getDescricao() {
        return Translator.get(bundleKey);
    }

    public boolean isFinalizado() {
        return this == FINALIZADO;
    }

    public boolean isPendente() {
        return this == PENDENTE;
    }

}
