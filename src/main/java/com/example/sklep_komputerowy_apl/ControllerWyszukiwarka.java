package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerWyszukiwarka  implements Initializable{

    int dana=0;
    //komponenty
    @FXML
    private AnchorPane test;

    @FXML
    private Text testText;
    //elementy panelu wyszukiwania w zakładce płyty główne
    @FXML
    private TextField min_cena_plyty;
    @FXML
    private TextField max_cena_plyty;
    @FXML
    private CheckBox checkbox_plyty_producent_1;
    @FXML
    private CheckBox checkbox_plyty_producent_2;
    @FXML
    private CheckBox checkbox_plyty_producent_3;
    @FXML
    private CheckBox checkbox_plyty_gniazdo_1;
    @FXML
    private CheckBox checkbox_plyty_gniazdo_2;
    @FXML
    private CheckBox checkbox_plyty_gniazdo_3;
    @FXML
    private CheckBox checkbox_plyty_chipset_1;
    @FXML
    private CheckBox checkbox_plyty_chipset_2;
    @FXML
    private CheckBox checkbox_plyty_chipset_3;
    @FXML
    private CheckBox checkbox_plyty_pamiec_1;
    @FXML
    private CheckBox checkbox_plyty_pamiec_2;
    @FXML
    private CheckBox checkbox_plyty_pamiec_3;
    @FXML
    private CheckBox checkbox_plyty_banki_1;
    @FXML
    private CheckBox checkbox_plyty_banki_2;
    @FXML
    private CheckBox checkbox_plyty_banki_3;
    @FXML
    private CheckBox checkbox_plyty_banki_4;
    @FXML
    private ChoiceBox<String> ttyb_plyty;
    @FXML
    private Button button_szukaj_plyty;

    //elementy panelu wyszukiwania w zakładce procesory
    @FXML
    private TextField min_cena_procesory;
    @FXML
    private TextField max_cena_procesory;
    @FXML
    private CheckBox checkbox_procesory_producent_1;
    @FXML
    private CheckBox checkbox_procesory_producent_2;
    @FXML
    private CheckBox checkbox_procesory_rodzina_1;
    @FXML
    private CheckBox checkbox_procesory_rodzina_2;
    @FXML
    private CheckBox checkbox_procesory_rodzina_3;
    @FXML
    private CheckBox checkbox_procesory_gniazdo_1;
    @FXML
    private CheckBox checkbox_procesory_gniazdo_2;
    @FXML
    private CheckBox checkbox_procesory_rdzenie_1;
    @FXML
    private CheckBox checkbox_procesory_rdzenie_2;
    @FXML
    private CheckBox checkbox_procesory_rdzenie_3;
    @FXML
    private Button button_szukaj_procesory;
    @FXML
    private ChoiceBox<String> ttyb_procesory;

    //elementy panelu wyszukiwania w zakładce karty graficzne
    @FXML
    private TextField min_cena_karty;
    @FXML
    private TextField max_cena_karty;
    @FXML
    private CheckBox checkbox_karty_producent_1;
    @FXML
    private CheckBox checkbox_karty_producent_2;
    @FXML
    private CheckBox checkbox_karty_producent_3;
    @FXML
    private CheckBox checkbox_karty_producent_4;
    @FXML
    private CheckBox checkbox_karty_uklad_1;
    @FXML
    private CheckBox checkbox_karty_uklad_2;
    @FXML
    private CheckBox checkbox_karty_uklad_3;
    @FXML
    private CheckBox checkbox_karty_uklad_4;
    @FXML
    private CheckBox checkbox_karty_zlacze_1;
    @FXML
    private CheckBox checkbox_karty_zlacze_2;
    @FXML
    private CheckBox checkbox_karty_zlacze_3;
    @FXML
    private CheckBox checkbox_karty_rodzaj_1;
    @FXML
    private CheckBox checkbox_karty_rodzaj_2;
    @FXML
    private CheckBox checkbox_karty_rodzaj_3;
    @FXML
    private CheckBox checkbox_karty_rodzaj_4;
    @FXML
    private Button button_szukaj_karty;
    @FXML
    private ChoiceBox<String> ttyb_karty;

    //elementy panelu wyszukiwania w zakładce pamięć RAM
    @FXML
    private TextField min_cena_pamiec;
    @FXML
    private TextField max_cena_pamiec;
    @FXML
    private CheckBox checkbox_pamiec_producent_1;
    @FXML
    private CheckBox checkbox_pamiec_producent_2;
    @FXML
    private CheckBox checkbox_pamiec_producent_3;
    @FXML
    private CheckBox checkbox_pamiec_producent_4;
    @FXML
    private CheckBox checkbox_pamiec_producent_5;
    @FXML
    private CheckBox checkbox_pamiec_rodzaj_1;
    @FXML
    private CheckBox checkbox_pamiec_rodzaj_2;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_1;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_2;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_3;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_4;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_5;
    @FXML
    private ChoiceBox<String> ttyb_pamiec;
    @FXML
    private Button button_szukaj_pamiec;

    //elementy panelu wyszukiwania w zakładce dyski
    @FXML
    private TextField max_cena_dyski;
    @FXML
    private TextField min_cena_dyski;
    @FXML
    private CheckBox checkbox_dyski_producent_1;
    @FXML
    private CheckBox checkbox_dyski_producent_2;
    @FXML
    private CheckBox checkbox_dyski_producent_3;
    @FXML
    private CheckBox checkbox_dyski_typ_1;
    @FXML
    private CheckBox checkbox_dyski_typ_2;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_1;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_2;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_3;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_4;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_5;
    @FXML
    private ChoiceBox<String> ttyb_dyski;
    @FXML
    private Button button_szukaj_dyski;

    //elementy panelu wyszukiwania w zakładce zestawy
    @FXML
    private TextField max_cena_zestawy;
    @FXML
    private TextField min_cena_zestawy;
    @FXML
    private Button button_szukaj_zestawy;

    //główne przyciski
    @FXML
    private Button button_dyski;
    @FXML
    private Button button_kartyGraf;
    @FXML
    private Button button_pamRam;
    @FXML
    private Button button_plyty;
    @FXML
    private Button button_procesory;
    @FXML
    private Button button_zestawy;

    //panele
    @FXML
    private AnchorPane dyski;
    @FXML
    private AnchorPane kartyGraficzne;
    @FXML
    private AnchorPane pamiecRam;
    @FXML
    private AnchorPane plytyGl;
    @FXML
    private AnchorPane procesory;
    @FXML
    private AnchorPane zestawy;



    //funkcje
    @FXML
    void wypisz(MouseEvent event) {
        testText.setText(Integer.toString(dana));
    }

    @FXML
    void zapisz(MouseEvent event) {
        dana=1;
    }

    @FXML
    void pisz1(MouseEvent event) {
        System.out.println("Button1");
    }

    @FXML
    void pisz2(MouseEvent event) {
        System.out.println("Button2");
    }

    //funkcja wyświetlająca strone dysków w panelu wyszukiwarki
    @FXML
    void showDyski(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(true);
        zestawy.setVisible(false);

    }

    //funkcja wyświetlająca strone kart graficznych w panelu wyszukiwarki
    @FXML
    void showKarty(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(true);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(false);
    }

    //funkcja wyświetlająca strone pamięci ram w panelu wyszukiwarki
    @FXML
    void showPamiecRam(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(true);
        dyski.setVisible(false);
        zestawy.setVisible(false);
    }

    //funkcja wyświetlająca strone płyt głównych w panelu wyszukiwarki
    @FXML
    void showPlyty(MouseEvent event) {
        plytyGl.setVisible(true);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(false);
    }

    //funkcja wyświetlająca strone procesorów w panelu wyszukiwarki
    @FXML
    void showProcesory(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(true);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(false);
    }

    //funkcja wyświetlająca strone zestawów w panelu wyszukiwarki
    @FXML
    void showZestawy(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(true);
    }

    //wprowadzenie wartości do choiceBoxów
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ttyb_plyty.getItems().addAll("Zakup","Zamówienie");
        ttyb_procesory.getItems().addAll("Zakup","Zamówienie");
        ttyb_karty.getItems().addAll("Zakup","Zamówienie");
        ttyb_pamiec.getItems().addAll("Zakup","Zamówienie");
        ttyb_dyski.getItems().addAll("Zakup","Zamówienie");
    }

    //odsianie płyt głównych
    @FXML
    void odsiej_plyty(MouseEvent event) {
        min_cena_plyty.getText();
        max_cena_plyty.getText();
        checkbox_plyty_producent_1.isSelected();
        checkbox_plyty_producent_2.isSelected();
        checkbox_plyty_producent_3.isSelected();
        checkbox_plyty_gniazdo_1.isSelected();
        checkbox_plyty_gniazdo_2.isSelected();
        checkbox_plyty_gniazdo_3.isSelected();
        checkbox_plyty_chipset_1.isSelected();
        checkbox_plyty_chipset_2.isSelected();
        checkbox_plyty_chipset_3.isSelected();
        checkbox_plyty_pamiec_1.isSelected();
        checkbox_plyty_pamiec_2.isSelected();
        checkbox_plyty_pamiec_3.isSelected();
        checkbox_plyty_banki_1.isSelected();
        checkbox_plyty_banki_2.isSelected();
        checkbox_plyty_banki_3.isSelected();
        checkbox_plyty_banki_4.isSelected();
        ttyb_plyty.getSelectionModel().getSelectedItem();
    }

    //odsianie procesorów
    @FXML
    void odsiej_procesory(MouseEvent event) {
        min_cena_procesory.getText();
        max_cena_procesory.getText();
        checkbox_procesory_producent_1.isSelected();
        checkbox_procesory_producent_2.isSelected();
        checkbox_procesory_rodzina_1.isSelected();
        checkbox_procesory_rodzina_2.isSelected();
        checkbox_procesory_rodzina_3.isSelected();
        checkbox_procesory_gniazdo_1.isSelected();
        checkbox_procesory_gniazdo_2.isSelected();
        checkbox_procesory_rdzenie_1.isSelected();
        checkbox_procesory_rdzenie_2.isSelected();
        checkbox_procesory_rdzenie_3.isSelected();
        ttyb_procesory.getSelectionModel().getSelectedItem();
    }

    //odsianie kart graficznych
    @FXML
    void odsiej_karty(MouseEvent event) {
        min_cena_karty.getText();
        max_cena_karty.getText();
        checkbox_karty_producent_1.isSelected();
        checkbox_karty_producent_2.isSelected();
        checkbox_karty_producent_3.isSelected();
        checkbox_karty_producent_4.isSelected();
        checkbox_karty_uklad_1.isSelected();
        checkbox_karty_uklad_2.isSelected();
        checkbox_karty_uklad_3.isSelected();
        checkbox_karty_uklad_4.isSelected();
        checkbox_karty_zlacze_1.isSelected();
        checkbox_karty_zlacze_2.isSelected();
        checkbox_karty_zlacze_3.isSelected();
        checkbox_karty_rodzaj_1.isSelected();
        checkbox_karty_rodzaj_2.isSelected();
        checkbox_karty_rodzaj_3.isSelected();
        checkbox_karty_rodzaj_4.isSelected();
        ttyb_karty.getSelectionModel().getSelectedItem();
    }

    //odsianie pamięci RAM
    @FXML
    void odsiej_pamiec(MouseEvent event) {
        min_cena_pamiec.getText();
        max_cena_pamiec.getText();
        checkbox_pamiec_producent_1.isSelected();
        checkbox_pamiec_producent_2.isSelected();
        checkbox_pamiec_producent_3.isSelected();
        checkbox_pamiec_producent_4.isSelected();
        checkbox_pamiec_producent_5.isSelected();
        checkbox_pamiec_rodzaj_1.isSelected();
        checkbox_pamiec_rodzaj_2.isSelected();
        checkbox_pamiec_pojemnosc_1.isSelected();
        checkbox_pamiec_pojemnosc_2.isSelected();
        checkbox_pamiec_pojemnosc_3.isSelected();
        checkbox_pamiec_pojemnosc_4.isSelected();
        checkbox_pamiec_pojemnosc_5.isSelected();
        ttyb_pamiec.getSelectionModel().getSelectedItem();
    }

    //odsianie dysków
    @FXML
    void odsiej_dyski(MouseEvent event) {
        min_cena_dyski.getText();
        max_cena_dyski.getText();
        checkbox_dyski_producent_1.isSelected();
        checkbox_dyski_producent_2.isSelected();
        checkbox_dyski_producent_3.isSelected();
        checkbox_dyski_typ_1.isSelected();
        checkbox_dyski_typ_2.isSelected();
        checkbox_dyski_pojemnosc_1.isSelected();
        checkbox_dyski_pojemnosc_2.isSelected();
        checkbox_dyski_pojemnosc_3.isSelected();
        checkbox_dyski_pojemnosc_4.isSelected();
        checkbox_dyski_pojemnosc_5.isSelected();
        ttyb_dyski.getSelectionModel().getSelectedItem();
    }

    //odsianie zestawów
    @FXML
    void odsiej_zestawy(MouseEvent event) {
        min_cena_zestawy.getText();
        max_cena_zestawy.getText();
    }
}