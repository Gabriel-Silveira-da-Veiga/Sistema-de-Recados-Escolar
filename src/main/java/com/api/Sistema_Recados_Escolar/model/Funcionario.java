package com.api.Sistema_Recados_Escolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="Funcionario")
public class Funcionario extends Usuario{
    @NotBlank(message = "Matéria obrigatória!")
    String materiaLecionada;
    
    @NotBlank(message = "Turma obrigatória!")
    String turmas;
}
