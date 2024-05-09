package com.champqcsoft.champexamen_by_nathan;

import com.champqcsoft.champexamen_by_nathan.Question;
import com.champqcsoft.champexamen_by_nathan.QuestionType;

import java.util.LinkedList;

public class MCQQuestion extends Question {
    private LinkedList<String> options;

    public MCQQuestion() {
        super(QuestionType.MCQ,"","");
        this.options = new LinkedList<>();
    }

    public MCQQuestion(LinkedList<String> options) {
        super(QuestionType.MCQ,"","");
        this.options = new LinkedList<>();
        this.options.addAll((options));
    }
    public MCQQuestion(String questionText, String correctAnswer, LinkedList<String> options) {
        super(QuestionType.MCQ,questionText, correctAnswer);
        this.options = new LinkedList<>();
        this.options.addAll((options));
    }

    public LinkedList<String> getOptions() {
        return options;
    }

    public void setOptions(LinkedList<String> options) {
        this.options.addAll(options);
    }
}

