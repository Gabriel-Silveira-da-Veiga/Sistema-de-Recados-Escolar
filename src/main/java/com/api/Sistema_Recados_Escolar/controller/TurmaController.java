package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Turma;
import com.api.Sistema_Recados_Escolar.service.TurmaService;
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
@RequestMapping("/truma")
public class TurmaController {
    @Autowired
    TurmaService turmaService;
    
    @PostMapping("/criar")
    public ResponseEntity<Turma> criarTurma(@Valid @RequestBody Turma turma) {
        var novaTurma = turmaService.criarTurma(turma);
        return new ResponseEntity<>(novaTurma, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listarTurmas() {
        List<Turma> turmas = turmaService.listarTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
    
    @GetMapping("/listar/{ìd}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable Integer id) {
        Turma turma = turmaService.getTurmaId(id);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Turma> atualizarTurma(@Valid @PathVariable Integer id, @RequestBody Turma turma) {
        var turmaAtt = turmaService.atualizarTurma(id, turma);
        return new ResponseEntity<>(turmaAtt, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarTurma(@PathVariable Integer id) {
        turmaService.deletarTurma(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
