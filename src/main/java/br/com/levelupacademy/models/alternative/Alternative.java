package br.com.levelupacademy.models.alternative;

import br.com.levelupacademy.models.question.Question;
import org.springframework.util.Assert;

import javax.persistence.*;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int sequence;
    private boolean correct;
    private String justification;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Deprecated
    public Alternative() {
    }

    public Alternative(String text, int sequence, boolean correct, String justification, Question question) {
        hasText(text, "Text can't be empty or null");
        notNull(question, "Must be associate with a question");
        this.text = text;
        this.sequence = sequence;
        this.correct = correct;
        this.justification = justification;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "text='" + text + '\'' +
                ", order=" + sequence +
                ", correct=" + correct +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }
}
