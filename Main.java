package com.example.webserver;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class Main extends Application {


    public void start(Stage stage){
        Button start = new Button();
        start.setText("Start");
        start.setTextAlignment(TextAlignment.CENTER);
        start.setTextFill(GREEN);
        start.setFont(Font.font(14));

        Button close  = new Button();
        close.setText("Close");
        close.setTextFill(RED);
        close.setFont(Font.font(14));

        Button maintain = new Button("Maintain");
        maintain.setText("Maintenance");
        maintain.setFont(Font.font(14));
        maintain.setTextFill(DARKORANGE);

        Label title = new Label("Interfata Grafica");
        title.setAlignment(Pos.CENTER);
        title.setTextFill(BLACK);
        title.setFont(Font.font(20));

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(start,close,maintain);

        VBox central = new VBox();
        central.setAlignment(Pos.CENTER);
        central.getChildren().addAll(title,buttons);

        Scene home_scene = new Scene(central,700,700);
        stage.setScene(home_scene);
        stage.setTitle("Graphic Interface!");
        home_scene.setFill(GRAY);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
