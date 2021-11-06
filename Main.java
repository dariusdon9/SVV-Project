package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import static src.Server.PORT;
import static src.Server.verbose;
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

        VBox close_all = new VBox();
        close_all.setAlignment(Pos.CENTER);
        Scene close_scene = new Scene(close_all,700,500);

        Label WebServerInfo1 = new Label("Web server INFO:");
        Label Server_Status1 = new Label("Server Status:Not Running ");
        Label Server_Adress1 = new Label("Server Adress: Not Running ");
        Label Server_Port1 = new Label("Server Port: Not Running");

        WebServerInfo1.setFont(Font.font(14));
        Server_Status1.setFont(Font.font(14));
        Server_Adress1.setFont(Font.font(14));
        Server_Port1.setFont(Font.font(14));

        Label WebServerConfig1 = new Label("Web Server Configuration:");
        Label ServerListeningOnPort1 = new Label("Server Listening On Port:");
        Label WebRootDirectory1 = new Label("Web Root Directory:");
        Label MaintenanceDirectory1 = new Label("Maintenance Directory:");

        WebServerConfig1.setFont(Font.font(14));
        ServerListeningOnPort1.setFont(Font.font(14));
        WebRootDirectory1.setFont(Font.font(14));
        MaintenanceDirectory1.setFont(Font.font(14));

        root.getText();
        maintain.getText();

        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.TOP_CENTER);
        vbox1.getChildren().addAll(WebServerInfo1,Server_Status1,Server_Adress1,Server_Port1);
        VBox vbox2 = new VBox();

        TextField text1 = new TextField(portText.getText());
        TextField text2 = new TextField();
        text1.setMaxWidth(50);
        TextField text3 = new TextField();
        Label label1 = new Label("Info:");
        label1.setFont(Font.font(14));

        close.setOnAction(e->{
            stage.setScene(close_scene);
            stage.setTitle("Web Server is running!!!");
            Server_Status1.setText(Server_Status.getText());
            Server_Adress1.setText(Server_Adress.getText());
            Server_Port1.setText(Server_Port.getText());
            text1.setText(portText.getText());
            text2.setText(root.getText());
            text3.setText(maintain_text.getText());
            text1.setDisable(true);
            text2.setDisable(true);
        });

        Button back1 = new Button("Back");
        back1.setTextFill(RED);
        back1.setFont(Font.font(14));
        back1.setOnAction(ex->stage.setScene(home_scene));

        Button close_close = new Button("Close");
        close_close.setTextFill(RED);
        close_close.setFont(Font.font(14));

        InputStream stream1 =  new FileInputStream("src/main/java/com/example/webserver/2.jpg");
        Image image1 = new Image(stream1);
        ImageView imageView1= new ImageView();
        imageView1.setImage(image1);
        close_close.setGraphic(imageView1);

        close_close.setOnAction(e->{
            stage.setTitle("Maintenance");
            Server_Status1.setText("Server Status: Ready For Maintenance!");
            System.out.println("Server Closed");
            close_all.setSpacing(100);
            close_all.getChildren().add(back1);
        });
        vbox1.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.getChildren().addAll(close_close);

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(200);
        h1.getChildren().addAll(vbox1,vbox2);
        close_all.setAlignment(Pos.TOP_CENTER);
        close_all.setSpacing(200);
        VBox v3 = new VBox();
        v3.getChildren().addAll(WebServerConfig1,ServerListeningOnPort,WebRootDirectory1,MaintenanceDirectory1);
        VBox v4 = new VBox();
        v4.getChildren().addAll(label1,text1,text2,text3);
        HBox h2 = new HBox();
        h2.getChildren().addAll(v3,v4);
        h2.setSpacing(200);
        h2.setAlignment(Pos.CENTER);
        close_all.getChildren().addAll(h1,h2);

        Label maintain_info = new Label("Info:");
        maintain_info.setFont(Font.font(14));

        TextField maintain1 = new TextField();
        TextField maintain2 = new TextField();
        TextField maintain3 = new TextField();

        maintain1.setDisable(false);
        maintain2.setDisable(false);
        maintain3.setDisable(true);

        VBox maintain_last = new VBox();
        maintain_last.setAlignment(Pos.CENTER);
        maintain_last.getChildren().addAll(maintain_info,maintain1,maintain2,maintain3);

        Label WebServerConfig2= new Label("Web Server Configuration:");
        Label ServerListeningOnPort2 = new Label("Server Listening On Port:");
        Label WebRootDirectory2 = new Label("Web Root Directory:");
        Label MaintenanceDirectory2 = new Label("Maintenance Directory:");

        WebServerConfig2.setFont(Font.font(14));
        ServerListeningOnPort2.setFont(Font.font(14));
        WebRootDirectory2.setFont(Font.font(14));
        MaintenanceDirectory2.setFont(Font.font(14));

        VBox maintain_labels = new VBox();
        maintain_labels.setAlignment(Pos.CENTER);
        maintain_labels.getChildren().addAll(WebServerConfig2,ServerListeningOnPort2,WebRootDirectory2,MaintenanceDirectory2);
        HBox main_bottom = new HBox();
        main_bottom.setAlignment(Pos.CENTER);
        main_bottom.setSpacing(200);
        main_bottom.getChildren().addAll(maintain_labels,maintain_last);
        Label WebServerInfo2 = new Label("Web server INFO:");
        Label Server_Status2 = new Label("Server Status:Not Running ");
        Label Server_Adress2 = new Label("Server Adress: Not Running ");
        Label Server_Port2 = new Label("Server Port: Not Running");

        WebServerInfo2.setFont(Font.font(14));
        Server_Status2.setFont(Font.font(14));
        Server_Adress2.setFont(Font.font(14));
        Server_Port2.setFont(Font.font(14));

        VBox top_main = new VBox();
        top_main.setAlignment(Pos.CENTER);
        top_main.getChildren().addAll(WebServerInfo2,Server_Status2,Server_Adress2,Server_Port2);

        VBox last = new VBox();

        last.getChildren().addAll(top_main,main_bottom);
        last.setAlignment(Pos.CENTER);
        last.setSpacing(100);

        Scene maintenance = new Scene(last,700,500);
        Button back2 = new Button("Back");
        back2.setOnAction(e->stage.setScene(home_scene));
        maintain1.setMaxWidth(50);
        maintain.setOnAction(e->{
            stage.setTitle("Web Server [Maintenance]");
            stage.setScene(maintenance);

            Server_Status2.setText(Server_Status1.getText());
            Server_Adress2.setText(Server_Adress.getText());
            Server_Port2.setText(Server_Adress.getText());
            maintain1.setDisable(true);
           // last.getChildren().addAll(start_server,close_close);
            last.getChildren().add(back);
        });
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
