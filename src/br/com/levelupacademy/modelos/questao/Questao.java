package br.com.levelupacademy.modelos.questao;

import br.com.levelupacademy.enums.TipoResposta;
import br.com.levelupacademy.modelos.atividade.Atividade;
import br.com.levelupacademy.modelos.secao.Secao;

public class Questao extends Atividade {

    private String enunciado;
    private TipoResposta tipoResposta;

    public Questao(String titulo, String codigo, Secao secao, String enunciado, TipoResposta tipoResposta) {
        super(titulo, codigo, secao);
        this.enunciado = enunciado;
        this.tipoResposta = tipoResposta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public TipoResposta getTipoResposta() {
        return tipoResposta;
    }
}
