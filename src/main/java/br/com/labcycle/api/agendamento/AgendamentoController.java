package br.com.labcycle.api.agendamento;

import br.com.labcycle.api.turma.Turma;
import br.com.labcycle.api.turma.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepositorio;

    @Autowired
    private TurmaRepository turmaRepositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemAgendamentoDTO> criarAgendamento(@RequestBody DadosAgendamentoDTO dados, UriComponentsBuilder uriBuilder) {
        Turma turma = turmaRepositorio.findById(dados.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma com id " + dados.turmaId() + " não encontrada."));

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setTurma(turma);
        novoAgendamento.setNomePratica(dados.nomePratica());
        novoAgendamento.setDataHora(dados.dataHora());

        agendamentoRepositorio.save(novoAgendamento);

        URI uri = uriBuilder.path("/agendamentos/{id}").buildAndExpand(novoAgendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemAgendamentoDTO(novoAgendamento));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemAgendamentoDTO>> buscarTodosAgendamentos() {
        List<DadosListagemAgendamentoDTO> agendamentos = agendamentoRepositorio.findAll().stream()
                .map(DadosListagemAgendamentoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(agendamentos);
    }
    
    @GetMapping("/por-turma/{turmaId}")
    public ResponseEntity<List<DadosListagemAgendamentoDTO>> buscarAgendamentosPorTurma(@PathVariable String turmaId) {
        List<DadosListagemAgendamentoDTO> agendamentos = agendamentoRepositorio.findByTurmaId(turmaId).stream()
                .map(DadosListagemAgendamentoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemAgendamentoDTO> atualizarAgendamento(@PathVariable String id, @RequestBody DadosAgendamentoDTO dados) {
        Agendamento agendamento = agendamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento com id " + id + " não encontrado."));

        Turma turma = turmaRepositorio.findById(dados.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma com id " + dados.turmaId() + " não encontrada."));

        agendamento.setTurma(turma);
        agendamento.setNomePratica(dados.nomePratica());
        agendamento.setDataHora(dados.dataHora());

        return ResponseEntity.ok(new DadosListagemAgendamentoDTO(agendamento));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarAgendamento(@PathVariable String id) {
        if (!agendamentoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agendamentoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}