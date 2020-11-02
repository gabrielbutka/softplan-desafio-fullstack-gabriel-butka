package br.com.gabriel.butka.backend.repository;

import br.com.gabriel.butka.backend.entity.Processo;
import br.com.gabriel.butka.backend.entity.Usuario;
import br.com.gabriel.butka.backend.enums.StatusProcesso;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoRepository extends BaseRepository<Processo, Long> {

    List<Processo> findAllByUsuario(Usuario usuario);

    List<Processo> findAllByStatus(StatusProcesso status);

}
