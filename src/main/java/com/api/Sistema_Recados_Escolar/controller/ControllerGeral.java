package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Aluno;
import com.api.Sistema_Recados_Escolar.model.Comentario;
import com.api.Sistema_Recados_Escolar.model.Funcionario;
import com.api.Sistema_Recados_Escolar.model.Recado;
import com.api.Sistema_Recados_Escolar.model.Turma;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerGeral {

    @GetMapping("/")
    public String telaLogin(){
        return "telaLogin";
    }
    
    @GetMapping("/menuFuncionario")
    public String telaFuncionario(){
        return "telaFuncionario";
    }
    
    
    //ControllerAluno
    private List<Aluno> listaAlunos = new ArrayList();
    
    public List<Aluno> getListaA(){
        return listaAlunos;
    }
    @GetMapping("/matricularAluno")
    public String matricularAluno(Model model){
        model.addAttribute("aluno", new Aluno());
        return "telaMatricularAluno";
    }  
    @PostMapping("/matricularAluno")
    public String processarFormulario(@ModelAttribute Aluno aluno, Model model) {
        aluno.setId(listaAlunos.size() + 1);
        listaAlunos.add(aluno);
        model.addAttribute("lista", listaAlunos);
        return "redirect:/exibirUsuarios";
    }
    @GetMapping("/editarAluno")
    public String editarAluno(){
        return "telaEditarAluno";
    }

    
    //ControllerFuncionario
    private List<Funcionario> listaFuncionarios = new ArrayList();
    
    public List<Funcionario> getListaFuncionarios(){
        return listaFuncionarios;
    }
    
    @GetMapping("/cadastrarFuncionario")
    public String cadastrarFuncionario(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "telaCadastrarFuncionario";
    }  
    @PostMapping("/cadastrarFuncionario")
    public String processarFormulario(@ModelAttribute Funcionario func, Model model) {
        func.setId(listaFuncionarios.size() + 1);
        listaFuncionarios.add(func);
        model.addAttribute("listaF", listaFuncionarios);
        return "redirec:/exibirUsuarios";
    }
    @GetMapping("/editarFuncionario")
    public String editarFuncionario(){
        return "telaEditarFuncionario";
    }
    
    
    //ControllerUsuario
    @GetMapping("exibirUsuarios")
    public String exibirUsuarios(Model model){
        model.addAttribute("listaA", listaAlunos);
        model.addAttribute("listaF", listaFuncionarios);
        return "telaVisuUsuarios";
    }
    
    
    //ControllerTurma
    private List<Turma> listaTurmas = new ArrayList();
    
    public List<Turma> getListaTurmas(){
        return listaTurmas;
    }
    
    @GetMapping("exibirTurmas")
    public String exibirTurmas(Model model){
        model.addAttribute("listaT", listaTurmas);
        model.addAttribute("turma", new Turma());
        return "telaVisuTurmas";
    }
    
    @PostMapping("/criarTurma")
    public String processarformulario(@ModelAttribute Turma turma, Model model) {
        turma.setId(listaTurmas.size() + 1);
        listaTurmas.add(turma);
        model.addAttribute("listaT", listaTurmas);
        return "telaVisuTurmas";
    }
    
    
    //ControllerRecado
    private List<Recado> listaRecados = new ArrayList();
    
    public List<Recado> getListaRecados(){
        return listaRecados;
    }
    
    @GetMapping("exibirRecados")
    public String exibirRecados(Model model){
        model.addAttribute("listaR", listaRecados);
        return "telaTurma";
    }
    
    @GetMapping("/criarRecado")
    public String cirarRecado(Model model){
        model.addAttribute("recado", new Recado());
        return "telaCriarRecado";
    }
    
    @PostMapping("/criarRecado")
    public String processarformulario(@ModelAttribute Recado recado, Model model) {
        recado.setId(listaRecados.size() + 1);
        listaRecados.add(recado);
        model.addAttribute("lista", listaRecados);
        return "telaTurma";
    }
    @GetMapping("/editarRecado")
    public String editarRecado(){
        return "telaEditarRecado";
    }
    
    
    //ControllerComentario
    private List<Comentario> listaComentario = new ArrayList();
    
    public List<Comentario> getListaComentario(){
        return listaComentario;
    }
    
    @GetMapping("exibirComentarios")
    public String exibirComentarios(Model model){
        model.addAttribute("listaC", listaComentario);
        return "telaVisuComentarios";
    }
    
    @GetMapping("/comentar")
    public String comentar(Model model){
        model.addAttribute("comentario", new Comentario());
        return "telaComentar";
    }
    
    @PostMapping("/comentar")
    public String processarformulario(@ModelAttribute Comentario coment, Model model) {
        coment.setId(listaComentario.size() + 1);
        listaComentario.add(coment);
        model.addAttribute("listaC", listaComentario);
        return "telaVisuComentarios";
    }
    
    @GetMapping("/editarComentario")
    public String editarComentario(){
        return "telaEditarComentario";
    }
}
