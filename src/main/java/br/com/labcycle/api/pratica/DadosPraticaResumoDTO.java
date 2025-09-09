package br.com.labcycle.api.pratica;

public record DadosPraticaResumoDTO(String id, String titulo) {
    
    public DadosPraticaResumoDTO(Pratica pratica) {
        this(pratica.getId(), pratica.getTitulo());
    }
}