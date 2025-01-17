package com.api.Sistema_Recados_Escolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="Turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    @NotBlank(message = "Número da turma obrigatório!")
    String numero;
}
