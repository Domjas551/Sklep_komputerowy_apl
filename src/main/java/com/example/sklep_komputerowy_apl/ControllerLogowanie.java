package com.example.sklep_komputerowy_apl;

import exceptions.ConnectionException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ResourceBundle;

public class ControllerLogowanie implements Initializable {

    ConnectionStorage connection = ConnectionStorage.getInstance();
    DataStorage dane = DataStorage.getInstance();
    private int con = 0;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane background;

    @FXML
    private Button button_connect;

    @FXML
    private Text button_home;

    @FXML
    private Button button_zaloguj;

    @FXML
    private Text connection_error_text;

    @FXML
    private TextField login_tekst_email;

    @FXML
    private PasswordField login_tekst_haslo;

    @FXML
    private Text logowanie_text_error;

    //Inicjalizacja
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //zmienna informująca że aktywna strona to strona logowania
        dane.setCzyLogowanie(1);
        try {
            //Pseudo PING - sprawdzanie czy instnieje połączenie z serwerem
            String x = connection.uzyskajDane("Select '1' from uzytkownik where id_uzytkownika=1")[0];
            //zmienna informująca czy nawiązano połączenie z serwerem
            dane.setCzyOffline(0);
        }
        //Brak połączenia z serwerem sprawia że nie da się opuścić strony z logowaniem
        catch (Exception ignored) {
            dane.setCzyOffline(1);
            connection_error_text.setVisible(true);
            button_connect.setVisible(true);
            con = 1;
        }
    }

    //Funkcja do połączenia z serwerem
    public void connect(){
        try{
            int port=6700;
            Socket socket=new Socket("127.0.0.1",port);
            InputStreamReader isr=new InputStreamReader(socket.getInputStream());
            BufferedReader br=new BufferedReader(isr);
            PrintWriter pw= new PrintWriter(socket.getOutputStream(), true);

            connection.setPort(port);
            connection.setSocket(socket);
            connection.setIsr(isr);
            connection.setBr(br);
            connection.setPw(pw);

            connection_error_text.setVisible(false);
            button_connect.setVisible(false);
            this.con = 0;
            dane.setCzyOffline(0);
        }catch(IOException e){
            connection_error_text.setVisible(true);
            button_connect.setVisible(true);
            this.con = 1;
        }
    }

    //Funkcja do sprawdzania połączenia
    public void checkConnection(){
        try {
            String x = connection.uzyskajDane("Select '1' from uzytkownik where id_uzytkownika=1")[0];
        }
        catch (Exception ignored) {
            connection_error_text.setVisible(true);
            button_connect.setVisible(true);
            this.con = 1;
        }
    }

    //Kontynuuj jako gość
    @FXML
    void guestLogin(MouseEvent event) throws IOException {
        checkConnection();
        try {
            if (this.con == 1) {
                throw new ConnectionException();
            }
        } catch (ConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.show();
        }

        if (this.con == 0) {
            dane.setCzyLogowanie(0);
            dane.setIdZalogowanegoUzytkownika("0");
            root = FXMLLoader.load(getClass().getResource("wyszukiwarka" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void doRejestracji(MouseEvent event) throws IOException {
        checkConnection();
        try {
            if (this.con == 1) {
                throw new ConnectionException();
            }
        } catch (ConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.show();
        }

        if (this.con == 0) {
            root = FXMLLoader.load(getClass().getResource("rejestracja" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void zaloguj(MouseEvent event) throws NoSuchAlgorithmException, IOException {
        checkConnection();
        try {
            if (this.con == 1) {
                throw new ConnectionException();
            }
        } catch (ConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString());
            alert.show();
        }

        if (this.con == 0){
            logowanie_text_error.setText("");

            //Zbieranie danych z textboxów
            CharSequence email = login_tekst_email.getCharacters();
            CharSequence haslo = login_tekst_haslo.getCharacters();

            String hasloDB = connection.uzyskajDane("Select haslo from Uzytkownik where email = '" + email + "'")[0];

            if (hasloDB.equals("")){
                //Konto nie istnieje
                logowanie_text_error.setText("Niepoprawne dane logowania!");
            }
            //Konto istnieje - sprawdzamy hasło
            else{
                String aktywne = connection.uzyskajDane("Select czy_aktywny from uzytkownik where email = '" + email + "'")[0];
                if (aktywne.equals("0")){
                    logowanie_text_error.setText("Niepoprawne dane logowania!");
                }
                else {
                    //Funkcja Hashująca na podanym haśle
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(haslo.toString().getBytes());
                    byte[] dig_pass = md.digest();
                    StringBuilder hexString = new StringBuilder();

                    //Konwersja na HEX
                    for (byte digPass : dig_pass) {
                        hexString.append(Integer.toHexString(0xFF & digPass));
                    }

                    if (hexString.toString().equals(hasloDB)) { //Hasło poprawne
                        String id = connection.uzyskajDane("Select id_uzytkownika from Uzytkownik where email = '" + email + "'")[0];
                        dane.setIdZalogowanegoUzytkownika(id);
                        String x = dane.getDestynacjaPowrotuZeStronyLogowania();
                        if (x.equals("")){
                            x = "wyszukiwarka";
                        }
                        dane.setCzyLogowanie(0);
                        root = FXMLLoader.load(getClass().getResource(x + ".fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else { //Błędne hasło
                        logowanie_text_error.setText("Niepoprawne dane logowania!");
                    }
                }
            }
        }
    }
}
