package com.example.sklep_komputerowy_apl;
public class TableTransakcjeUzytkownika {
    private String data;
    private Double cena;
    private String nazwyProduktow;


    public TableTransakcjeUzytkownika(String data, String nazwyProduktow, Double cena) {
        this.data = data;
        this.nazwyProduktow=nazwyProduktow;
        this.cena=cena;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNazwyProduktow() {
        return nazwyProduktow;
    }

    public void setNazwyProduktow(String nazwyProduktow) {
        this.nazwyProduktow = nazwyProduktow;
    }
}
