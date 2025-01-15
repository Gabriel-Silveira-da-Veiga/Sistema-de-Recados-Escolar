package com.api.Sistema_Recados_Escolar.service;

import com.api.Sistema_Recados_Escolar.exception.ResourceNotFoundException;
import com.api.Sistema_Recados_Escolar.model.Comentario;
import com.api.Sistema_Recados_Escolar.model.ComentarioRepository;
import com.api.Sistema_Recados_Escolar.model.Recado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentRepository;
    
    public Comentario criarComentario(Comentario coment) {
        coment.setId(null);
        comentRepository.save(coment);
        return coment;
    }
    
    public Comentario getComentarioId(Integer id) {
        return comentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado " + id));
    }
    
    public List<Comentario> listarComentarios(Recado recado) {
        return comentRepository.findByRecado(recado);
    }
    
    public Comentario atualizarComentario(Integer id, Comentario comentRequest) {
        Comentario coment = getComentarioId(id);
        coment.setConteudo(comentRequest.getConteudo());
        
        comentRepository.save(coment);
        return coment;
    }
    
    public void deletarComentario(Integer id) {
        Comentario coment = getComentarioId(id);
        comentRepository.deleteById(coment.getId());
    }
}
