package br.com.labcycle.api.comentario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, String> {
    List<Comentario> findByPraticaIdOrderByDataCriacaoDesc(String praticaId);

}