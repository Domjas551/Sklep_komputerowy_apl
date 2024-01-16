package com.example.sklep_komputerowy_apl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.regex.*;
import java.security.MessageDigest;

public class ControllerRejestracja {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    @FXML
    private AnchorPane background;

    @FXML
    private CheckBox rejestracja_pokaz_haslo_CB;

    @FXML
    private TextField rejestracja_haslo_plain;

    @FXML
    private TextField rejestracja_powtorz_plain;

    @FXML
    private Button button_cart;

    @FXML
    private Text button_home;

    @FXML
    private Button button_zaloguj;

    @FXML
    private Button button_zarejestruj;

    @FXML
    private ImageView image_cart;

    @FXML
    private Text rejestracja_imie_label;

    @FXML
    private Text rejestracja_email_label;

    @FXML
    private Text rejestracja_haslo_label;

    @FXML
    private Text rejestracja_nazwisko_label;

    @FXML
    private Text rejestracja_powtorz_haslo_label;

    @FXML
    private TextField rejestracja_tekst_email;

    @FXML
    private TextField rejestracja_tekst_haslo;

    @FXML
    private TextField rejestracja_tekst_imie;

    @FXML
    private TextField rejestracja_tekst_nazwisko;

    @FXML
    private TextField rejestracja_tekst_powtorzhaslo;

    @FXML
    private Text rejestracja_text_error;

    @FXML
    private AnchorPane wyloguj;

    @FXML
    private AnchorPane zaloguj;

