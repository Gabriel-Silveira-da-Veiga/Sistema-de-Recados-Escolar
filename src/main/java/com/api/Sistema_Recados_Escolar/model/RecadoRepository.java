package com.api.Sistema_Recados_Escolar.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecadoRepository extends JpaRepository<Recado, Integer>{
    List<Recado> findByTurma(Turma turma);
    
    List<Recado> findByMateriaContaining(String materia);
}
