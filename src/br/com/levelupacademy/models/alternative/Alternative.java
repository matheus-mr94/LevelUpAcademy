package br.com.levelupacademy.models.alternative;

import br.com.levelupacademy.models.question.Question;

public class Alternative {

    private String text;
    private int order;
    private boolean correct;
    private String justification;
    private Question question;

    public Alternative(String text, int order, boolean correct, String justification, Question question) {
        this.text = text;
        this.order = order;
        this.correct = correct;
        this.justification = justification;
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
}
