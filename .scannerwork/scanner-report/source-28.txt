package com.queijos_finos.main.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Amostra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date data;
    private Double quantidadeleite;
    private Double quantidadeQueijo;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "Propriedade_idPropriedade")
    private Propriedade propriedade;

    @Override
    public int hashCode() {
        return Objects.hash(Id, data, observacao, quantidadeQueijo, quantidadeleite, propriedade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Amostra other = (Amostra) obj;
        return Objects.equals(Id, other.Id) && Objects.equals(data, other.data)
                && Objects.equals(observacao, other.observacao)
                && Objects.equals(quantidadeQueijo, other.quantidadeQueijo)
                && Objects.equals(quantidadeleite, other.quantidadeleite)
                && Objects.equals(propriedade, other.propriedade);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getQuantidadeleite() {
        return quantidadeleite;
    }

    public void setQuantidadeleite(Double quantidadeleite) {
        this.quantidadeleite = quantidadeleite;
    }

    public Double getQuantidadeQueijo() {
        return quantidadeQueijo;
    }

    public void setQuantidadeQueijo(Double quantidadeQueijo) {
        this.quantidadeQueijo = quantidadeQueijo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }
}
