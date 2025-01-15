package com.api.Sistema_Recados_Escolar.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
    @Nullable
    Funcionario findByLoginAndSenha(String login, String senha);
    
    List<Funcionario> findByNomeContaining(String nome);
}
