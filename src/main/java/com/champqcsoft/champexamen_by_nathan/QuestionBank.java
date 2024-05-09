package com.champqcsoft.champexamen_by_nathan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class QuestionBank {
    protected LinkedList<Question> questions;

    public QuestionBank(){
        this.questions = new LinkedList<>();

    }
    public QuestionBank(LinkedList<Question> q){
        questions = new LinkedList<>();
        this.questions.addAll(q);
    }

    public void clearQuestions(){
        questions.clear();
    }

    public Question getQuestion(int i){
        return questions.get(i);
    }
    public void printAllQuestions(){
        int counter = 1;
        for (Question question : questions){
            System.out.println(counter + ". " + question.toString());
            counter++;
        }
    }

    public void readMCQ(String fname) throws FileNotFoundException {
        File file = new File(fname);
        Scanner reader = new Scanner(file);

        LinkedList<String> options = new LinkedList<>();
            while (reader.hasNext()) {
                String questionText = reader.nextLine().split("[0-9]+\\. ")[1];

                for (int i = 0; i < 4; i++) {
                    options.add(reader.nextLine());
                }
                String correctAnswer = reader.nextLine().split("[a-d]\\. ")[0].trim();
                Question mcq = new MCQQuestion(questionText, correctAnswer, options);
                this.questions.add(mcq);
                reader.nextLine();
                options.clear();
        }
    }
    public void readTFQ(String fname) throws FileNotFoundException {
        File file = new File(fname);
        Scanner reader = new Scanner(file);
        while (reader.hasNext()){
            String questionText = reader.nextLine().split(":")[1];
            String correctAnswer = reader.nextLine().split(" ")[1].trim();
            TFQQuestion tfq = new TFQQuestion(questionText, correctAnswer);
            this.questions.add((tfq));
        }
    }

    public LinkedList<Question> selectRandQuestions(int[] indices){
        LinkedList<Question> list = new LinkedList<>();
        for (int i=0; i<indices.length; i++){
            list.add(this.questions.get(indices[i]));
        }
        return list;
    }

}
