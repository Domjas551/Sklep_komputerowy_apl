package com.example.sklep_komputerowy_apl;

public class CenaTypeException extends RuntimeException{
    private String message;

    public CenaTypeException() {
        this.message="Cena może zawierać tylko cyfry oraz ',' lub '.'";
    }

    public String toString(){
        return message;
    }
}
