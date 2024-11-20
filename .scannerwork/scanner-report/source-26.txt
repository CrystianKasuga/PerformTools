package com.queijos_finos.main.controller;

import java.text.ParseException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.queijos_finos.main.model.*;
import com.queijos_finos.main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PropriedadeController {

    @Autowired
    private PropriedadeRepository propriedadeRepo;
    @Autowired
    private CursosRepository cursoRepo;
    @Autowired
    private TecnologiaRepository tecnologiaRepo;
    @Autowired
    private FornecedorRepository fornecedorRepo;
    @Autowired
    private AmostraRepository amostraRepo;


    @GetMapping("/propriedade")
    public String showPropriedade(Model model) {
        List<Propriedade> propriedade;
        Pageable pageable = PageRequest.of(0, 100);
        propriedade = propriedadeRepo.findAll(pageable).getContent();
        model.addAttribute("propriedade", propriedade);
        return "propriedade";
    }

    @GetMapping("/propriedade/visualizar")
    public String detailsPropriedade(@RequestParam(required = false) Long idPropriedade, Model model) {
        Optional<Propriedade> propriedadeOptional = propriedadeRepo.findById(idPropriedade);
        Propriedade propriedade = propriedadeOptional.orElse(null);

        model.addAttribute("propriedade", propriedade);
        model.addAttribute("amostra", new Amostra());
        return "visualizarPropriedade";
    }

    @PostMapping("/amostras")
    public String createAmostra(@RequestParam Long propriedadeId, @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data,
                                @RequestParam Double quantidadeleite, @RequestParam Double quantidadeQueijo,
                                @RequestParam String observacao) {

        Propriedade propriedade = propriedadeRepo.findById(propriedadeId)
                .orElseThrow(() -> new IllegalArgumentException("Propriedade não encontrada"));

        Amostra amostra = new Amostra();
        amostra.setData(data);
        amostra.setQuantidadeleite(quantidadeleite);
        amostra.setQuantidadeQueijo(quantidadeQueijo);
        amostra.setObservacao(observacao);
        amostra.setPropriedade(propriedade);

        amostraRepo.save(amostra);

        return "redirect:/propriedade/visualizar?idPropriedade=" + propriedadeId;
    }

    @DeleteMapping("/amostra/delete/{id}")
    @ResponseBody
    public void deleteAmostra(@PathVariable("id") Long id) {
        amostraRepo.deleteById(id); // Remove a amostra pelo ID fornecido
    }


    @GetMapping("/propriedade/cadastrar")
    public String createPropriedadeView(@RequestParam(required = false) Long idPropriedade,
                                        Model model) {

        if (idPropriedade != null) {
            Optional<Propriedade> propriedade = propriedadeRepo.findById(idPropriedade);

            model.addAttribute("propriedade", propriedade.get());
        }


        List<Curso> cursos = cursoRepo.findAll();
        List<Tecnologias> tecnologias = tecnologiaRepo.findAll();
        List<Fornecedor> fornecedores = fornecedorRepo.findAll();

        model.addAttribute("cursos", cursos);
        model.addAttribute("tecnologias", tecnologias);
        model.addAttribute("fornecedores", fornecedores);

        return "propriedadeCadastrar";
    }


    @PostMapping("/propriedade")
    public String createPropriedade(@RequestBody Propriedade propriedadeReq,
                                    Model model) throws ParseException {

        for (Tecnologias tecnologia : propriedadeReq.getTecnologias()) {
            List<Tecnologias> tecnologiaExistente = tecnologiaRepo.findByNome(tecnologia.getNome());
            if (tecnologiaExistente.isEmpty()) {
                tecnologia.setId(null);
                tecnologiaRepo.save(tecnologia);
                tecnologia.setId(tecnologiaRepo.findFirstByOrderByIdDesc().getId());
            }
        }

        System.out.print(propriedadeReq);

        if (propriedadeReq.getIdPropriedade() != -1) {
            propriedadeRepo.findById(propriedadeReq.getIdPropriedade())
                    .map(propriedade -> {
                        propriedade.setNomePropriedade(propriedadeReq.getNomePropriedade());
                        propriedade.setEmail(propriedadeReq.getEmail());
                        propriedade.setStatus(propriedadeReq.getStatus());
                        propriedade.setCPF(propriedadeReq.getCPF());
                        propriedade.setCNPJ(propriedadeReq.getCNPJ());
                        propriedade.setTelefone(propriedadeReq.getTelefone());
                        propriedade.setCelular(propriedadeReq.getCelular());
                        propriedade.setRua(propriedadeReq.getRua());
                        propriedade.setBairro(propriedadeReq.getBairro());
                        propriedade.setCidade(propriedadeReq.getCidade());
                        propriedade.setUF(propriedadeReq.getUF());
                        propriedade.setLatitude(propriedadeReq.getLatitude());
                        propriedade.setLongitude(propriedadeReq.getLongitude());
                        propriedade.setNomeProdutor(propriedadeReq.getNomeProdutor());
                        propriedade.setCursos(propriedadeReq.getCursos());
                        propriedade.setTecnologias(propriedadeReq.getTecnologias());
                        propriedade.setFornecedores(propriedadeReq.getFornecedores());
                        return propriedadeRepo.save(propriedade);
                    })
                    .orElseThrow(() -> new RuntimeException("Propriedade não encontrada com o ID: " + propriedadeReq.getIdPropriedade()));
        } else {
            propriedadeReq.setIdPropriedade(null);
            propriedadeRepo.save(propriedadeReq);
        }

        model.addAttribute("mensagem", "Propriedade salva com sucesso");
        return "redirect:/propriedade";
    }

    @PostMapping("/propriedade/delete/{id}")
    public String createContrato(@PathVariable("id") Long idPropriedade,
                                 Model model) {

        propriedadeRepo.deleteById(idPropriedade);

        model.addAttribute("mensagem", "Propriedade deletada com sucesso");
        return "redirect:/propriedade";
    }


}

