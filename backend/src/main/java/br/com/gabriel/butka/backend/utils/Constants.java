package br.com.gabriel.butka.backend.utils;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class Constants {

    public final Locale LOCALE_DEFAULT =Locale.forLanguageTag("pt-BR");

    public final DateTimeFormatter DATE_TIME_FORMATTER_DEFAULT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}
