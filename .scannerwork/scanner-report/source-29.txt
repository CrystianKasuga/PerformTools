package com.queijos_finos.main.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    private Date dataEmissao;
    private Date dataVercimento;

    @ManyToOne
    @JoinColumn(name = "Propriedade_idPropriedade")
    @JsonBackReference
    private Propriedade propriedade;


    public Contrato() {

    }

    public Contrato(String nome, Date dataEmissao, Date dataVercimento, Propriedade propriedade) {
        super();
        this.nome = nome;
        this.dataEmissao = dataEmissao;
        this.dataVercimento = dataVercimento;
        this.propriedade = propriedade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, dataEmissao, dataVercimento, nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contrato other = (Contrato) obj;
        return Objects.equals(Id, other.Id) && Objects.equals(dataEmissao, other.dataEmissao)
                && Objects.equals(dataVercimento, other.dataVercimento) && Objects.equals(nome, other.nome);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVercimento() {
        return dataVercimento;
    }

    public void setDataVercimento(Date dataVercimento) {
        this.dataVercimento = dataVercimento;
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public String getDataVercimentoString() {
        SimpleDateFormat formatoSaida = new SimpleDateFormat("y-MM-dd");
        String dataFormatada = formatoSaida.format(dataVercimento);

        return dataFormatada;
    }

    public String getDataEmissaoString() {
        SimpleDateFormat formatoSaida = new SimpleDateFormat("y-MM-dd");
        String dataFormatada = formatoSaida.format(dataEmissao);

        return dataFormatada;
    }


}
