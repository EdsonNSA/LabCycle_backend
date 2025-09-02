package br.com.labcycle.api.reagente;
import java.time.LocalDate;
public record ReagenteRequestDTO(
    String nome,
    String numeroCas,
    Double quantidade,
    String unidade,
    LocalDate dataValidade,
    String localizacao,
    StatusReagente status
) {}