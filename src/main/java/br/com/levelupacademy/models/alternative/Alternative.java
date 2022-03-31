package br.com.levelupacademy.models.alternative;

import br.com.levelupacademy.models.question.Question;

import javax.persistence.*;

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
