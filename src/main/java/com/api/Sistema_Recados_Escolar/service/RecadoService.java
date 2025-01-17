package com.api.Sistema_Recados_Escolar.service;

import com.api.Sistema_Recados_Escolar.exception.ResourceNotFoundException;
import com.api.Sistema_Recados_Escolar.model.Recado;
import com.api.Sistema_Recados_Escolar.model.RecadoRepository;
import com.api.Sistema_Recados_Escolar.model.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecadoService {
    @Autowired
    RecadoRepository recadoRepository;
    
    public Recado criarRecado(Recado recado) {
        recado.setId(null);
        recadoRepository.save(recado);
        return recado;
    }
    
    public Recado getRecadoId(Integer id) {
        return recadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recado não encontrado " + id));
    }
    
    public List<Recado> listarRecados(Turma turma) {
        return recadoRepository.findByTurmaId(turma.getId());
    }
    
    public List<Recado> listarRecadosMateria(Turma turma, String materia) {
        return recadoRepository.findByTurmaIdAndMateriaContaining(turma.getId(), materia);
    }
    
    public Recado atualizarRecado(Integer id, Recado recadoRequest) {
        Recado  recado  = getRecadoId(id);
        recado.setTitulo(recadoRequest.getTitulo());
        recado.setDescricao(recadoRequest.getDescricao());
        recado.setData(recadoRequest.getData());
        
        recadoRepository.save(recado);
        return recado;
    }
    
    public void deletarRecado(Integer id) {
        Recado recado = getRecadoId(id);
        recadoRepository.deleteById(recado.getId());
    }
    
    
}
