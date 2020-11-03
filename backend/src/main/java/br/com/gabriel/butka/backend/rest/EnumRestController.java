package br.com.gabriel.butka.backend.rest;

import br.com.gabriel.butka.backend.enums.BaseEnum;
import br.com.gabriel.butka.backend.enums.StatusProcesso;
import br.com.gabriel.butka.backend.enums.TipoUsuario;
import br.com.gabriel.butka.backend.rest.dto.EnumDTO;
import br.com.gabriel.butka.backend.rest.res.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/enum")
@RequiredArgsConstructor
public class EnumRestController extends BaseRestController {

    @GetMapping(value = "/tiposUsuario")
    public ResponseEntity<DataResponse<List<EnumDTO>>> getTiposUsuario() {
        return ok(Stream.of(TipoUsuario.values())
                .sorted(Comparator.comparing(BaseEnum::getDescricao))
                .map(EnumDTO::build)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/statusProcesso")
    public ResponseEntity<DataResponse<List<EnumDTO>>> getStatusProcesso() {
        return ok(Stream.of(StatusProcesso.values())
                .map(EnumDTO::build)
                .collect(Collectors.toList()));
    }

}
