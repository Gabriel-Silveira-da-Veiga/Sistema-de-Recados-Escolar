package com.api.Sistema_Recados_Escolar.service;

import com.api.Sistema_Recados_Escolar.exception.ResourceNotFoundException;
import com.api.Sistema_Recados_Escolar.model.Aluno;
import com.api.Sistema_Recados_Escolar.model.AlunoRepository;
import com.api.Sistema_Recados_Escolar.model.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;
    
    public Aluno criarAluno(Aluno aluno) {
        aluno.setId(null);
        alunoRepository.save(aluno);
        return aluno;
    }
    
    public Aluno getAlunoId(Integer id) {
        return alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado " + id));
    }
    
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }
    
    public Aluno getAlunoByLogin(String login, String senha) {
        return alunoRepository.findByLoginAndSenha(login, senha);
    }
    
    public List<Aluno> listarAlunoNome(String nome) {
        return alunoRepository.findByNomeContaining(nome);
    }
    
    public Aluno atualizarAluno(Integer id, Aluno alunoRequest) {
        Aluno aluno = getAlunoId(id);
        aluno.setNome(alunoRequest.getNome());
        aluno.setIdade(alunoRequest.getIdade());
        aluno.setTurmaId(alunoRequest.getTurmaId());
        aluno.setLogin(alunoRequest.getLogin());
        aluno.setSenha(alunoRequest.getSenha());
        
        alunoRepository.save(aluno);
        return aluno;
    }
    
    public void deletarAluno(Integer id) {
        Aluno aluno = getAlunoId(id);
        alunoRepository.deleteById(aluno.getId());
    }
}
