package br.com.levelupacademy.models.alternative;

import br.com.levelupacademy.models.question.Question;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;
import static br.com.levelupacademy.validators.Validations.objectIsNotNull;

public class Alternative {

    private String text;
    private int orderInSystem;
    private boolean correct;
    private String justification;
    private Question question;

    public Alternative(String text, int orderInSystem, boolean correct, String justification, Question question) {
        cantBeEmptyOrNull(text, "Text can't be empty or null");
        objectIsNotNull(question,"Should be associate with a question");
        this.text = text;
        this.orderInSystem = orderInSystem;
        this.correct = correct;
        this.justification = justification;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "text='" + text + '\'' +
                ", order=" + orderInSystem +
                ", correct=" + correct +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }
}