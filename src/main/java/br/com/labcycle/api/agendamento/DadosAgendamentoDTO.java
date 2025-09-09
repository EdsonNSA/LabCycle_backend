package br.com.labcycle.api.agendamento;

import java.time.LocalDateTime;

public record DadosAgendamentoDTO(
        String turmaId,
        String praticaId,
        LocalDateTime dataHora
) {}