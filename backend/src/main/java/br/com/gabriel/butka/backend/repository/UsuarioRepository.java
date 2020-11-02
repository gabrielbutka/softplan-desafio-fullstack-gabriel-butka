package br.com.gabriel.butka.backend.repository;

import br.com.gabriel.butka.backend.entity.Usuario;
import br.com.gabriel.butka.backend.enums.TipoUsuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByAtivoIsTrueAndEmail(String email);

    Optional<Usuario> findById(Long id);

    List<Usuario> findAllByTipo(TipoUsuario tipo);

}
