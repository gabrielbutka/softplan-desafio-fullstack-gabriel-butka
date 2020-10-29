package br.com.gabriel.butka.backend.service;

import br.com.gabriel.butka.backend.entity.Usuario;
import br.com.gabriel.butka.backend.repository.UsuarioRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {

    @Getter
    private final UsuarioRepository repository;

}
