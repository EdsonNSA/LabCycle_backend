package br.com.labcycle.api.reagente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reagentes")
public class ReagenteController {

    @Autowired
    private ReagenteRepository repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<Reagente> criarReagente(@RequestBody ReagenteRequestDTO dados) {
        Reagente novoReagente = new Reagente(dados);
        repositorio.save(novoReagente);
        return ResponseEntity.ok(novoReagente);
    }

    @GetMapping
    public ResponseEntity<List<Reagente>> buscarTodosReagentes() {
        List<Reagente> todosReagentes = repositorio.findAll();
        return ResponseEntity.ok(todosReagentes);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Reagente> atualizarReagente(@PathVariable String id, @RequestBody ReagenteRequestDTO dados) {
        Reagente reagente = repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Reagente não encontrado com o id: " + id));

        reagente.setNome(dados.nome());
        reagente.setNumeroCas(dados.numeroCas());
        reagente.setQuantidade(dados.quantidade());
        reagente.setUnidade(dados.unidade());
        reagente.setDataValidade(dados.dataValidade());
        reagente.setLocalizacao(dados.localizacao());
        reagente.setStatus(dados.status());

        return ResponseEntity.ok(reagente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarReagente(@PathVariable String id) {
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}