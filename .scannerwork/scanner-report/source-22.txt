package com.queijos_finos.main.controller;

import com.queijos_finos.main.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.queijos_finos.main.model.Amostra;
import com.queijos_finos.main.model.Propriedade;
import com.queijos_finos.main.repository.AmostraRepository;
import com.queijos_finos.main.repository.PropriedadeRepository;

import java.util.Date;

@Controller
@RequestMapping("/amostras")
public class AmostraController {

    @Autowired
    private AmostraRepository amostraRepository;

    @Autowired
    private PropriedadeRepository propriedadeRepository;


}
