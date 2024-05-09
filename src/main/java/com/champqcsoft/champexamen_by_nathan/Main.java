package com.champqcsoft.champexamen_by_nathan;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main (String[]args) throws FileNotFoundException {
            Exam exam = new Exam();
            QuestionBank bank = new QuestionBank();
            bank.readMCQ("mcq.txt");
            bank.readTFQ("tfq.txt");
            Random random = new Random();
            int[] tenRandom = new int[10];

            for (int i=0; i<tenRandom.length; i++){
                tenRandom[i]= random.nextInt(65);
                System.out.println(tenRandom[i]);
            }
            LinkedList<Question> list = new LinkedList<>(bank.selectRandQuestions(tenRandom));
            exam.setQuestions(list);



            for (int i=0; i<10; i++){
                System.out.println("Question text: " + exam.getQuestions(i).getQuestionText());
                System.out.println("Coorect answer: " +exam.getQuestions(i).getCorrectAnswer());

            }


    }
}
