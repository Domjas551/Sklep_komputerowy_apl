package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerWyszukiwarka implements Initializable {

    int dana=0;
    //komponenty
    @FXML
    private AnchorPane test;

    @FXML
    private Text testText;

    @FXML
    private ChoiceBox<String> producent;
    //producent.getSelectionModel().getSelectedItem();

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
    private AnchorPane dyski;

    @FXML
    private AnchorPane kartyGraficzne;

    @FXML
    private AnchorPane pamiecRam;

    @FXML
    private AnchorPane plytyGl;

    @FXML
    private AnchorPane procesory;



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
    }

    //funkcja wyświetlająca strone kart graficznych w panelu wyszukiwarki
    @FXML
    void showKarty(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(true);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
    }

    //funkcja wyświetlająca strone pamięci ram w panelu wyszukiwarki
    @FXML
    void showPamiecRam(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(true);
        dyski.setVisible(false);
    }

    //funkcja wyświetlająca strone płyt głównych w panelu wyszukiwarki
    @FXML
    void showPlyty(MouseEvent event) {
        plytyGl.setVisible(true);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
    }

    //funkcja wyświetlająca strone procesorów w panelu wyszukiwarki
    @FXML
    void showProcesory(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(true);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        producent.getItems().addAll("p1","p2");
    }
}