package br.com.levelupacademy.modelos.atividade;

import br.com.levelupacademy.modelos.secao.Secao;

public class Atividade {

    private String titulo;
    private String codigo;
    private boolean ativa = false;
    //TODO ordem que será exibido
    private Secao secao;

    public Atividade(String titulo, String codigo, Secao secao) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.secao = secao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public Secao getSecao() {
        return secao;
    }
}
