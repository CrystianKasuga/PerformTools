package com.queijos_finos.main.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String conteudo;
    private String professor;
    private String duracao;

    @ManyToMany(mappedBy = "cursos")
    private List<Propriedade> propriedades;


    @Override
    public int hashCode() {
        return Objects.hash(id, conteudo, duracao, nome, professor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        return Objects.equals(id, other.id) && Objects.equals(conteudo, other.conteudo)
                && Objects.equals(duracao, other.duracao) && Objects.equals(nome, other.nome)
                && Objects.equals(professor, other.professor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idReq) {
        id = idReq;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Curso [Id=" + id + ", nome=" + nome + ", conteudo=" + conteudo + ", professor=" + professor
                + ", duracao=" + duracao + ", propriedades=" + propriedades + "]";
    }


}
