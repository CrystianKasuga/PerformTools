package com.queijos_finos.main;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.queijos_finos.main.model.Contrato;
import com.queijos_finos.main.model.Propriedade;
import com.queijos_finos.main.model.Usuarios;
import com.queijos_finos.main.UsuarioService;

@SpringBootTest
class QueijosFinosApplicationTests {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ContratoService contratoService;

    
    void cadastrarUsuarioComHash() {
        //cenario
        long id = -1;
        String nome = "Arthur";
        String email = "ritzelarthur@gmail.com";
        String senha = "admin";
        BCryptPasswordEncoder hashGenerator = new BCryptPasswordEncoder();

        //acao
        Usuarios usuario = usuarioService.cadastrarAlterarUsuarioHash(id, nome, email, senha);

        //validacao
        assertTrue(hashGenerator.matches(senha, usuario.getSenha()));
    }

    
    void alterarUsuarioComHash() {
        //cenario
        long id = 202;
        String nome = "Arthur";
        String email = "ritzelarthur@gmail.com";
        String senha = "";
        BCryptPasswordEncoder hashGenerator = new BCryptPasswordEncoder();

        //acao
        Usuarios usuario = usuarioService.cadastrarAlterarUsuarioHash(id, nome, email, senha);

        //validacao
        assertEquals(email, usuario.getEmail());
        assertEquals(nome, usuario.getNome());
    }
    
    
    void alterarSenhaUsuarioTest() {
        //cenario
        long id = 202;
        String novaSenha = "admin";
        BCryptPasswordEncoder hashGenerator = new BCryptPasswordEncoder();

        //acao
        Usuarios usuario = usuarioService.alterarSenhaUsuario(id, novaSenha);

        //validacao
        assertTrue(hashGenerator.matches(novaSenha, usuario.getSenha()));
    }
    
    // CONTRATOS -------------------------------
   
    @Test
    public void testCreateContrato() throws ParseException {
    	long idContrato = -1;
        String nome = "Nome";
        String data_1 = "2024-04-05";
        String data_2 = "2024-04-05";
        long idProp = 550;

       
        Contrato contrato = contratoService.saveOrUpdateContrato(idContrato, nome, data_1, data_2, idProp);

        assertEquals(nome, contrato.getNome());
        assertEquals(idProp, contrato.getPropriedade().getIdPropriedade());
        
    }

    @Test
    public void testEditContrato() throws ParseException {
    	long idContrato = 501;
        String nome = "Nome2";
        String data_1 = "2024-04-05";
        String data_2 = "2024-10-05";
        long idProp = 550;

       
        Contrato contrato = contratoService.saveOrUpdateContrato(idContrato, nome, data_1, data_2, idProp);

        assertEquals(nome, contrato.getNome());
        assertEquals(idProp, contrato.getPropriedade().getIdPropriedade());
        
    }
    
    

    @Test
    public void testCreateContratoWithNullName() throws ParseException {
        long idContrato = -1;
        String nome = "";
        String data_1 = "2024-04-05";
        String data_2 = "2024-04-05";
        long idProp = 550;

        Contrato contrato = contratoService.saveOrUpdateContrato(idContrato, nome, data_1, data_2, idProp);

        assertEquals(nome, contrato.getNome());
    }

    @Test
    public void testCreateContratoWithFutureDates() throws ParseException {
        long idContrato = -1;
        String nome = "Nome";
        String data_1 = "2025-04-05";
        String data_2 = "2025-04-05";
        long idProp = 550;

        Contrato contrato = contratoService.saveOrUpdateContrato(idContrato, nome, data_1, data_2, idProp);

        assertEquals(nome, contrato.getNome());
        assertEquals(idProp, contrato.getPropriedade().getIdPropriedade());
        assertTrue(contrato.getDataEmissao().after(new Date()));
    }

    @Test
    public void testEditContratoWithPastDates() throws ParseException {
        long idContrato = 501;
        String nome = "Nome2";
        String data_1 = "2020-04-05";
        String data_2 = "2020-04-05";
        long idProp = 550;

        Contrato contrato = contratoService.saveOrUpdateContrato(idContrato, nome, data_1, data_2, idProp);

        assertEquals(nome, contrato.getNome());
        assertEquals(idProp, contrato.getPropriedade().getIdPropriedade());
        assertTrue(contrato.getDataEmissao().before(new Date())); 
    }

    @Test
    public void testCreateContratoWithSameDates() throws ParseException {
        long idContrato = -1;
        String nome = "Nome";
        String sameDate = "2024-04-05";
        long idProp = 550;

        Contrato contrato = contratoService.saveOrUpdateContrato(idContrato, nome, sameDate, sameDate, idProp);

        assertEquals(nome, contrato.getNome());
        assertEquals(idProp, contrato.getPropriedade().getIdPropriedade());
        assertEquals(contrato.getDataEmissao(), contrato.getDataVercimento()); // Ensure the dates are the same
    }
    @Test
    public void deleteContrato() throws ParseException {
        long idContrato = 500;
       

        Long id = contratoService.deleteContrato(idContrato);

        assertEquals(idContrato, id);
    }
    
}
