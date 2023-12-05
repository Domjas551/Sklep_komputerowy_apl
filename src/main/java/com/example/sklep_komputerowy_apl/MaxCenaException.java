package com.example.sklep_komputerowy_apl;

public class MaxCenaException extends RuntimeException{
    private String message;

    public MaxCenaException() {
        this.message ="Max cena nie może być mniejsza od ceny minimalnej";
    }

    public String toString(){
        return message;
    }
}
