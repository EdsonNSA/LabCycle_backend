package br.com.labcycle.api.controller;

import br.com.labcycle.api.service.CylaRequestDTO;
import br.com.labcycle.api.service.CylaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cyla")
@CrossOrigin(origins = "*")
public class CylaController {

    @Autowired
    private CylaService cylaService;

    @PostMapping("/perguntar")
    public ResponseEntity<String> conversarComCyla(@RequestBody CylaRequestDTO dados) {
        String resposta = cylaService.perguntarParaCyla(dados.contexto(), dados.pergunta());
        return ResponseEntity.ok(resposta);
    }
}