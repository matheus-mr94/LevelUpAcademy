package br.com.levelupacademy.modelos.secao;

import br.com.levelupacademy.modelos.curso.Curso;

public class Secao {

    private String nomeSecao;
    private String codigo;
    //TODO ordem que será exibido
    private boolean ativa = false;
    private boolean ehProva = false;
    private Curso curso;

    public Secao(String nomeSecao, String codigo, Curso curso) {
        this.nomeSecao = nomeSecao;
        this.codigo = codigo;
        this.curso = curso;
    }

    public String getNomeSecao() {
        return nomeSecao;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public boolean isEhProva() {
        return ehProva;
    }

    public Curso getCurso() {
        return curso;
    }
}
