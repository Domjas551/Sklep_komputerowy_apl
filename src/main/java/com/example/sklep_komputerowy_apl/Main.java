package com.example.sklep_komputerowy_apl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //podłączenie do klas z komunikacją do BD
    ConnectionStorage connection=ConnectionStorage.getInstance();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logowanie" + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("KomPol@nd");

        connection.setStage(stage);



        //ustawienie ikony aplikacji
        Image icon =new Image(getClass().getResourceAsStream("/images/SISCK_icon1.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}