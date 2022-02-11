package br.com.levelupacademy.models.question;

import br.com.levelupacademy.enums.AnswerType;
import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.validators.Validations;

public class Question extends Activity {

    private String statement;
    private AnswerType answerType = AnswerType.ONLY_ANSWER;

    public Question(String title, String code, int order, Section section, String statement) {
        super(title, code, order, section);
        Validations.cantBeEmptyOrNull(statement, "The statement can't be empty or null");
        this.statement = statement;
    }

    public Question(String title, String code, int order, Section section, String statement, AnswerType answerType) {
        super(title, code, order, section);
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
