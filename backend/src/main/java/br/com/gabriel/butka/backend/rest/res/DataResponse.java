package br.com.gabriel.butka.backend.rest.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@JsonRootName("response")
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DataResponse<T> {

	private HttpStatus status;
	private String message;
	private T data;

}
