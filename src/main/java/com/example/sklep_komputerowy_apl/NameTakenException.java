package com.example.sklep_komputerowy_apl;

public class NameTakenException extends Exception{

    private String message;

    public NameTakenException() {
        this.message = "Produkt o danej nazwie już istnieje";
    }

    public String toString(){
        return message;
    }
}
