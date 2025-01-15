package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Comentario;
import com.api.Sistema_Recados_Escolar.model.Recado;
import com.api.Sistema_Recados_Escolar.service.ComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    ComentarioService comentService;
    
    @PostMapping("/criar")
    public ResponseEntity<Comentario> criarComentario(@RequestBody Comentario coment) {
        var novoComent = comentService.criarComentario(coment);
        return new ResponseEntity<>(novoComent, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar/{recado}")
    public ResponseEntity<List> listarComentarios(@PathVariable Recado recado) {
        List<Comentario> coments = comentService.listarComentarios(recado);
        return new ResponseEntity<>(coments, HttpStatus.OK);
    }
    
    @GetMapping("/listar/{ìd}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Comentario coment = comentService.getComentarioId(id);
        return new ResponseEntity<>(coment, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable Integer id, @RequestBody Comentario coment) {
        var comentAtt = comentService.atualizarComentario(id, coment);
        return new ResponseEntity<>(comentAtt, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarComentario(@PathVariable Integer id) {
        comentService.deletarComentario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
