package com.api.Sistema_Recados_Escolar.controller;

import com.api.Sistema_Recados_Escolar.model.Aluno;
import com.api.Sistema_Recados_Escolar.model.Comentario;
import com.api.Sistema_Recados_Escolar.model.Funcionario;
import com.api.Sistema_Recados_Escolar.model.Login;
import com.api.Sistema_Recados_Escolar.model.PesquisaFiltro;
import com.api.Sistema_Recados_Escolar.model.Recado;
import com.api.Sistema_Recados_Escolar.model.Turma;
import com.api.Sistema_Recados_Escolar.service.AlunoService;
import com.api.Sistema_Recados_Escolar.service.ComentarioService;
import com.api.Sistema_Recados_Escolar.service.FuncionarioService;
import com.api.Sistema_Recados_Escolar.service.RecadoService;
import com.api.Sistema_Recados_Escolar.service.TurmaService;
import jakarta.validation.Valid;
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
    public String telaLogin(Model model) {
        boolean falha = false;
        model.addAttribute("falha",falha);
        model.addAttribute("login", new Login());
        return "telaLogin";
    }

    @PostMapping("/fazerLogin")
    public String fazerLogin(@ModelAttribute("login") Login log, Model model) {
        if (alunoService.getAlunoByLogin(log.getLogin(), log.getSenha()) != null) {
            Aluno aluno = alunoService.getAlunoByLogin(log.getLogin(), log.getSenha());
            return "redirect:/turma/" + aluno.getTurmaId() + "/" + aluno.getId() + "/" + aluno.getCargo();
        }
        if (funcService.getFuncionarioByLogin(log.getLogin(), log.getSenha()) != null) {
            Funcionario func = funcService.getFuncionarioByLogin(log.getLogin(), log.getSenha());
            return "redirect:/menuFuncionario/" + func.getId();
        }
        boolean falha = true;
        model.addAttribute("falha",falha);
        return "telaLogin";
    }

    //ControllerAluno
    @GetMapping("/matricularAluno/{idUser}")
    public String matricularAluno(@PathVariable Integer idUser, Model model) {
        model.addAttribute("idUser", idUser);
        model.addAttribute("listaT", turmaService.listarTurmas());
        Aluno aluno = new Aluno();
        aluno.setCargo("Aluno");
        model.addAttribute("aluno", aluno);
        return "telaMatriculaAluno";
    }

    @GetMapping("/editarAluno/{id}/{idUser}")
    public String editarAluno(@PathVariable Integer id, @PathVariable Integer idUser, Model model) {
        model.addAttribute("idUser", idUser);
        model.addAttribute("listaT", turmaService.listarTurmas());
        model.addAttribute("aluno", alunoService.getAlunoId(id));
        return "telaEditarAluno";
    }

    @PostMapping("/salvarAluno/{idUser}")
    public String salvarAluno(@PathVariable Integer idUser, @Valid @ModelAttribute("aluno") Aluno aluno, BindingResult result) {
        if (result.hasErrors()) {
            return "telaMatriculaAluno";
        }
        if (aluno.getId() == null) {
            alunoService.criarAluno(aluno);
        } else {
            alunoService.atualizarAluno(aluno.getId(), aluno);
        }
        return "redirect:/exibirUsuarios/"+idUser;
    }
    
    @GetMapping("/deletarAluno/{id}/{idUser}")
    public String deletarAluno(@PathVariable Integer id, @PathVariable Integer idUser){
        alunoService.deletarAluno(id);
        return "redirect:/exibirUsuarios/"+idUser;
    }

    //ControllerFuncionario
    @GetMapping("/menuFuncionario/{id}")
    public String telaFuncionario(@PathVariable Integer id, Model model) {
        Funcionario func = funcService.getFuncionarioId(id);
        model.addAttribute("func", func);
        return "telaFuncionario";
    }

    @GetMapping("/cadastrarFuncionario/{idUser}")
    public String cadastrarFuncionario(@PathVariable Integer idUser, Model model) {
        model.addAttribute("idUser", idUser);
        model.addAttribute("func", new Funcionario());
        return "telaCadastrarFuncionario";
    }

    @GetMapping("/atualizarFuncionario/{id}/{idUser}")
    public String atualizarFuncionario(@PathVariable Integer id, @PathVariable Integer idUser, Model model) {
        model.addAttribute("idUser", idUser);
        model.addAttribute("func", funcService.getFuncionarioId(id));
        return "telaEditarFuncionario";
    }

    @PostMapping("/salvarFunc/{idUser}")
    public String salvarFunc(@PathVariable Integer idUser, @Valid @ModelAttribute("func") Funcionario func, BindingResult result) {
        if (result.hasErrors()) {
            return "telaCadastrarFuncionario";
        }
        if (func.getId() == null) {
            funcService.criarFuncionario(func);
        } else {
            funcService.atualizarFuncionario(func.getId(), func);
        }
        return "redirect:/exibirUsuarios/"+idUser;
    }
    
    @GetMapping("/deletarFunc/{id}/{idUser}")
    public String deletarFunc(@PathVariable Integer id, @PathVariable Integer idUser){
        funcService.deletarFuncionario(id);
        return "redirect:/exibirUsuarios/"+idUser;
    }

    //ControllerUsuario
    @GetMapping("/exibirUsuarios/{id}")
    public String exibirUsuarios(@PathVariable Integer id, Model model) {
        model.addAttribute("user", funcService.getFuncionarioId(id));
        model.addAttribute("listaA", alunoService.listarAlunos());
        model.addAttribute("listaF", funcService.listarFuncionarios());
        model.addAttribute("listaT", turmaService.listarTurmas());
        return "telaVisuUsuarios";
    }

    
    
    
    //ControllerTurma
    @GetMapping("/exibirTurmas/{idUser}")
    public String exibirTurmas(@PathVariable Integer idUser, Model model) {
        model.addAttribute("user", funcService.getFuncionarioId(idUser));
        model.addAttribute("listaT", turmaService.listarTurmas());
        model.addAttribute("turma", new Turma());
        return "telaVisuTurmas";
    }

    @GetMapping(value={"/turma/{idTurma}/{idUser}/{userCargo}","/turma/{idTurma}/{idUser}/{userCargo}/{filtro}"})
    public String turma(@PathVariable Integer idTurma, @PathVariable Integer idUser, @PathVariable String userCargo, 
            @PathVariable(required = false) String filtro, Model model) {
        
        Turma turma = turmaService.getTurmaId(idTurma);
        model.addAttribute("turma", turma);
        if(userCargo.equals("Aluno")){
            model.addAttribute("user", alunoService.getAlunoId(idUser));
        }
        else{
            model.addAttribute("user", funcService.getFuncionarioId(idUser));
        }
        
        if(filtro == null){
            model.addAttribute("listaR", recadoService.listarRecados(turma));
        }
        else{
            model.addAttribute("listaR", recadoService.listarRecadosMateria(turma,filtro));
        }
        model.addAttribute("pesquisa",new PesquisaFiltro());
        return "telaTurma";
    }

    @PostMapping("/salvarTurma/{idUser}")
    public String salvarTurma(@PathVariable Integer idUser, @Valid @ModelAttribute("turma") Turma turma, BindingResult result) {
        if (result.hasErrors()) {

        } else {
            turmaService.criarTurma(turma);
        }
        return "redirect:/exibirTurmas/"+idUser;
    }
    
    @GetMapping("/deletarTurma/{id}/{idUser}")
    public String deletarTurma(@PathVariable Integer id, @PathVariable Integer idUser){
        turmaService.deletarTurma(id);
        return "redirect:/exibirTurmas/"+idUser;
    }

    
    
    
    //ControllerRecado
    @GetMapping("/exibirRecado/{idRecado}/{idTurma}/{idUser}/{userCargo}")
    public String exibirRecado(@PathVariable Integer idRecado, @PathVariable Integer idTurma, @PathVariable Integer idUser, 
            @PathVariable String userCargo, Model model) {
        
        Turma turma = turmaService.getTurmaId(idTurma);
        model.addAttribute("turma", turma);
        if(userCargo.equals("Aluno")){
            model.addAttribute("user", alunoService.getAlunoId(idUser));
        }
        else{
            model.addAttribute("user", funcService.getFuncionarioId(idUser));
        }
        model.addAttribute("recado", recadoService.getRecadoId(idRecado));
        return "telaVisuRecado";
    }

    @GetMapping("/criarRecado/{idTurma}/{idFunc}")
    public String cirarRecado(@PathVariable Integer idTurma, @PathVariable Integer idFunc, Model model) {
        Recado recado = new Recado();
        recado.setTurmaId(idTurma);
        recado.setFuncionarioId(idFunc);
        recado.setMateria(funcService.getFuncionarioId(idFunc).getMateriaLecionada());
        model.addAttribute("user",funcService.getFuncionarioId(idFunc));
        model.addAttribute("recado", recado);
        return "telaCriarRecado";
    }

    @GetMapping("/editarRecado/{idRecado}")
    public String editarRecado(@PathVariable Integer idRecado,Model model) {
        model.addAttribute("user",funcService.getFuncionarioId(recadoService.getRecadoId(idRecado).getFuncionarioId()));
        model.addAttribute("recado", recadoService.getRecadoId(idRecado));
        return "telaEditarRecado";
    }

    @PostMapping("/salvarRecado/{idTurma}/{idUser}/{userCargo}")
    public String salvarRecado(@PathVariable Integer idTurma, @PathVariable Integer idUser, @PathVariable String userCargo,
            @Valid @ModelAttribute("recado") Recado recado, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user",funcService.getFuncionarioId(idUser));
            return "telaCriarRecado";
        }
        if (recado.getId() == null) {
            recadoService.criarRecado(recado);
        } else {
            recadoService.atualizarRecado(recado.getId(), recado);
        }
        return "redirect:/turma/"+idTurma+"/"+idUser+"/"+userCargo;
    }
    
    @PostMapping("/pesquisarRecado/{idTurma}/{idUser}/{userCargo}")
    public String pesquisarRecado(@PathVariable Integer idTurma, 
            @PathVariable Integer idUser, @PathVariable String userCargo, @ModelAttribute("pesquisa") PesquisaFiltro filtro) {
        if (filtro.getFiltro().isBlank() && filtro.getFiltro().isEmpty()) {
            return "redirect:/turma/"+idTurma+"/"+idUser+"/"+userCargo;
        }
        return "redirect:/turma/"+idTurma+"/"+idUser+"/"+userCargo+"/"+filtro.getFiltro();
    }
    
    @GetMapping("/deletarRecado/{id}/{idTurma}/{idUser}/{userCargo}")
    public String deletarRecado(@PathVariable Integer id, @PathVariable Integer idTurma, @PathVariable Integer idUser, @PathVariable String userCargo){
        recadoService.deletarRecado(id);
        return "redirect:/turma/"+idTurma+"/"+idUser+"/"+userCargo;
    }

    
    
    
    //ControllerComentario
    @GetMapping("/exibirComentarios/{idRecado}/{idUser}/{userCargo}")
    public String exibirComentarios(@PathVariable Integer idRecado, @PathVariable Integer idUser, @PathVariable String userCargo, Model model) {
        model.addAttribute("recado",recadoService.getRecadoId(idRecado));
        if(userCargo.equals("Aluno")){
            model.addAttribute("user", alunoService.getAlunoId(idUser));
        }
        else{
            model.addAttribute("user", funcService.getFuncionarioId(idUser));
        }
        model.addAttribute("listaC", comentService.listarComentarios(recadoService.getRecadoId(idRecado)));
        return "telaVisuComentarios";
    }

    @GetMapping("/comentar/{idRecado}/{idUser}/{userCargo}")
    public String comentar(@PathVariable Integer idRecado, @PathVariable Integer idUser, @PathVariable String userCargo, Model model) {
        Comentario comentario = new Comentario();
        comentario.setRecadoId(idRecado);
        if(userCargo.equals("Aluno")){
           Aluno aluno = alunoService.getAlunoId(idUser);
           comentario.setUsuarioId(aluno.getId());;
           comentario.setUsuarioCargo(aluno.getCargo());;
           comentario.setUsuarioNome(aluno.getNome());
        }
        else{
           Funcionario func = funcService.getFuncionarioId(idUser);
           comentario.setUsuarioId(func.getId());;
           comentario.setUsuarioCargo(func.getCargo());;
           comentario.setUsuarioNome(func.getNome());
        }
        model.addAttribute("coment", comentario);
        return "telaComentar";
    }

    @GetMapping("/editarComentario/{id}")
    public String editarComentario(@PathVariable Integer id, Model model) {
        Comentario coment = comentService.getComentarioId(id);
        model.addAttribute("coment", coment);
        return "telaEditarComentario";
    }

    @PostMapping("/salvarComent/{idRecado}/{idUser}/{userCargo}")
    public String salvarComent(@PathVariable Integer idRecado, @PathVariable Integer idUser, @PathVariable String userCargo, 
            @Valid @ModelAttribute("coment") Comentario coment, BindingResult result) {
        if (result.hasErrors()) {
            return "telaCriarComentario";
        }
        if (coment.getId() == null) {
            comentService.criarComentario(coment);
        } else {
            comentService.atualizarComentario(coment.getId(), coment);
        }
        return "redirect:/exibirComentarios/"+idRecado+"/"+idUser+"/"+userCargo;
    }
    
    @GetMapping("/deletarComent/{id}/{idRecado}/{idUser}/{userCargo}")
    public String deletarComent(@PathVariable Integer id, @PathVariable Integer idRecado, @PathVariable Integer idUser, 
            @PathVariable String userCargo){
        comentService.deletarComentario(id);
        return "redirect:/exibirComentarios/"+idRecado+"/"+idUser+"/"+userCargo;
    }
}
