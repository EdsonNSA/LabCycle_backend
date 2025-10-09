package br.com.labcycle.api.pratica;


public record DadosCadastroPraticaDTO(
        String titulo,
        String disciplina,
        String duracao,
        String dificuldade,
        String videoUrl,
        String objetivosJson,
        String procedimentoJson,
        String segurancaJson,
        String materiaisJson,
        String reagentesJson,
        String descarteJson
) {}