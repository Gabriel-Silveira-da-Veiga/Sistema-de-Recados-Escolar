package com.api.Sistema_Recados_Escolar.model;

import lombok.Data;

@Data
public class Comentario {
    Integer id;
    Integer idPost;
    Integer idUsuario;
    String conteudo;
}
