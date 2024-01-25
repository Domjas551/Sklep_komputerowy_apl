package exceptions;

import javafx.scene.control.Alert;

public class EmptyValueException extends Exception{

    String message;

    public EmptyValueException() {
        this.message ="Żadna z wartości nie może być pusta";
    }

    public void alert(){
        //wyświetlenie alertu informacyjnego
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
