package com.api.Sistema_Recados_Escolar.service;

import com.api.Sistema_Recados_Escolar.exception.ResourceNotFoundException;
import com.api.Sistema_Recados_Escolar.model.Turma;
import com.api.Sistema_Recados_Escolar.model.TurmaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository turmaRepository;
    
    public Turma criarTurma(Turma turma){
        turma.setId(null);
        turmaRepository.save(turma);
        return turma;
    }
    
    public Turma getTurmaId(Integer id) {
        return turmaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada " + id));
    }
    
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
    
    public Turma atualizarTurma(Integer id, Turma turmaRequest) {
        Turma turma = getTurmaId(id);
        turma.setNumero(turmaRequest.getNumero());
        
        turmaRepository.save(turma);
        return turma;
    }
    
    public void deletarTurma(Integer id) {
        Turma turma = getTurmaId(id);
        turmaRepository.deleteById(turma.getId());
    }
}
