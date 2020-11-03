package br.com.gabriel.butka.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class BackendException extends RuntimeException {

    public BackendException(String message) {
        super(message);
    }

    public BackendException(String message, Object... args) {
        super(String.format(message, args));
    }

}
