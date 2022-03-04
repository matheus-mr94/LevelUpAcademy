package br.com.levelupacademy.models.question;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;

public class Question extends Activity {

    private String statement;
    private QuestionType questionType = QuestionType.SINGLE_CHOICE;

    public Question(String title, String code, int sequence, Section section, String statement) {
        super(title, code, sequence, section);
        cantBeEmptyOrNull(statement, "The statement can't be empty or null");
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
