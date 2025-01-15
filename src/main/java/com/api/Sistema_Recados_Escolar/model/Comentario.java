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
@Table(name="Comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    @ManyToOne
    @JoinColumn(name = "recado_id")
    @NotBlank(message = "Recado obrigatória!")
    @NotNull(message = "Recado obrigatória!")
    Recado recado;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
            @NotBlank(message = "Usuário obrigatória!")
    @NotNull(message = "Usuário obrigatória!")
    Usuario usuario;
    
    String conteudo;
}
