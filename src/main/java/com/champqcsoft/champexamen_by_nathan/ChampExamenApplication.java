package com.champqcsoft.champexamen_by_nathan;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ChampExamenApplication extends Application {
    Exam exam = new Exam();
    Label gradeLabel = new Label("Grade: ");
    VBox root = new VBox(10);
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(10);
        VBox scrollThing = new VBox(10);
        ScrollPane scrollPane = new ScrollPane();
        gradeLabel.setFont(new Font(24));
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
        VBox[] examArray = createExamPage(exam);

        for (int i=0; i < examArray.length; i++){
            scrollThing.getChildren().add(examArray[i]);
        }
        scrollPane.setContent(scrollThing);

        root.getChildren().addAll(buildMenu(), buildTopBanner(), scrollPane, gradeLabel, buildNavigationBar());
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("ChampExam application");
        stage.setScene(scene);
        stage.show();
    }

    public VBox[] createExamPage(Exam exam){
        VBox[] examPage = new VBox[exam.getQuestions().size()];
        int questionNumber = 0;
        for (Question question : exam.getQuestions().values()) {
            if (question.getQuestionType()== QuestionType.TFQ) {
                examPage[questionNumber] = buildTrueFalseQ(questionNumber, (TFQQuestion) question);
            } else if (question.getQuestionType()==QuestionType.MCQ) {
                examPage[questionNumber] = buildMCQ(questionNumber, (MCQQuestion) question);
            }
            questionNumber++;
        }
        return examPage;
    }

    public VBox buildTrueFalseQ(int questionNumber, TFQQuestion tfqQuestion){
        VBox vbox = new VBox();
        Label label = new Label((questionNumber+1) + ") " + tfqQuestion.getQuestionText());
        CheckBox tfCheckBox1 = new CheckBox("True");
        tfCheckBox1.setOnAction(e -> setQuestionAnswer(questionNumber, "true"));
        CheckBox tfCheckBox2 = new CheckBox("False");
        vbox.getChildren().addAll(label, tfCheckBox1,tfCheckBox2);
        tfCheckBox2.setOnAction(e -> setQuestionAnswer(questionNumber, "false"));

        return vbox;
    }


    public VBox buildMCQ (int questionNumber, MCQQuestion mcqQuestion){
        VBox mcq = new VBox();
        Label label = new Label((questionNumber+1) + ") " + mcqQuestion.getQuestionText());

        LinkedList<String> answerChoices = mcqQuestion.getOptions();
        ToggleGroup toggleGroup = new ToggleGroup();
        mcq.getChildren().add(label);
        for (String answer : answerChoices) {
            RadioButton radioButton = new RadioButton(answer);
            radioButton.setOnAction(e -> setQuestionAnswer(questionNumber, String.valueOf(answer.charAt(0))));
            radioButton.setToggleGroup(toggleGroup);
            mcq.getChildren().add(radioButton);
        }

        return mcq;
    }
    public HBox buildNavigationBar(){
        HBox navBar = new HBox();
        Button prevButton = new Button("Clear");
        prevButton.setOnAction(e -> clearExamAnswers());
        Button nextButton = new Button("Save");
        nextButton.setOnAction(e -> saveExamAnswers());
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new SubmitButtonHandler());

        navBar.getChildren().addAll(prevButton, nextButton, submitButton);
        navBar.setAlignment(Pos.CENTER);

        return navBar;
    }

    private void saveExamAnswers() {

    }


    private void clearExamAnswers() {
        exam.getSubmittedAnswers().clear();
    }

    private void setQuestionAnswer(int questionNumber, String answer) {
        this.exam.getSubmittedAnswers().put(questionNumber, answer);
        System.out.println();
        System.out.println("quesiton number: " + questionNumber);
        System.out.println("correct answer: " + exam.getQuestions(questionNumber).getCorrectAnswer());
        System.out.println("submitted answer: " + exam.getSubmittedAnswer(questionNumber));
    }
    public HBox buildTopBanner(){
        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);
        hbox.setMaxHeight(500);
        Image logo = new Image("logo.png");
        Image banner = new Image("banner.png");

        ImageView logoView = new ImageView(logo);
        ImageView bannerView = new ImageView(banner);

        logoView.setPreserveRatio(true);
        bannerView.setPreserveRatio(true);
        logoView.setFitHeight(200);
        bannerView.setFitHeight(200);


        hbox.getChildren().addAll(logoView, bannerView);
        return hbox;
    }
    public MenuBar buildMenu(){
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu quizMenu = new Menu("Quiz");
        Menu extrasMenu = new Menu("Extras");
        Menu helpMenu = new Menu("Help");

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");

        fileMenu.getItems().addAll(newItem, openItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, quizMenu, extrasMenu, helpMenu);
        return menuBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
    class SubmitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int examGrade = 0;
            exam.printAllQuestions();
            System.out.println(exam.getQuestions().size());
            System.out.println("------");
            for (int i=0; i<exam.getSubmittedAnswers().size(); i++){
                Question question = exam.getQuestions(i);
                if (exam.getSubmittedAnswer(i).equals(question.getCorrectAnswer())){
                    examGrade+=1;
                }


            }
            System.out.println("Grade: " + examGrade);
            gradeLabel.setText("Grade: " + examGrade + "/" + exam.getQuestions().size());
        }
    }
}



