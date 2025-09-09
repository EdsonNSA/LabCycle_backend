package br.com.labcycle.api.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {

    @Query("""
            SELECT a FROM Agendamento a
            JOIN FETCH a.turma
            JOIN FETCH a.pratica
            WHERE a.turma.id = :turmaId
            ORDER BY a.dataHora DESC
            """)
    List<Agendamento> findByTurmaIdWithDetails(String turmaId);
}