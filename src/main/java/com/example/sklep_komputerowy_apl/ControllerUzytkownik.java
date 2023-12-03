package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ControllerUzytkownik {

    @FXML
    private AnchorPane background;

    @FXML
    private Text button_home;

    @FXML
    private Button button_profil;

    @FXML
    private Button button_transakcje;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zamowienia;

    @FXML
    private Button button_zmienHaslo;

    @FXML
    private AnchorPane transakcje;

    @FXML
    private AnchorPane zamowienia;

    @FXML
    private AnchorPane profil;

    @FXML
    private Text value_of_email;

    @FXML
    private Text value_of_imie;

    @FXML
    private Text value_of_nazwisko;

    @FXML
    void goHome(MouseEvent event) {
        System.out.println("Went home!");
    }

    @FXML
    void showProfil(MouseEvent event) {
        profil.setVisible(true);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
    }

    @FXML
    void showTransakcje(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(true);
        zamowienia.setVisible(false);
    }

    @FXML
    void showZamowienia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(true);
    }

    @FXML
    void wyloguj(MouseEvent event) {

    }

    @FXML
    void zmienHaslo(MouseEvent event) {

    }

}
