package com.example.demo2;


import javafx.scene.control.*;
import javafx.event.*;
import java.io.IOException;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.application.*;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class Dhinesh extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        GridPane root=new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        Scene scene = new Scene(root,600,600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Course Registration");

        Label lTitle=new Label("KONGU ENGINEERING COLLEGE");


        //Components to Get Student's Details
        Label lName=new Label("Name : ");
        Label lRegNo=new Label(" Roll no : ");
        Label lbranch=new Label("Department : ");
        Label lHobbies=new Label("Your Hobbies");
        Label lRes=new Label();

        TextField txtName=new TextField();
        txtName.setPrefWidth(80);
        TextField txtRegNo=new TextField();
        txtRegNo.setPrefWidth(80);


        RadioButton r1=new RadioButton("CSE");
        RadioButton r2=new RadioButton("IT");
        RadioButton r3=new RadioButton("ECE");
        RadioButton r4=new RadioButton("MECH");
        RadioButton r5=new RadioButton("CIVIL");
        ToggleGroup tg=new ToggleGroup();
        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        r3.setToggleGroup(tg);
        r4.setToggleGroup(tg);
        r5.setToggleGroup(tg);


        CheckBox ck1=new CheckBox("Games");
        CheckBox ck2=new CheckBox("Music");
        CheckBox ck3=new CheckBox("Reading Books");
        CheckBox ck4=new CheckBox("Craft Work");


        //Add 2 buttons to the stage
        Button b1=new Button("Submit");
        Button b2=new Button("Cancel");


        //Adding the Components to the Layout (GridPane)
        root.addColumn(1, lTitle);
        root.addRow(1,lName,txtName);
        root.addRow(2, lRegNo,txtRegNo);
        root.addRow(3, lbranch);
        root.addRow(4,r1,r2,r3,r4,r5);
        root.addRow(5, lHobbies);
        root.addRow(6, ck1,ck2,ck3,ck4);
        root.addRow(7, b1,b2);
        root.addRow(8, lRes);


        //Handle the button click event for Submit button
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String stbranch,stName,stRegNo;
                String stHobbies="Hobbies: ";
                String stRes;
                //Retrieve the data from TextFields
                stName=txtName.getText();
                stRegNo=txtRegNo.getText();

                //Retrieve option selected from RadioButtons
                if(r1.isSelected())
                    stbranch=r1.getText();
                else if(r2.isSelected())
                    stbranch=r2.getText();
                else if(r3.isSelected())
                    stbranch=r3.getText();
                else if(r4.isSelected())
                    stbranch = r4.getText();
                else
                    stbranch=r5.getText();


                //Retrieve the options from CheckBoxes
                if(ck1.isSelected())
                    stHobbies+=ck1.getText()+", ";
                if(ck2.isSelected())
                    stHobbies+=ck2.getText()+", ";
                if(ck3.isSelected())
                    stHobbies+=ck3.getText()+", ";
                if(ck4.isSelected())
                    stHobbies+=ck4.getText();



                stRes="Your Details:\n"+stName+"\n"+stRegNo+"\n"+stbranch+"\n"+stHobbies;
                lRes.setText(stRes);
                lRes.setStyle("-fx-background-color:green");
                lRes.setStyle("-fx-font-color:red");
                root.setStyle("-fx-background-color:pink");

            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lRes.setText("You submission has been cancelled");
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}