package br.com.labcycle.api.agendamento;

import br.com.labcycle.api.pratica.DadosPraticaResumoDTO;

import java.time.LocalDateTime;

public record DadosListagemAgendamentoDTO(
        String id,
        LocalDateTime dataHora,
        String turmaCodigo,
        String disciplinaNome,
        DadosPraticaResumoDTO pratica
) {
    public DadosListagemAgendamentoDTO(Agendamento agendamento) {
        this(
            agendamento.getId(),
            agendamento.getDataHora(),
            agendamento.getTurma().getCodigo(),
            agendamento.getTurma().getNomeDisciplina(),
            agendamento.getPratica() != null ? new DadosPraticaResumoDTO(agendamento.getPratica()) : null
        );
    }
}
