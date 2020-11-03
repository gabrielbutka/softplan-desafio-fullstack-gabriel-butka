package br.com.gabriel.butka.backend.rest;

import br.com.gabriel.butka.backend.rest.input.AuthInput;
import br.com.gabriel.butka.backend.rest.res.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController extends BaseRestController {

    @Resource(name="authManager")
    private final AuthenticationManager authManager;

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<DataResponse<UserDetails>> login(@RequestBody AuthInput input) {
        var authRequest = new UsernamePasswordAuthenticationToken(input.getEmail(), input.getSenha());
        var auth = authManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ok((UserDetails) auth.getPrincipal());
    }

}
