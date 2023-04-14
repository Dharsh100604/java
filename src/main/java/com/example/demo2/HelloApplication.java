package com.example.demo2;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.*;
public class HelloApplication extends Application {
    @Override
    public void start(Stage st) throws IOException {
        FlowPane fp = new FlowPane();
        Scene scene = new Scene(fp, 320, 240);
        st.setTitle("Hello!");
        Label l1=new Label("dharsh");
        Label l2=new Label("ganesan");
        Button b1=new Button("true");
        Button b2=new Button("false");
        RadioButton r1=new RadioButton("IT");
        RadioButton r2=new RadioButton("CSE");
        CheckBox c1=new CheckBox("yes");
        CheckBox c2=new CheckBox("No");
        TextField t1=new TextField();
        fp.getChildren().addAll(l1,l2);
        fp.getChildren().add(t1);
        fp.getChildren().addAll(b1,b2,r1,r2);
        fp.getChildren().addAll(c1,c2);
        st.setScene(scene);
        st.show();
    }

    public static void main(String[] args) {
        launch();
    }
}