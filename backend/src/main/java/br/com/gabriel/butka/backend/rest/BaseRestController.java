package br.com.gabriel.butka.backend.rest;

import br.com.gabriel.butka.backend.rest.res.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseRestController {

    public <T> ResponseEntity<DataResponse<T>> ok(T data, String message) {
        var response = new DataResponse<T>(HttpStatus.OK, message, data);
        return ResponseEntity.ok(response);
    }

    public <T> ResponseEntity<DataResponse<T>> ok(T data) {
        var response = new DataResponse<T>(HttpStatus.OK, null, data);
        return ResponseEntity.ok(response);
    }

    public <T> ResponseEntity<DataResponse<T>> response(HttpStatus status, String message) {
        var response = new DataResponse<T>(status, message, null);
        return ResponseEntity.status(status).body(response);
    }

    public <T> ResponseEntity<DataResponse<T>> response(HttpStatus status) {
        var response = new DataResponse<T>(status, null, null);
        return ResponseEntity.status(status).body(response);
    }

}
