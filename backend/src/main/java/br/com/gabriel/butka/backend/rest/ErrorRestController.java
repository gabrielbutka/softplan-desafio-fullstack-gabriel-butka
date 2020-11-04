package br.com.gabriel.butka.backend.rest;

import br.com.gabriel.butka.backend.exception.BackendException;
import br.com.gabriel.butka.backend.rest.res.ErrorResponse;
import br.com.gabriel.butka.backend.utils.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorRestController {

    @ExceptionHandler({BackendException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest request, Exception exception) {
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST, request.getServletPath(), exception.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDanied(HttpServletRequest request, Exception exception) {
        var message = Translator.get("access.danied.exception");
        var response = new ErrorResponse(HttpStatus.UNAUTHORIZED, request.getServletPath(), message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<ErrorResponse> handleInternalAuthentication(HttpServletRequest request, Exception exception) {
        var message = Translator.get("internal.auth.exception");
        var response = new ErrorResponse(HttpStatus.UNAUTHORIZED, request.getServletPath(), message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleBadCredentials(HttpServletRequest request, Exception exception) {
        var message = Translator.get("bad.credentials.exception");
        var response = new ErrorResponse(HttpStatus.UNAUTHORIZED, request.getServletPath(), message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalError(HttpServletRequest request, Exception exception) {
        var message = Translator.get("internal.error.exception");
        var response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
