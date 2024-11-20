package com.queijos_finos.main.controller;

import com.queijos_finos.main.model.Contrato;
import com.queijos_finos.main.model.Fornecedor;
import com.queijos_finos.main.model.Propriedade;
import com.queijos_finos.main.model.Usuarios;
import com.queijos_finos.main.repository.FornecedorRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class FornecedorController {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping("/fornecedores")
    public String showFornecedores(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Fornecedor> fornecedores;
        if (query != null && !query.isEmpty()) {
            fornecedores = fornecedorRepository.findByNomeContainingIgnoreCase(query);
        } else {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            fornecedores = fornecedorRepository.findAll(pageable).getContent();
        }
        model.addAttribute("fornecedores", fornecedores);
        return "fornecedores";
    }

    @PostMapping("/fornecedores")
    public String createFornecedor(@RequestParam("id") Long id,
                                   @RequestParam("nome") String nome,
                                   @RequestParam("nicho") String nicho,
                                   @RequestParam("email") String email,
                                   @RequestParam("qualidade") Double qualidade,
                                   Model model) throws ParseException {

        if (id != -1) {
            fornecedorRepository.findById(id)
                    .map(fornecedor -> {
                        fornecedor.setNome(nome);
                        fornecedor.setNicho(nicho);
                        fornecedor.setEmail(email);
                        fornecedor.setQualidade(qualidade);
                        return fornecedorRepository.save(fornecedor);
                    })
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(nome);
            fornecedor.setNicho(nicho);
            fornecedor.setEmail(email);
            fornecedor.setQualidade(qualidade);
            fornecedorRepository.save(fornecedor);
        }

        model.addAttribute("mensagem", "Fornecedor salvo com sucesso");
        return "redirect:/fornecedores";
    }
    
    @Transactional
    @PostMapping("/fornecedor/delete/{id}")
    public String deleteFornecedor(@PathVariable("id") Long id,
                                   Model model) {
    	fornecedorRepository.deleteFornecedorPropriedadeRelacionamento(id);
        fornecedorRepository.deleteById(id);
        return "redirect:/fornecedores";
    }


    @GetMapping("/fornecedores/cadastrar")
    public String createFornecedorView(@RequestParam(required = false) Long idFornecedor,
                                       Model model) {

        if (idFornecedor != null) {
            Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);
            model.addAttribute("fornecedor", fornecedor.get());
        }

        Pageable pageable = PageRequest.of(0, 20);
        return "fornecedoresCadastrar";
    }
}
