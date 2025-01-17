package com.api.Sistema_Recados_Escolar.model;

import com.api.Sistema_Recados_Escolar.service.AlunoService;
import com.api.Sistema_Recados_Escolar.service.FuncionarioService;
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
    
    Integer recadoId;
    
    Integer usuarioId;
    
    @NotBlank(message = "Usuário obrigatória!")
    @NotNull(message = "Usuário obrigatória!")
    String usuarioNome;
    
    @NotBlank(message = "Usuário obrigatória!")
    @NotNull(message = "Usuário obrigatória!")
    String usuarioCargo;
    
    String conteudo;
}
