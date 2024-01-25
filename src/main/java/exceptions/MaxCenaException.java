package exceptions;

import javafx.scene.control.Alert;

public class MaxCenaException extends RuntimeException{
    private String message;

    public MaxCenaException() {
        this.message ="Max cena nie może być mniejsza od ceny minimalnej";
    }

    public void alert(){
        //wyświetlenie alertu informacyjnego
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
