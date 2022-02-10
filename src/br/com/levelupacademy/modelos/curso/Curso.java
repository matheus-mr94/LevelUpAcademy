package br.com.levelupacademy.modelos.curso;

public class Curso {

    private String nome;
    private String codigo;
    private Integer tempoEstimado;   //TODO colocar o limite do tempo entre 1 a 20
    private boolean ehPrivado = true;
    private String publicoAlvo;
    private String instrutor;
    private String ementa;
    private String habilidadesDesenvolvidas;

    public Curso(String nome, String codigo, Integer tempoEstimado, String publicoAlvo, String instrutor, String ementa, String habilidadesDesenvolvidas) {
        this.nome = nome;
        this.codigo = codigo;
        this.tempoEstimado = tempoEstimado;
        this.publicoAlvo = publicoAlvo;
        this.instrutor = instrutor;
        this.ementa = ementa;
        this.habilidadesDesenvolvidas = habilidadesDesenvolvidas;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public boolean isEhPrivado() {
        return ehPrivado;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public String getEmenta() {
        return ementa;
    }

    public String getHabilidadesDesenvolvidas() {
        return habilidadesDesenvolvidas;
    }
}
