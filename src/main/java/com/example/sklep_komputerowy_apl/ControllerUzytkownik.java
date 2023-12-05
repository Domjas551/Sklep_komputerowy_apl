package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ControllerUzytkownik {

    @FXML
    private AnchorPane background;

    @FXML
    private Button button_anuluj_usuniecie;

    @FXML
    private Button button_cart;

    @FXML
    private Text button_home;

    @FXML
    private Button button_opinie;

    @FXML
    private Button button_profil;

    @FXML
    private Button button_transakcje;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zamowienia;

    @FXML
    private Button button_zatwierdz_usuniecie;

    @FXML
    private Button button_zmienHaslo;

    @FXML
    private ImageView image_cart;

    @FXML
    private ImageView image_user;

    @FXML
    private AnchorPane opinie;

    @FXML
    private AnchorPane profil;

    @FXML
    private AnchorPane transakcje;

    @FXML
    private Button usun_konto;

    @FXML
    private Text value_of_email;

    @FXML
    private Button button_value_of_name;

    @FXML
    private Text value_of_imie;

    @FXML
    private Text value_of_nazwisko;

    @FXML
    private AnchorPane zamowienia;

    @FXML
    private AnchorPane zatwierdzenie_usuniecia;


    @FXML
    void deleteAccount(MouseEvent event) {

    }

    @FXML
    void goHome(MouseEvent event) {
        System.out.println("Went home!");
    }


    @FXML
    void goToCart(MouseEvent event) {

    }

    @FXML
    void showOpinie(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(true);
    }

    @FXML
    void showProfil(MouseEvent event) {
        profil.setVisible(true);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
    }

    @FXML
    void showTransakcje(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(true);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
    }

    @FXML
    void showZamowienia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(true);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
    }


    @FXML
    void showZatwierdzenieUsuniecia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(true);
        opinie.setVisible(false);
    }

    @FXML
    void wyloguj(MouseEvent event) {

    }

    @FXML
    void zmienHaslo(MouseEvent event) {

    }

}