    @FXML
    void goHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void doLogowania(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void rejestracja(MouseEvent event) throws NoSuchAlgorithmException, IOException {

        //Do dodawania danych do DB
        int err = 0;

        //Ustawienia okienka z tekstem błędów na puste
        rejestracja_text_error.setText("");

        //Ustwianie kolorów na domyślne
        rejestracja_email_label.setFill(Paint.valueOf("BLACK"));
        rejestracja_imie_label.setFill(Paint.valueOf("BLACK"));
        rejestracja_nazwisko_label.setFill(Paint.valueOf("BLACK"));
        rejestracja_powtorz_haslo_label.setFill(Paint.valueOf("BLACK"));
        rejestracja_haslo_label.setFill(Paint.valueOf("BLACK"));

        //Zbieranie tekstu z TextBoxów
        CharSequence imie = rejestracja_tekst_imie.getCharacters();
        CharSequence nazwisko = rejestracja_tekst_nazwisko.getCharacters();
        CharSequence email = rejestracja_tekst_email.getCharacters();
        CharSequence haslo = rejestracja_tekst_haslo.getCharacters();
        CharSequence haslo_powt = rejestracja_tekst_powtorzhaslo.getCharacters();

        //Jeżeli hasło jest widzialne pobieramy je z innego text boxa
        if (rejestracja_pokaz_haslo_CB.isSelected()){
            haslo = rejestracja_haslo_plain.getCharacters();
            haslo_powt = rejestracja_powtorz_plain.getCharacters();
        }

        //Sprawdzanie czy email jest już zarejestrowany
        try {
            int emailIstnieje = Integer.parseInt(connection.uzyskajDane("Select id_uzytkownika from Uzytkownik where email = '" + email+"'")[0]);
            if (emailIstnieje != 0) {
                throw new BadDataException("emailIstnieje");
            }
        }
        //Jest zarejstrowany
        catch (BadDataException e){
            err = 1;
            rejestracja_email_label.setFill(Paint.valueOf("RED"));
            rejestracja_text_error.setText("Na ten email zostało już założone konto!");
        }
        //Nie jest zarejestrowany (parseInt wywala error bo String jest pusty więc nie ma konta z takim mailem, ignorujemy go)
        catch (Exception ignored){

        }

        //Email + imie + nazwisko + powtórz hasło wyrzucają ten sam typ błedu (BadDataException), łapany tylko 1 na raz
        try {

            //Obsługa emaila - format taki sam dla całej reszty danych oprócz hasła
            Pattern pat_em = Pattern.compile("^.+@.+[.].+$"); //Ustawianie REGEX
            Matcher matcher = pat_em.matcher(email); //Sprawdzanie czy tekst ma poprawny format

            if (!matcher.find()) { //Jak nie to error
                throw new BadDataException("email");
            } //Jak tak to tekst na czane jakby był wcześniej zmieniany

            //Obsługa imie
            Pattern pat_imie = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
            matcher = pat_imie.matcher(imie);

            if (!matcher.find()) {
                throw new BadDataException("imie");
            }

            //Obsługa nazwisko
            Pattern pat_naz = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
            matcher = pat_naz.matcher(nazwisko);

            if (!matcher.find()) {
                throw new BadDataException("nazwisko");
            }

            //Obsługa powtórzenia hasła
            if (!haslo_powt.toString().equals(haslo.toString())) {
                throw new BadDataException("haslo_powt");
            }

        } catch (BadDataException ex) {
            err = 1; //Jest błąd, nie wysyłamy do DB
            switch (ex.getField()) {
                case "email" -> {
                    rejestracja_email_label.setFill(Paint.valueOf("RED")); //Test na czerwono żeby było ładnie
                    rejestracja_text_error.setText("Podano błędne dane w polu email!"); //Ustawiamy komunikat
                }
                case "nazwisko" -> {
                    rejestracja_nazwisko_label.setFill(Paint.valueOf("RED"));
                    rejestracja_text_error.setText("Podano błędne dane w polu nazwisko!");
                }
                case "imie" -> {
                    rejestracja_imie_label.setFill(Paint.valueOf("RED"));
                    rejestracja_text_error.setText("Podano błędne dane w polu imie!");
                }
                case "haslo_powt" -> {
                    rejestracja_powtorz_haslo_label.setFill(Paint.valueOf("RED"));
                    rejestracja_text_error.setText("Podane hasła nie są takie same!");
                }
            }
        }
        try {
            //REGEX dla hasła
            Pattern pat_ha = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{6,20}");
            Matcher matcher = pat_ha.matcher(haslo);

            if (!matcher.find()) {
                throw new BadPasswordException();
            }

        } catch (BadPasswordException ex) {
            err = 1;
            rejestracja_haslo_label.setFill(Paint.valueOf("RED"));
            rejestracja_text_error.setText("Hasło musi zawierać od 6 do 20 znaków w tym co najmniej 1 małą i dużą literę, cyfrę i znak specjalny!");
        }


        if (err == 0) {

            //Blokowanie aby zapobiec duplikacji id
            connection.lock();

            //Nadawanie nowego id użytkownikowi na zasadzie następnego dostępnego id
            String[] maxId = connection.uzyskajDane("Select max(id_uzytkownika) from Uzytkownik");
            Integer id = Integer.parseInt(maxId[0])+1;

            //Szyfrowanie używając MessageDigest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(haslo.toString().getBytes());
            byte[] dig_pass = md.digest();

            //Konwersja na HEX
            StringBuilder hexString = new StringBuilder();
            for (byte digPass : dig_pass) {
                hexString.append(Integer.toHexString(0xFF & digPass));
            }

            //Wprowadzanie do bazy
            connection.wprowadzDaneBezAlert("INSERT INTO uzytkownik values ("+ id +", '"+ email +"','"+imie+"','"+nazwisko+"','"+ hexString +"',0,0,1)");
            dane.setIdZalogowanegoUzytkownika(String.valueOf(id));

            connection.wprowadzDaneBezAlert("INSERT INTO uzytkownik_rabat values("+1+", "+id+",0)");
            connection.wprowadzDaneBezAlert("INSERT INTO uzytkownik_rabat values("+2+", "+id+",0)");
            connection.wprowadzDaneBezAlert("INSERT INTO uzytkownik_rabat values("+3+", "+id+",0)");
            connection.wprowadzDaneBezAlert("INSERT INTO uzytkownik_rabat values("+4+", "+id+",0)");

            //Odblokowanie
            connection.unlock();

            root = FXMLLoader.load(getClass().getResource("wyszukiwarka" + ".fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void pokazHaslo(ActionEvent event) {

        // Podmienienie hasła z kropek na widzialne
        if (rejestracja_pokaz_haslo_CB.isSelected()){
            rejestracja_haslo_plain.setText(rejestracja_tekst_haslo.getText());
            rejestracja_tekst_haslo.setVisible(false);
            rejestracja_haslo_plain.setVisible(true);

            rejestracja_powtorz_plain.setText(rejestracja_tekst_powtorzhaslo.getText());
            rejestracja_tekst_powtorzhaslo.setVisible(false);
            rejestracja_powtorz_plain.setVisible(true);
        }
        // Podmienienie hasła z widzialnego na kropki
        else {
            rejestracja_tekst_haslo.setText(rejestracja_haslo_plain.getText());
            rejestracja_tekst_haslo.setVisible(true);
            rejestracja_haslo_plain.setVisible(false);

            rejestracja_tekst_powtorzhaslo.setText(rejestracja_powtorz_plain.getText());
            rejestracja_tekst_powtorzhaslo.setVisible(true);
            rejestracja_powtorz_plain.setVisible(false);
        }
    }
}
