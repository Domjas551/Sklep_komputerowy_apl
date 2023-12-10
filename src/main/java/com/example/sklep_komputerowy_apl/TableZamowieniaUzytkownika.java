package com.example.sklep_komputerowy_apl;

public class TableZamowieniaUzytkownika {
    private String dataZlozenia;
    private String dataOdbioru;
    private String nazwyProduktow;
    private String status;


    public TableZamowieniaUzytkownika(String dataZlozenia, String dataOdbioru, String nazwyProduktow, String status) {
        this.dataZlozenia=dataZlozenia;
        this.dataOdbioru=dataOdbioru;
        this.nazwyProduktow=nazwyProduktow;
        this.status=status;
    }

    public String getDataZlozenia() {
        return dataZlozenia;
    }

    public void setDataZlozenia(String dataZlozenia) {
        this.dataZlozenia = dataZlozenia;
    }

    public String getDataOdbioru() {
        return dataOdbioru;
    }

    public void setDataOdbioru(String dataOdbioru) {
        this.dataOdbioru = dataOdbioru;
    }

    public String getNazwyProduktow() {
        return nazwyProduktow;
    }

    public void setNazwyProduktow(String nazwyProduktow) {
        this.nazwyProduktow = nazwyProduktow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
