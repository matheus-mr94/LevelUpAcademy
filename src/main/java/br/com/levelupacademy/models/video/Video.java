package br.com.levelupacademy.models.video;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

import javax.persistence.*;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Video extends Activity {

    private String url;
    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;
    private String transcription;

    @Deprecated
    public Video() {
    }

    public Video(String title, String code, int sequence, Section section, boolean active, String url, Integer durationInMinutes, String transcription) {
        super(title, code, sequence, section, active);
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
