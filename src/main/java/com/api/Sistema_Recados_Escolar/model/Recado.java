package com.api.Sistema_Recados_Escolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="Recado")
public class Recado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    Integer funcionarioId;
    
    Integer turmaId;
    
    @NotBlank(message = "Título obrigatório!")
    String titulo;
    
    @NotBlank(message = "Descrição obrigatória!")
    String descricao;
    
    @NotBlank(message = "Matéria obrigatória!")
    String materia;
    
    String data;
}
