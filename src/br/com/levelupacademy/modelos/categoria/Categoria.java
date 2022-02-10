package br.com.levelupacademy.modelos.categoria;

public class Categoria {

    private String nome;
    private String codigo;
    private String descricao;
    private String guiaDeEstudo;
    private boolean ativa = false;
    //TODO ordem que será exibido
    private String url;
    private String corHexadecimal;


    public Categoria(String nome, String codigo, String descricao, String guiaDeEstudo, boolean ativa, String url, String corHexadecimal) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.guiaDeEstudo = guiaDeEstudo;
        this.ativa = ativa;
        this.url = url;
        this.corHexadecimal = corHexadecimal;
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
    public String getUrl() {
        return url;
    }
    public String getCorHexadecimal() {
        return corHexadecimal;
    }
}
