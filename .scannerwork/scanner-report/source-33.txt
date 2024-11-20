package com.queijos_finos.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Propriedade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropriedade;

    private String nomePropriedade;
    private String email;
    private int status;
    private String CPF;
    private String CNPJ;
    private String telefone;
    private String celular;
    private String rua;
    private String bairro;
    private String cidade;
    private String UF;
    private String latitude;
    private String longitude;
    private String nomeProdutor;

    @ManyToMany
    @JoinTable(name = "fornecedor_has_propriedade",
            joinColumns = @JoinColumn(name = "propriedade_id_propriedade"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id_fornecedor"))
    private List<Fornecedor> fornecedores;

    @ManyToMany
    @JoinTable(name = "tecnologia_has_propriedade",
            joinColumns = @JoinColumn(name = "propriedade_id_propriedade"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id_tecnologia"))
    private List<Tecnologias> tecnologias;

    @ManyToMany
    @JoinTable(name = "propriedade_has_curso",
            joinColumns = @JoinColumn(name = "propriedade_id_propriedade"),
            inverseJoinColumns = @JoinColumn(name = "curso_id_curso"))
    private List<Curso> cursos;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "propriedade")
    private List<Imagem> imagens;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "propriedade")
    @JsonManagedReference
    private List<Contrato> contratos;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "propriedade")
    private List<Amostra> amostras;


    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Tecnologias> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<Tecnologias> tecnologias) {
        this.tecnologias = tecnologias;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Amostra> getAmostras() {
        return amostras;
    }

    public void setAmostras(List<Amostra> amostras) {
        this.amostras = amostras;
    }

    public Long getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(Long idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public String getNomePropriedade() {
        return nomePropriedade;
    }

    public void setNomePropriedade(String nomePropriedade) {
        this.nomePropriedade = nomePropriedade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String uF) {
        UF = uF;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNomeProdutor() {
        return nomeProdutor;
    }

    public void setNomeProdutor(String nomeProdutor) {
        this.nomeProdutor = nomeProdutor;
    }

    @Override
    public String toString() {
        return "Propriedade [idPropriedade=" + idPropriedade + ", nomePropriedade=" + nomePropriedade + ", email="
                + email + ", status=" + status + ", CPF=" + CPF + ", CNPJ=" + CNPJ + ", telefone=" + telefone
                + ", celular=" + celular + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", UF=" + UF
                + ", latitude=" + latitude + ", longitude=" + longitude + ", nomeProdutor=" + nomeProdutor
                + ", fornecedores=" + fornecedores + ", tecnologias=" + tecnologias + ", cursos=" + cursos.toString()
                + ", imagens=" + imagens + ", contratos=" + contratos + ", amostras=" + amostras + "]";
    }


}
