package com.champqcsoft.champexamen_by_nathan;

import java.util.HashMap;
import java.util.LinkedList;

public class Exam {
    private HashMap<Integer, Question> questions;
    private HashMap<Integer, String> submittedAnswers;

    public Exam() {
        questions = new HashMap<>();
        submittedAnswers = new HashMap<>();
    }

    public Exam(HashMap<Integer, Question> questions, HashMap<Integer, String> submittedAnswers) {
        this.questions = new HashMap<>();
        this.submittedAnswers = new HashMap<>();

        this.questions.putAll(questions);
        this.submittedAnswers.putAll(submittedAnswers);
    }

    public void setQuestions(LinkedList<Question> qList) {
        int counter = 0;
        for (Question question: qList){
            this.questions.put(counter, question);
            counter++;
        }
    }

    public void setSubmittedAnswers(HashMap<Integer, String> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }

    public Exam(LinkedList<Question> qList) {
        this.questions = new HashMap<>();
        int counter = 1;
        for (Question question: qList){
            this.questions.put(counter, question);
            counter++;
        }
    }

    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    public HashMap<Integer, String> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void clearQuestions() {
        this.submittedAnswers.clear();
    }

    public Question getQuestions(int i){
        return this.questions.get(i);
    }
    public String getSubmittedAnswer(int i){
        return this.submittedAnswers.get(i);
    }

    public void printAllQuestions(){
        for (HashMap.Entry<Integer, Question> set :
                this.questions.entrySet()) {

            System.out.println(set.getKey() + " = "
                    + set.getValue());
        }
    }
    private void setQuestionAnswer(int questionNumber, String answer) {
        submittedAnswers.put(questionNumber, answer);
    }


}
