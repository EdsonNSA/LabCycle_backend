package br.com.labcycle.api.turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("turmas")
public class TurmaController {
    @Autowired
    private TurmaRepository repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<Turma> criarTurma(@RequestBody DadosCriacaoTurmaDTO dados) {
        Turma novaTurma = new Turma(dados);
        repositorio.save(novaTurma);
        return ResponseEntity.ok(novaTurma);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> buscarTodasTurmas() {
        return ResponseEntity.ok(repositorio.findAll());
    }
}