package br.com.gabriel.butka.backend.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public boolean nonNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

}
