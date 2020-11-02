package br.com.gabriel.butka.backend.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Objects;

@UtilityClass
public class Utils {

    public boolean isNullOrEmpty(String str) {
        return Objects.isNull(str) || str.trim().isEmpty();
    }

    public boolean nonNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public String formatDate(LocalDateTime ldt) {
        return Objects.isNull(ldt) ? null : Constants.DATE_TIME_FORMATTER_DEFAULT.format(ldt);
    }

}
