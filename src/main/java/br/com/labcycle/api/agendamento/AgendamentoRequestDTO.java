package br.com.labcycle.api.agendamento;
import java.time.LocalDateTime;
public record AgendamentoRequestDTO(String turmaId, String nomePratica, LocalDateTime dataHora) {}
