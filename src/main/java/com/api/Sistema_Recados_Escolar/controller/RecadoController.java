package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Recado;
import com.api.Sistema_Recados_Escolar.model.Turma;
import com.api.Sistema_Recados_Escolar.service.RecadoService;
import jakarta.validation.Valid;
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
@RequestMapping("/recado")
public class RecadoController {
    @Autowired
    RecadoService recadoService;
    
    @PostMapping("/criar")
    public ResponseEntity<Recado> criarRecado(@Valid @RequestBody Recado recado) {
        var novoRecado = recadoService.criarRecado(recado);
        return new ResponseEntity<>(novoRecado, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar/{turma}")
    public ResponseEntity<List> listarRecados(@PathVariable Turma turma) {
        List<Recado> recados = recadoService.listarRecados(turma);
        return new ResponseEntity<>(recados, HttpStatus.OK);
    }
    
    @GetMapping("/listar/{ìd}")
    public ResponseEntity<Recado> getRecadoById(@PathVariable Integer id) {
        Recado recado = recadoService.getRecadoId(id);
        return new ResponseEntity<>(recado, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Recado> atualizarRecado(@Valid @PathVariable Integer id, @RequestBody Recado recado) {
        var recadoAtt = recadoService.atualizarRecado(id, recado);
        return new ResponseEntity<>(recadoAtt, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarRecado(@PathVariable Integer id) {
        recadoService.deletarRecado(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
