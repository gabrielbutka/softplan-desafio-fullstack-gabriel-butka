package br.com.gabriel.butka.backend.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Translator implements MessageSourceAware {

    private static MessageSource messageSource;

    public static String get(String bundleKey, Object... args) {
        if (Objects.nonNull(messageSource)) {
            return messageSource.getMessage(bundleKey, args, Constants.LOCALE_DEFAULT);
        }
        return bundleKey;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        Translator.messageSource = messageSource;
    }
}
