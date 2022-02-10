package br.com.levelupacademy.modelos.subcategoria;

import br.com.levelupacademy.modelos.categoria.Categoria;

public class Subcategoria {

    private String nome;
    private String codigo;
    private String descricao;
    private String guiaDeEstudo;
    private boolean ativa;
    //TODO ordem que será exibido
    private Categoria categoria;

    public Subcategoria(String nome, String codigo, String descricao, String guiaDeEstudo, boolean ativa, Categoria categoria) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.guiaDeEstudo = guiaDeEstudo;
        this.ativa = ativa;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getGuiaDeEstudo() {
        return guiaDeEstudo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
