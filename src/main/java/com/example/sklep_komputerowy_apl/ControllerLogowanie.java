package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ResourceBundle;

public class ControllerLogowanie implements Initializable {

    ConnectionStorage connection = ConnectionStorage.getInstance();
    DataStorage dane = DataStorage.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    //Inicjalizacja
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private AnchorPane background;

    @FXML
    private Button button_cart;

    @FXML
    private Text button_home;

    @FXML
    private Button button_zaloguj;

    @FXML
    private ImageView image_cart;

    @FXML
    private TextField login_tekst_email;

    @FXML
    private TextField login_tekst_haslo;

    @FXML
    private Text logowanie_text_error;

    @FXML
    void goHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void doRejestracji(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("rejestracja" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zaloguj(MouseEvent event) throws NoSuchAlgorithmException {
        logowanie_text_error.setText("");

        //Zbieranie danych z textboxów
        CharSequence email = login_tekst_email.getCharacters();
        CharSequence haslo = login_tekst_haslo.getCharacters();

        String hasloDB = connection.uzyskajDane("SELECT haslo from Uzytkownicy where email = \"" + email + "\"")[0];

        if (hasloDB.equals("")){
            //Konto nie istnieje
            logowanie_text_error.setText("Niepoprawne dane logowania!");
        }
        //Konto istnieje - sprawdzamy hasło
        else{
            //Funkcja Hashująca na podanym haśle
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(haslo.toString().getBytes());
            byte[] dig_pass = md.digest();
            StringBuilder hexString = new StringBuilder();

            //Konwersja na HEX
            for (byte digPass : dig_pass) {
                hexString.append(Integer.toHexString(0xFF & digPass));
            }

            if (hexString.toString().equals(hasloDB)){ //Hasło poprawne
                String id = connection.uzyskajDane("SELECT id from Uzytkownicy where email = \"" + email + "\"")[0];
                dane.setIdZalogowanegoUzytkownika(id);
            }
            else{ //Błędne hasło
                logowanie_text_error.setText("Niepoprawne dane logowania!");
            }
        }

    }
}
