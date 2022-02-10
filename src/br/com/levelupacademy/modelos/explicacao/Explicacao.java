package br.com.levelupacademy.modelos.explicacao;

import br.com.levelupacademy.modelos.atividade.Atividade;
import br.com.levelupacademy.modelos.secao.Secao;

public class Explicacao extends Atividade {

    private String texto;

    public Explicacao(String titulo, String codigo, Secao secao, String texto) {
        super(titulo, codigo, secao);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
