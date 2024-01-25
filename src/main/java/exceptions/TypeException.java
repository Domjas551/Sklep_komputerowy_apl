package exceptions;

import javafx.scene.control.Alert;

public class TypeException extends Exception{

    private String message;

    public TypeException(String message) {
        this.message = message;
    }

    public void alert(){
        //wyświetlenie alertu informacyjnego
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
