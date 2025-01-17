package com.api.Sistema_Recados_Escolar.service;

import com.api.Sistema_Recados_Escolar.exception.ResourceNotFoundException;
import com.api.Sistema_Recados_Escolar.model.Funcionario;
import com.api.Sistema_Recados_Escolar.model.FuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcRepository;
    
    public Funcionario criarFuncionario(Funcionario func){
        func.setId(null);
        funcRepository.save(func);
        return func;
    }
    
    public Funcionario getFuncionarioId(Integer id) {
        return funcRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado " + id));
    }
    
    public List<Funcionario> listarFuncionarios() {
        return funcRepository.findAll();
    }
    
    public Funcionario getFuncionarioByLogin(String login, String senha) {
        return funcRepository.findByLoginAndSenha(login, senha);
    }
    
    public List<Funcionario> listarFuncionarioNome(String nome) {
        return funcRepository.findByNomeContaining(nome);
    }
    
    public Funcionario atualizarFuncionario(Integer id, Funcionario funcRequest) {
        Funcionario func = getFuncionarioId(id);
        func.setNome(funcRequest.getNome());
        func.setIdade(funcRequest.getIdade());
        func.setTurmas(funcRequest.getTurmas());
        func.setCargo(funcRequest.getCargo());
        func.setMateriaLecionada(funcRequest.getMateriaLecionada());
        func.setLogin(funcRequest.getLogin());
        func.setSenha(funcRequest.getSenha());
        
        funcRepository.save(func);
        return func;
    }
    
    public void deletarFuncionario(Integer id) {
        Funcionario func = getFuncionarioId(id);
        funcRepository.deleteById(func.getId());
    }
}
