package com.example.sklep_komputerowy_apl;
public class TableTransakcjeUzytkownika {
    private String data;
    private Double cena;
    private String nazwy;


    public TableTransakcjeUzytkownika(String data, String nazwyProduktow, Double cena) {
        this.data = data;
        this.nazwy=nazwyProduktow;
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

    public String getNazwy() {
        return nazwy;
    }

    public void setNazwy(String nazwyProduktow) {
        this.nazwy = nazwyProduktow;
    }
}
