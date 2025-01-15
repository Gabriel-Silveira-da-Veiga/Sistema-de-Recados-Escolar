package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Aluno;
import com.api.Sistema_Recados_Escolar.model.Comentario;
import com.api.Sistema_Recados_Escolar.model.Funcionario;
import com.api.Sistema_Recados_Escolar.model.Recado;
import com.api.Sistema_Recados_Escolar.model.Turma;
import com.api.Sistema_Recados_Escolar.service.AlunoService;
import com.api.Sistema_Recados_Escolar.service.ComentarioService;
import com.api.Sistema_Recados_Escolar.service.FuncionarioService;
import com.api.Sistema_Recados_Escolar.service.RecadoService;
import com.api.Sistema_Recados_Escolar.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerGeral {
    //Services
    @Autowired
    AlunoService alunoService;
    
    @Autowired
    FuncionarioService funcService;
    
    @Autowired
    TurmaService turmaService;
    
    @Autowired
    RecadoService recadoService;
    
    @Autowired
    ComentarioService comentService;
    
    
    //Controllers
    @GetMapping("/")
    public String telaLogin(){
        return "telaLogin";
    }
    
    @PostMapping("/fazerLogin")
    public String fazerLogin(String login, String senha) {
        if(alunoService.getAlunoByLogin(login, senha) != null){
            Aluno aluno = alunoService.getAlunoByLogin(login, senha);
            return "redirect:/telaTurma/"+aluno.getId();
        }
        if(funcService.getFuncionarioByLogin(login, senha) != null) {
            Funcionario func = funcService.getFuncionarioByLogin(login, senha);
            return "redirect:/menuFuncionario/"+func.getId();
        }
        return "telaLogin";
    }
    
    
    
    
    //ControllerAluno
    @GetMapping("/matricularAluno")
    public String matricularAluno(Model model){
        model.addAttribute("aluno", new Aluno());
        return "telaMatricularAluno";
    }
    
    @GetMapping("/editarAluno/{id}")
    public String editarAluno(@PathVariable Integer id, Model model){
        Aluno aluno = alunoService.getAlunoId(id);
        model.addAttribute("aluno", aluno);
        return "telaEditarAluno";
    }
    
    @PostMapping("/salvarAluno")
    public String salvarAluno(@ModelAttribute("aluno") Aluno aluno, BindingResult result) {
        if (result.hasErrors()) {
            return "telaMatricularAluno";
        }
        if (aluno.getId() == null) {
            alunoService.criarAluno(aluno);
        }
        else {
            alunoService.atualizarAluno(aluno.getId(), aluno);
        }
        return "redirect:/telaExibirUsuarios";
    }
    
    
    
    
    //ControllerFuncionario
    @GetMapping("/menuFuncionario/{id}")
    public String telaFuncionario(@PathVariable Integer id, Model model){
        Funcionario func = funcService.getFuncionarioId(id);
        model.addAttribute("func", func);
        return "telaFuncionario";
    }
    
    @GetMapping("/cadastrarFuncionario")
    public String cadastrarFuncionario(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "telaCadastrarFuncionario";
    }
    
    @GetMapping("/atualizarFuncionario/id")
    public String atualizarFuncionario(@PathVariable Integer id, Model model){
        Funcionario func = funcService.getFuncionarioId(id);
        model.addAttribute("func",func);
        return "telaEditarFuncionario";
    }
    
    @PostMapping("/salvarFunc")
    public String salvarFunc(@ModelAttribute("func") Funcionario func, BindingResult result) {
        if (result.hasErrors()) {
            return "telaCadastrarFuncionario";
        }
        if (func.getId() == null) {
            funcService.criarFuncionario(func);
        }
        else {
            funcService.atualizarFuncionario(func.getId(), func);
        }
        return "redirect:/telaExibirUsuarios";
    }
    
    
    
    
    //ControllerUsuario
    @GetMapping("exibirUsuarios")
    public String exibirUsuarios(Model model){
        model.addAttribute("listaA", alunoService.listarAlunos());
        model.addAttribute("listaF", funcService.listarFuncionarios());
        return "telaVisuUsuarios";
    }
    
    
    
    
    //ControllerTurma
    @GetMapping("/exibirTurmas")
    public String exibirTurmas(Model model){
        model.addAttribute("listaT", turmaService.listarTurmas());
        model.addAttribute("turma", new Turma());
        return "telaVisuTurmas";
    }
    @GetMapping("/turma/{id}")
    public String turma(@PathVariable Aluno aluno, Model model){
        model.addAttribute("listaR", recadoService.listarRecados(aluno.getTurma()));
        return "telaTurma";
    }
    
    @PostMapping("/salvarTurma")
    public String salvarTurma(@ModelAttribute("turma") Turma turma, BindingResult result) {
        if (result.hasErrors()) {
            
        }
        else {
            turmaService.criarTurma(turma);
        }
        return "telaVisuTurmas";
    }
    
    
    
    
    //ControllerRecado
    @GetMapping("/exibirRecado/{id}")
    public String exibirRecado(@PathVariable Integer id, Model model) {
        model.addAttribute("recado", recadoService.getRecadoId(id));
        return "telaVisuRecado";
    }
    
    @GetMapping("/criarRecado")
    public String cirarRecado(Model model){
        model.addAttribute("recado", new Recado());
        return "telaCriarRecado";
    }
    
    @GetMapping("/editarRecado/{id}")
    public String editarRecado(@PathVariable Integer id, Model model){
        Recado recado = recadoService.getRecadoId(id);
        model.addAttribute("recado", recado);
        return "telaEditarRecado";
    }
    
    @PostMapping("/salvarRecado")
    public String salvarRecado(@ModelAttribute("recado") Recado recado, BindingResult result) {
        if (result.hasErrors()) {
            return "telaCriarRecado";
        }
        if (recado.getId() == null) {
            recadoService.criarRecado(recado);
        }
        else {
            recadoService.atualizarRecado(recado.getId(), recado);
        }
        return "redirect:/telaTurma";
    }
    
    
    
    
    //ControllerComentario
    @GetMapping("/exibirComentarios/{recado}")
    public String exibirComentarios(@PathVariable Recado recado, Model model){
        model.addAttribute("listaC", comentService.listarComentarios(recado));
        return "telaVisuComentarios";
    }
    
    @GetMapping("/comentar")
    public String comentar(Model model){
        model.addAttribute("comentario", new Comentario());
        return "telaComentar";
    }
    
    @GetMapping("/editarComentario/{id}")
    public String editarComentario(@PathVariable Integer id, Model model){
        Comentario coment = comentService.getComentarioId(id);
        model.addAttribute("coment",coment);
        return "telaEditarComentario";
    }
    
    @PostMapping("/salvarComent")
    public String salvarComent(@ModelAttribute("coment") Comentario coment, BindingResult result) {
        if (result.hasErrors()) {
            return "telaCriarComentario";
        }
        if (coment.getId() == null) {
            comentService.criarComentario(coment);
        }
        else {
            comentService.atualizarComentario(coment.getId(), coment);
        }
        return "redirect:/telaVisuComentarios";
    }
}
