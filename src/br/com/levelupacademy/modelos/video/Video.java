package br.com.levelupacademy.modelos.video;

import br.com.levelupacademy.modelos.atividade.Atividade;
import br.com.levelupacademy.modelos.secao.Secao;

public class Video extends Atividade {

    private String url;
    private Integer minutosDeVideo;
    private String transcricao;

    public Video(String titulo, String codigo, Secao secao, String url, Integer minutosDeVideo, String transcricao) {
        super(titulo, codigo, secao);
        this.url = url;
        this.minutosDeVideo = minutosDeVideo;
        this.transcricao = transcricao;
    }

    public String getUrl() {
        return url;
    }

    public Integer getMinutosDeVideo() {
        return minutosDeVideo;
    }

    public String getTranscricao() {
        return transcricao;
    }
}
