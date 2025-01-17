package com.api.Sistema_Recados_Escolar.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@MappedSuperclass
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    @NotBlank(message = "Nome obrigatório!")
    String nome;
    
    @NotNull(message = "Idade obrigatória!")
    int idade;
    
    @NotBlank(message = "Login obrigatório!")
    String login;
    
    @NotBlank(message = "Senha obrigatória!")
    String senha;
    
    @NotBlank(message = "Cargo obrigatório!")
    String cargo;
}
