package br.com.labcycle.api.reagente;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "reagentes")
@Entity(name = "Reagente")
public class Reagente {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String numeroCas; // CAS Number
    private Double quantidade;
    private String unidade; // ex: mL, L, g, kg
    private LocalDate dataValidade;
    private String localizacao;

    @Enumerated(EnumType.STRING)
    private StatusReagente status;

    // Construtores
    public Reagente() {}

    public Reagente(ReagenteRequestDTO dados) {
        this.nome = dados.nome();
        this.numeroCas = dados.numeroCas();
        this.quantidade = dados.quantidade();
        this.unidade = dados.unidade();
        this.dataValidade = dados.dataValidade();
        this.localizacao = dados.localizacao();
        this.status = dados.status();
    }

    // Getters e Setters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getNumeroCas() { return numeroCas; }
    public Double getQuantidade() { return quantidade; }
    public String getUnidade() { return unidade; }
    public LocalDate getDataValidade() { return dataValidade; }
    public String getLocalizacao() { return localizacao; }
    public StatusReagente getStatus() { return status; }

    public void setNome(String nome) { this.nome = nome; }
    public void setNumeroCas(String numeroCas) { this.numeroCas = numeroCas; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public void setStatus(StatusReagente status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reagente reagente = (Reagente) o;
        return Objects.equals(id, reagente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}