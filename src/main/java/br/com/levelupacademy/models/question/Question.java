package br.com.levelupacademy.models.question;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;
import org.springframework.util.Assert;

import javax.persistence.*;

import static org.springframework.util.Assert.hasText;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Question extends Activity {

    private String statement;
    @Column(name = "question_type", columnDefinition = "ENUM")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.SINGLE_CHOICE;

    @Deprecated
    public Question() {
    }

    public Question(String title, String code, int sequence, Section section, boolean active, String statement) {
        super(title, code, sequence, section, active);
        hasText(statement, "statement can't be empty or null");
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "Question{" +
                "statement='" + statement + '\'' +
                ", answerType=" + questionType +
                '}';
    }
}
