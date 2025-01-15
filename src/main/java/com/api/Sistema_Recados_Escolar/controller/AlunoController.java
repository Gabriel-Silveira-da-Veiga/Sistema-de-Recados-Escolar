package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Aluno;
import com.api.Sistema_Recados_Escolar.service.AlunoService;
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
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    AlunoService alunoService;
    
    @PostMapping("/criar")
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        var novoAluno = alunoService.criarAluno(aluno);
        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listarAluno() {
        List<Aluno> alunos = alunoService.listarAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
        Aluno aluno = alunoService.getAlunoId(id);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Integer id, @RequestBody Aluno aluno) {
        var alunoAtt = alunoService.atualizarAluno(id, aluno);
        return new ResponseEntity<>(alunoAtt, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarAluno(@PathVariable Integer id) {
        alunoService.deletarAluno(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
