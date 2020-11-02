package br.com.gabriel.butka.backend.enums;

import br.com.gabriel.butka.backend.utils.Translator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TipoUsuario implements BaseEnum {

    ADMINISTRADOR("tipo.usuario.administrador"),
    TRIADOR("tipo.usuario.triador"),
    FINALIZADOR("tipo.usuario.finalizador");

    private final String bundleKey;

    @Override
    public String getChave() {
        return name();
    }

    @Override
    public String getDescricao() {
        return Translator.get(bundleKey);
    }

    public boolean isAdministrador() {
        return this == ADMINISTRADOR;
    }

    public boolean isTriador() {
        return this == TRIADOR;
    }

    public boolean isFinalizador() {
        return this == FINALIZADOR;
    }

}
