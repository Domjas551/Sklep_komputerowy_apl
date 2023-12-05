package com.example.sklep_komputerowy_apl;

public class TableRam {
    private String nazwa;
    private String rodzaj;
    private double cena;

    public TableRam(String nazwa, String rodzaj, double cena) {
        this.nazwa = nazwa;
        this.rodzaj = rodzaj;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public double getCena() {
        return cena;
    }
}
