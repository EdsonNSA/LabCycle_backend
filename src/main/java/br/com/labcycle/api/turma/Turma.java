package br.com.labcycle.api.turma;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Table(name = "turmas")
@Entity(name = "Turma")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Turma {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nomeDisciplina;
    private String codigo;
    private String semestre;
    private Integer numeroAlunos;

    // Construtores
    public Turma() {}

    public Turma(DadosCriacaoTurmaDTO dados) {
        this.nomeDisciplina = dados.nomeDisciplina();
        this.codigo = dados.codigo();
        this.semestre = dados.semestre();
        this.numeroAlunos = dados.numeroAlunos();
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNomeDisciplina() { return nomeDisciplina; }
    public void setNomeDisciplina(String nomeDisciplina) { this.nomeDisciplina = nomeDisciplina; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
    public Integer getNumeroAlunos() { return numeroAlunos; }
    public void setNumeroAlunos(Integer numeroAlunos) { this.numeroAlunos = numeroAlunos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}