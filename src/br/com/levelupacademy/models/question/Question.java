package br.com.levelupacademy.models.question;

import br.com.levelupacademy.enums.AnswerType;
import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

public class Question extends Activity {

    private String statement;
    private AnswerType answerType;

    public Question(String title, String code, Section section, String statement, AnswerType answerType) {
        super(title, code, section);
        this.statement = statement;
        this.answerType = answerType;
    }

    public String getStatement() {
        return statement;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }
}
