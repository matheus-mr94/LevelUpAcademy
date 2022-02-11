package br.com.levelupacademy.models.video;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.validators.Validations;

public class Video extends Activity {

    private String url;
    private Integer time;
    private String transcription;

    public Video(String title, String code,int order, Section section, String url, Integer time, String transcription) {
        super(title, code, order, section);
        Validations.cantBeEmptyOrNull(url, "Url can't be empty or null");
        this.url = url;
        this.time = time;
        this.transcription = transcription;
    }

    public String getUrl() {
        return url;
    }

    public Integer getTime() {
        return time;
    }

    public String getTranscription() {
        return transcription;
    }
}
