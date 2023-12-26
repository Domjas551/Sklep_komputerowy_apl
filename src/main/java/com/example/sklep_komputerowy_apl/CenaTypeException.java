package com.example.sklep_komputerowy_apl;

import javafx.scene.control.Alert;

public class CenaTypeException extends RuntimeException{
    private String message;

    public CenaTypeException() {
        this.message="Cena może zawierać tylko cyfry, w tym dwie liczby po przecinku oraz ',' lub '.'";
    }

    public void alert(){
        //wyświetlenie alertu informacyjnego
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
