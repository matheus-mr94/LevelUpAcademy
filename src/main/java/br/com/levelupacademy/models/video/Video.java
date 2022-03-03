package br.com.levelupacademy.models.video;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;

public class Video extends Activity {

    private String url;
    private Integer durationInMinutes;
    private String transcription;

    public Video(String title, String code, int order, Section section, String url, Integer durationInMinutes, String transcription) {
        super(title, code, order, section);
        cantBeEmptyOrNull(url, "Url can't be empty or null");
        this.url = url;
        this.durationInMinutes = durationInMinutes;
        this.transcription = transcription;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", time=" + durationInMinutes +
                ", transcription='" + transcription + '\'' +
                '}';
    }
}
