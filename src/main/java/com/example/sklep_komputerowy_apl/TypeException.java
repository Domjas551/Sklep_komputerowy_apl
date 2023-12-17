package com.example.sklep_komputerowy_apl;

public class TypeException extends Exception{

    private String message;

    public TypeException(String message) {
        this.message = message;
    }

    public String toString(){
        return message;
    }
}
