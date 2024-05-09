package com.champqcsoft.champexamen_by_nathan;

import java.util.LinkedList;

public class TFQQuestion extends Question{
    public TFQQuestion() {
        super(QuestionType.TFQ, "", "");
    }

    public TFQQuestion(String questionText, String correctAnswer){
        super(QuestionType.TFQ, questionText, correctAnswer);
    }
}