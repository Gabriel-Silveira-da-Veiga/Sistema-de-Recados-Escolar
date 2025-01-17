package com.api.Sistema_Recados_Escolar.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
    List<Comentario> findByRecadoId(Integer recadoId);
}
