package br.com.labcycle.api.pratica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "praticas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pratica {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String titulo;
    private String disciplina;
    private String duracao;
    private String dificuldade;
    private String videoUrl;

    @Lob
    private String objetivosJson;

    @Lob
    private String procedimentoJson;

    @Lob
    private String segurancaJson;

    @Lob
    private String materiaisJson;

    @Lob
    private String reagentesJson;

    private String descarte;

    public Pratica() {}

    public Pratica(DadosCadastroPraticaDTO dados) {
        this.titulo = dados.titulo();
        this.disciplina = dados.disciplina();
        this.duracao = dados.duracao();
        this.dificuldade = dados.dificuldade();
        this.videoUrl = dados.videoUrl();
        this.objetivosJson = dados.objetivosJson();
        this.procedimentoJson = dados.procedimentoJson();
        this.segurancaJson = dados.segurancaJson();
        this.materiaisJson = dados.materiaisJson();
        this.reagentesJson = dados.reagentesJson();
        this.descarte = dados.descarte();
    }

    public void atualizarDados(DadosCadastroPraticaDTO dados) {
        if (dados.titulo() != null) this.titulo = dados.titulo();
        if (dados.disciplina() != null) this.disciplina = dados.disciplina();
        if (dados.duracao() != null) this.duracao = dados.duracao();
        if (dados.dificuldade() != null) this.dificuldade = dados.dificuldade();
        if (dados.videoUrl() != null) this.videoUrl = dados.videoUrl();
        if (dados.objetivosJson() != null) this.objetivosJson = dados.objetivosJson();
        if (dados.procedimentoJson() != null) this.procedimentoJson = dados.procedimentoJson();
        if (dados.segurancaJson() != null) this.segurancaJson = dados.segurancaJson();
        if (dados.materiaisJson() != null) this.materiaisJson = dados.materiaisJson();
        if (dados.reagentesJson() != null) this.reagentesJson = dados.reagentesJson();
        if (dados.descarte() != null) this.descarte = dados.descarte();
    }

    //Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getObjetivosJson() {
        return objetivosJson;
    }

    public void setObjetivosJson(String objetivosJson) {
        this.objetivosJson = objetivosJson;
    }

    public String getProcedimentoJson() {
        return procedimentoJson;
    }

    public void setProcedimentoJson(String procedimentoJson) {
        this.procedimentoJson = procedimentoJson;
    }

    public String getSegurancaJson() {
        return segurancaJson;
    }

    public void setSegurancaJson(String segurancaJson) {
        this.segurancaJson = segurancaJson;
    }

    public String getMateriaisJson() {
        return materiaisJson;
    }

    public void setMateriaisJson(String materiaisJson) {
        this.materiaisJson = materiaisJson;
    }

    public String getReagentesJson() {
        return reagentesJson;
    }

    public void setReagentesJson(String reagentesJson) {
        this.reagentesJson = reagentesJson;
    }

    public String getDescarte() {
        return descarte;
    }

    public void setDescarte(String descarte) {
        this.descarte = descarte;
    }
}