package com.queijos_finos.main.controller;

import com.queijos_finos.main.model.Curso;
import com.queijos_finos.main.repository.CursosRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping
    public String showCursos(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Curso> cursos;
        if (search != null && !search.isEmpty()) {
            cursos = cursosRepository.findByNomeContainingIgnoreCase(search);
        } else {
            cursos = cursosRepository.findAll();
        }
        model.addAttribute("cursos", cursos);
        return "cursos";
    }

    @GetMapping("/cadastrar")
    public String createCursosView(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursosCadastrar";
    }

    @PostMapping
    public String cadastrarCurso(@ModelAttribute Curso curso, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cursosCadastrar";
        }
        cursosRepository.save(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editCursosView(@PathVariable Long id, Model model) {
        Optional<Curso> curso = cursosRepository.findById(id);
        if (curso.isPresent()) {
            model.addAttribute("curso", curso.get());
            return "cursosEditar";
        } else {
            model.addAttribute("mensagem", "Curso não encontrado");
            return "redirect:/cursos";
        }
    }

    @PostMapping("/editar/{id}")
    public String editCursos(@PathVariable Long id, @ModelAttribute Curso curso, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("curso", curso);
            return "cursosEditar";
        }
        curso.setId(id);
        cursosRepository.save(curso);
        model.addAttribute("mensagem", "Curso atualizado com sucesso");
        return "redirect:/cursos";
    }
    
    @Transactional
    @PostMapping("/delete/{id}")
    public String deleteCursos(@PathVariable Long id, Model model) {
    	cursosRepository.deleteCursoPropriedadeRelacionamento(id);
        cursosRepository.deleteById(id);
        model.addAttribute("mensagem", "Curso excluído com sucesso");
        return "redirect:/cursos";
    }
}
