package com.example.webserver;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static javafx.scene.paint.Color.*;

public class Main extends Application {


    public void start(Stage stage) throws FileNotFoundException {
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
        VBox first_start_box = new VBox();
        first_start_box.setAlignment(Pos.TOP_CENTER);
        Button start_server = new Button();
        start_server.setTextFill(GREEN);
        start_server.setFont(Font.font(14));
        start_server.setText("Start");

        Label WebServerInfo = new Label("Web server INFO:");
        Label Server_Status = new Label("Server Status:Not Running ");
        Label Server_Adress = new Label("Server Adress: Not Running ");
        Label Server_Port = new Label("Server Port: Not Running");
        WebServerInfo.setFont(Font.font(14));
        Server_Status.setFont(Font.font(14));
        Server_Adress.setFont(Font.font(14));
        Server_Port.setFont(Font.font(14));

        VBox labels = new VBox();
        labels.getChildren().addAll(WebServerInfo,Server_Status,Server_Adress,Server_Port);
        labels.setAlignment(Pos.TOP_CENTER);

        HBox serverandstart = new HBox();
        serverandstart.getChildren().addAll(labels,start_server);
        serverandstart.setAlignment(Pos.CENTER);
        serverandstart.setSpacing(200);

        VBox second_start_box = new VBox();
        second_start_box.setAlignment(Pos.CENTER);
        VBox start_all = new VBox();

        start_all.setAlignment(Pos.CENTER);
        start_all.setSpacing(50);

        first_start_box.getChildren().add(serverandstart);

        InputStream stream =  new FileInputStream("src/main/java/com/example/webserver/1.jpg");
        Image image = new Image(stream);
        ImageView imageView= new ImageView();
        imageView.setImage(image);
        start_server.setGraphic(imageView);


        TextField portText = new TextField();
        portText.setMaxWidth(50);

        Label WebServerConfig = new Label("Web Server Configuration:");
        Label ServerListeningOnPort = new Label("Server Listening On Port:");
        Label WebRootDirectory = new Label("Web Root Directory:");
        Label MaintenanceDirectory = new Label("Maintenance Directory:");


        WebServerConfig.setFont(Font.font(14));
        ServerListeningOnPort.setFont(Font.font(14));
        WebRootDirectory.setFont(Font.font(14));
        MaintenanceDirectory.setFont(Font.font(14));

        TextField root = new TextField();
        TextField maintain_text = new TextField();

        Label type = new Label("Type Info:");
        type.setFont(Font.font(14));

        VBox texts = new VBox();
        texts.getChildren().addAll(type,portText,root,maintain_text);

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(200);
        bottom.getChildren().addAll(second_start_box,texts);
        second_start_box.getChildren().addAll(WebServerConfig,ServerListeningOnPort,WebRootDirectory,MaintenanceDirectory);

        Button back = new Button("Back");
        back.setFont(Font.font(14));
        back.setTextFill(RED);

        start_all.getChildren().addAll(first_start_box,bottom);
        start_server.setOnAction(e->{
            portText.getText();
            stage.setTitle("Web Server is runnig!!!");
            Server_Status.setText("Server Status: running...");
            Server_Adress.setText("Server Adress:" + "127.0.0.1");
            Server_Port.setText("Server Listening Port:" + portText.getText());
            if(root.getText().equals("src/main/java/root")){
                System.out.println("Correct root!");
            }
            else {
                System.out.println("Try another one!");
            }
            if(maintain_text.getText().equals("src/main/java/maintain")){
                System.out.println("Correct Maintain Folder");
            }
            else
                System.out.println("Try another one!");
            start_all.getChildren().add(back);
        });
        Scene start_scene = new Scene(start_all,700,500);
        start.setOnAction(e->{
            stage.setScene(start_scene);
            stage.setTitle("StartWindow");
        });
        back.setOnAction(e-> stage.setScene(home_scene));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
