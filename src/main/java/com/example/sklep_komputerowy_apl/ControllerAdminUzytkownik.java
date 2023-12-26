package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControllerAdminUzytkownik implements Initializable {
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    //Wartosci przyjete roboczo
    private String idWybranegoUzytkownika="43";

    //Elementy
    @FXML
    private Text button_home;

    @FXML
    private Button button_usun_konto;

    @FXML
    private Button button_wroc;

    @FXML
    private Button button_zmien_haslo;

    @FXML
    private AnchorPane transakcje;

    @FXML
    private Text value_of_email;

    @FXML
    private Text value_of_imie;

    @FXML
    private Text value_of_nazwisko;

    //-------------------------------------------------------------------------------------
    //Funkcje

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Value_of_name
        String wynik[] = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);
        value_of_imie.setText(wynik[0]);

        //Value_of_nazwisko
        wynik = connection.uzyskajDane("Select nazwisko from Uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);
        value_of_nazwisko.setText(wynik[0]);

        //Value_of_name
        wynik = connection.uzyskajDane("Select email from Uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);
        value_of_email.setText(wynik[0]);
    }
    @FXML
    void ChangePassword(MouseEvent event) {
        //TODO
    }

    @FXML
    void DeleteAccount(MouseEvent event) {
        //TODO
    }

    @FXML
    void goBack(MouseEvent event) {
        //TODO
    }

    @FXML
    void goHome(MouseEvent event) {
        //TODO
    }

}
