package br.com.labcycle.api.agendamento;

import br.com.labcycle.api.turma.Turma;
import br.com.labcycle.api.turma.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepositorio;

    @Autowired
    private TurmaRepository turmaRepositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody DadosAgendamentoDTO dados) {
        Turma turma = turmaRepositorio.findById(dados.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setTurma(turma);
        novoAgendamento.setNomePratica(dados.nomePratica());
        novoAgendamento.setDataHora(dados.dataHora());

        agendamentoRepositorio.save(novoAgendamento);
        return ResponseEntity.ok(novoAgendamento);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemAgendamentoDTO>> buscarTodosAgendamentos() {
        List<DadosListagemAgendamentoDTO> agendamentos = agendamentoRepositorio.findAll().stream()
                .map(DadosListagemAgendamentoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemAgendamentoDTO> atualizarAgendamento(@PathVariable String id, @RequestBody DadosAgendamentoDTO dados) {
        Agendamento agendamento = agendamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Turma turma = turmaRepositorio.findById(dados.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        agendamento.setTurma(turma);

        agendamento.setNomePratica(dados.nomePratica());
        agendamento.setDataHora(dados.dataHora());

        return ResponseEntity.ok(new DadosListagemAgendamentoDTO(agendamento));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarAgendamento(@PathVariable String id) {
        agendamentoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}