package br.com.labcycle.api.agendamento;
import java.time.LocalDateTime;

public record DadosListagemAgendamentoDTO(String id, String nomePratica, LocalDateTime dataHora, String nomeDisciplina, String codigoTurma) {
    public DadosListagemAgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getNomePratica(), agendamento.getDataHora(), agendamento.getTurma().getNomeDisciplina(), agendamento.getTurma().getCodigo());
    }
}