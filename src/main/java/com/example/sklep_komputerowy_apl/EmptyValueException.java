package com.example.sklep_komputerowy_apl;

public class EmptyValueException extends Exception{

    String message;

    public EmptyValueException() {
        this.message ="Żadna z wartości nie może być pusta";
    }

    public String toString(){
        return message;
    }
}
