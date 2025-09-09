package br.com.labcycle.api.comentario;

import br.com.labcycle.api.pratica.Pratica;
import br.com.labcycle.api.pratica.PraticaRepository;
import br.com.labcycle.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/praticas/{praticaId}/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepositorio;

    @Autowired
    private PraticaRepository praticaRepositorio;
    

    @GetMapping
    public ResponseEntity<List<DadosListagemComentarioDTO>> buscarComentarios(@PathVariable String praticaId) {
        List<DadosListagemComentarioDTO> comentarios = comentarioRepositorio.findByPraticaIdOrderByDataCriacaoDesc(praticaId)
                .stream()
                .map(DadosListagemComentarioDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(comentarios);
    }

    @PostMapping
    public ResponseEntity<DadosListagemComentarioDTO> adicionarComentario(
            @PathVariable String praticaId,
            @RequestBody DadosCriacaoComentarioDTO dados,
            Authentication authentication,
            UriComponentsBuilder uriBuilder
    ) {
        User autor = (User) authentication.getPrincipal();

        Pratica pratica = praticaRepositorio.findById(praticaId)
                .orElseThrow(() -> new RuntimeException("Prática não encontrada"));

        Comentario novoComentario = new Comentario();
        novoComentario.setConteudo(dados.conteudo());
        novoComentario.setAutor(autor);
        novoComentario.setPratica(pratica);
        novoComentario.setDataCriacao(LocalDateTime.now());

        comentarioRepositorio.save(novoComentario);

        DadosListagemComentarioDTO dtoDeResposta = new DadosListagemComentarioDTO(novoComentario);
        URI uri = uriBuilder.path("/praticas/{praticaId}/comentarios/{comentarioId}")
                .buildAndExpand(praticaId, novoComentario.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoDeResposta);
    }
}