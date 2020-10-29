package br.com.gabriel.butka.backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoUsuario implements BaseEnum {

    ADMINISTRADOR("Administrador"),
    TRIADOR("Triador"),
    FINALIZADOR("Finalizador");

    @Getter
    private final String descricao;

    @Override
    public String getChave() {
        return name();
    }

}
