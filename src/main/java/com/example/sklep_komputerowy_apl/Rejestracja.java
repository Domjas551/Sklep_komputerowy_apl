package sk;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Rejestracja implements javafx.fxml.Initializable {

    public Rejestracja(){}

    @FXML
    private TextField text_email;

    @FXML
    private TextField text_haslo;

    @FXML
    private TextField text_haslo_powt;

    @FXML
    private TextField text_imie;

    @FXML
    private TextField text_nazwisko;

    public void zarejestruj() {
        CharSequence imie = text_imie.getCharacters();
        CharSequence nazwisko = text_nazwisko.getCharacters();
        CharSequence email = text_email.getCharacters();
        CharSequence haslo = text_haslo.getCharacters();
        CharSequence haslo_powt = text_haslo_powt.getCharacters();
        int id = (int) ((Math.random() * 9999) + 1000);

        try {
            Pattern pat_imie = Pattern.compile("^[A-Z][a-z]+$");
            Matcher matcher = pat_imie.matcher(imie);

            if (!matcher.find()) {
                throw new BadDataException("imie");
            }

            Pattern pat_naz = Pattern.compile("^[A-Z][a-z]+$");
            matcher = pat_naz.matcher(nazwisko);

            if (!matcher.find()) {
                throw new BadDataException("nazwisko");
            }

            Pattern pat_em = Pattern.compile("^.+@.+[.].+$");
            matcher = pat_em.matcher(email);

            if (!matcher.find()) {
                throw new BadDataException("email");
            }

            if (!haslo.equals(haslo_powt)) {
                throw new BadDataException("haslo_powt");
            }

        } catch (BadDataException ex) {
            //pass
        }
        try {
            System.out.print("Podaj haslo: ");

            Pattern pat_ha = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(){};:<>~?_=+-]).{6,20}$");
            Matcher matcher = pat_ha.matcher(haslo);

            if (haslo.length() < 6 || haslo.length() > 20 || !matcher.find()) {
                throw new BadPasswordException();
            }
        } catch (BadPasswordException ex) {
            //pass
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}