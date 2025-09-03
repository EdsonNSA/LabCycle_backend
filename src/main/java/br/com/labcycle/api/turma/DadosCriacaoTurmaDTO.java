package br.com.labcycle.api.turma;

public record DadosCriacaoTurmaDTO(
        String nomeDisciplina,
        String codigo,
        String semestre,
        Integer numeroAlunos
) {}
