package com.example.sklep_komputerowy_apl;

public class TableOpinieUzytkownika {
    private String data;
    private String nazwaProduktu;
    private Double ocena;
    private String tresc;

    public TableOpinieUzytkownika(String data, String nazwaProduktu, Double ocena, String tresc) {
        this.data = data;
        this.nazwaProduktu=nazwaProduktu;
        this.ocena=ocena;
        this.tresc = tresc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
