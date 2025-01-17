package com.api.Sistema_Recados_Escolar.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    @Nullable
    Aluno findByLoginAndSenha(String login, String senha);
    
    List<Aluno> findByNomeContaining(String nome);
}
