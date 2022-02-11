package br.com.levelupacademy.models.alternative;

import br.com.levelupacademy.models.question.Question;
import br.com.levelupacademy.validators.Validations;

public class Alternative {

    private String text;
    private int order;
    private boolean correct;
    private String justification;
    private Question question;

    public Alternative(String text, int order, boolean correct, String justification, Question question) {
        Validations.cantBeEmptyOrNull(text, "Text can't be empty or null");
        this.text = text;
        this.order = order;
        this.correct = correct;
        this.justification = justification;
        Validations.objectValidation(question,"Should be associate with a question");
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public int getOrder() {
        return order;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getJustification() {
        return justification;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "text='" + text + '\'' +
                ", order=" + order +
                ", correct=" + correct +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }
}
