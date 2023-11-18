package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ControllerWyszukiwarka {

    int dana=0;
    @FXML
    private AnchorPane test;

    @FXML
    private Text testText;

    @FXML
    void wypisz(MouseEvent event) {
        testText.setText(Integer.toString(dana));
    }

    @FXML
    void zapisz(MouseEvent event) {
        dana=1;
    }

}