package com.example.sklep_komputerowy_apl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("uzytkownik.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wyszukiwarka");

        Image icon =new Image(getClass().getResourceAsStream("/images/SISCK_icon1.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}