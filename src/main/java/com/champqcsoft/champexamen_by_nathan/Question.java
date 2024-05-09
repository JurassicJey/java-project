package com.champqcsoft.champexamen_by_nathan;

public class Question {
    private QuestionType questionType;
    private String questionText;
    private String correctAnswer;


    public Question() {
        this.questionType = null;
        this.questionText = "";
        this.correctAnswer = "";
    }

    public Question(QuestionType questionType, String questionText, String correctAnswer) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }


    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionTest(String questionTest) {
        this.questionText = questionTest;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return  "questionType='" + questionType + '\'' +
                ", questionText='" + questionText + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}

