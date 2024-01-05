package com.example.sklep_komputerowy_apl;

public class TableTrazam {
    private Integer id;
    private String email;
    private String typ;
    private Integer ilosc;
    private Double cena;

    public TableTrazam(Integer id, String email, String typ, Integer ilosc, Double cena) {
        this.id=id;
        this.email = email;
        this.typ=typ;
        this.ilosc = ilosc;
        this.cena = cena;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTyp() {
        return typ;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public Double getCena() {
        return cena;
    }
}
