package br.com.labcycle.api.turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("turmas")
@CrossOrigin(origins = "*")
public class TurmaController {

    @Autowired
    private TurmaRepository repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<Turma> criarTurma(@RequestBody DadosCriacaoTurmaDTO dados, UriComponentsBuilder uriBuilder) {
        Turma novaTurma = new Turma(dados);
        repositorio.save(novaTurma);

        URI uri = uriBuilder.path("/turmas/{id}").buildAndExpand(novaTurma.getId()).toUri();
        return ResponseEntity.created(uri).body(novaTurma);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> buscarTodasTurmas() {
        List<Turma> turmas = repositorio.findAll();
        return ResponseEntity.ok(turmas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Turma> atualizarTurma(@PathVariable String id, @RequestBody DadosCriacaoTurmaDTO dados) {
        Turma turma = repositorio.getReferenceById(id);

        turma.setNomeDisciplina(dados.nomeDisciplina());
        turma.setCodigo(dados.codigo());
        turma.setSemestre(dados.semestre());
        turma.setNumeroAlunos(dados.numeroAlunos());
        
        return ResponseEntity.ok(turma);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTurma(@PathVariable String id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build(); 
        }

        repositorio.deleteById(id);

        return ResponseEntity.noContent().build();
    }

@GetMapping("/{id}")
public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable String id) {
    return repositorio.findById(id)
            .map(turma -> ResponseEntity.ok(turma))
            .orElse(ResponseEntity.notFound().build());
}
}