package com.api.Sistema_Recados_Escolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="Aluno")
public class Aluno extends Usuario{
    @ManyToOne
    @JoinColumn(name = "turma_id")
    @NotBlank(message = "Turma obrigatória!")
    Turma turma;
}
