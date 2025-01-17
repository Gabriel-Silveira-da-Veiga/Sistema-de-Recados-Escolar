package com.api.Sistema_Recados_Escolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Aluno")
public class Aluno extends Usuario{
    Integer turmaId;
}
