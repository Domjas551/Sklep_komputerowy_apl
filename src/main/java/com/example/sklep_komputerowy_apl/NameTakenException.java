package com.example.sklep_komputerowy_apl;

import javafx.scene.control.Alert;

public class NameTakenException extends Exception{

    private String message;

    public NameTakenException() {
        this.message = "Produkt o danej nazwie już istnieje";
    }

    public void alert(){
        //wyświetlenie alertu informacyjnego
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
