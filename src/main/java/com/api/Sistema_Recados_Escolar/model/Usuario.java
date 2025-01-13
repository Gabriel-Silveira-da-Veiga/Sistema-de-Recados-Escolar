package com.api.Sistema_Recados_Escolar.model;

import lombok.Data;

@Data
public class Usuario {
    Integer id;
    String nome;
    int idade;
    String login;
    String senha;
    String cargo;
}
