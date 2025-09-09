package br.com.labcycle.api.pratica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("praticas")
@CrossOrigin(origins = "*")
public class PraticaController {

    @Autowired
    private PraticaRepository repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<Pratica> criarPratica(@RequestBody DadosCadastroPraticaDTO dados, UriComponentsBuilder uriBuilder) {
        Pratica novaPratica = new Pratica(dados);
        repositorio.save(novaPratica);

        URI uri = uriBuilder.path("/praticas/{id}").buildAndExpand(novaPratica.getId()).toUri();
        return ResponseEntity.created(uri).body(novaPratica);
    }

    @GetMapping
    public ResponseEntity<List<Pratica>> buscarTodasPraticas() {
        return ResponseEntity.ok(repositorio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pratica> buscarPraticaPorId(@PathVariable String id) {
        return repositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Pratica> atualizarPratica(@PathVariable String id, @RequestBody DadosCadastroPraticaDTO dados) {
        Pratica pratica = repositorio.getReferenceById(id);
        
        pratica.atualizarDados(dados); 
        
        return ResponseEntity.ok(pratica);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarPratica(@PathVariable String id) {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}