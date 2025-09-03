package br.com.labcycle.api.agendamento;

import br.com.labcycle.api.turma.Turma;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "agendamentos")
@Entity(name = "Agendamento")
public class Agendamento {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id")
    private Turma turma;

    private String nomePratica;
    private LocalDateTime dataHora;

    // Construtores
    public Agendamento() {}

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public String getNomePratica() { return nomePratica; }
    public void setNomePratica(String nomePratica) { this.nomePratica = nomePratica; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}