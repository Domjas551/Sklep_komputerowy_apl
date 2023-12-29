package com.example.sklep_komputerowy_apl;

public class TableReklamacje {
    private String nazwa;
    private String rek;

    public TableReklamacje(String nazwa, String rek) {
        this.nazwa = nazwa;
        this.rek = rek;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getRek() {
        return rek;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setRek(String rek) {
        this.rek = rek;
    }
}
