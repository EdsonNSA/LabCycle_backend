package br.com.labcycle.api.comentario;

import java.time.LocalDateTime;

public record DadosListagemComentarioDTO(
        String id,
        String conteudo,
        String nomeAutor,
        LocalDateTime dataCriacao
) {

    public DadosListagemComentarioDTO(Comentario comentario) {
        this(
            comentario.getId(), 
            comentario.getConteudo(), 
            comentario.getAutor().getName(),
            comentario.getDataCriacao()
        );
    }
}