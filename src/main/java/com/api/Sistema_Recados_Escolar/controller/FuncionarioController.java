package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Funcionario;
import com.api.Sistema_Recados_Escolar.service.FuncionarioService;
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
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    FuncionarioService funcService;
    
    @PostMapping("/criar")
    public ResponseEntity<Funcionario> criarFuncionario(@Valid @RequestBody Funcionario func) {
        var novoFunc = funcService.criarFuncionario(func);
        return new ResponseEntity<>(novoFunc, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listarFuncionarios() {
        List<Funcionario> funcs = funcService.listarFuncionarios();
        return new ResponseEntity<>(funcs, HttpStatus.OK);
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<Funcionario> getFuncById(@PathVariable Integer id) {
        Funcionario func = funcService.getFuncionarioId(id);
        return new ResponseEntity<>(func, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@Valid @PathVariable Integer id, @RequestBody Funcionario func) {
        var funcAtt = funcService.atualizarFuncionario(id, func);
        return new ResponseEntity<>(funcAtt, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFuncionario(@PathVariable Integer id) {
        funcService.deletarFuncionario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
