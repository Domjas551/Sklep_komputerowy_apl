package com.example.sklep_komputerowy_apl;

public class TableZamowieniaUzytkownika {
    private String data_zlo;
    private String data_odb;
    private String nazwy;
    private String status;


    public TableZamowieniaUzytkownika(String dataZlozenia, String dataOdbioru, String nazwyProduktow, String status) {
        this.data_zlo=dataZlozenia;
        this.data_odb=dataOdbioru;
        this.nazwy=nazwyProduktow;
        this.status=status;
    }

    public String getData_zlo() {
        return data_zlo;
    }

    public void setData_zlo(String dataZlozenia) {
        this.data_zlo = dataZlozenia;
    }

    public String getData_odb() {
        return data_odb;
    }

    public void setData_odb(String dataOdbioru) {
        this.data_odb = dataOdbioru;
    }

    public String getNazwy() {
        return nazwy;
    }

    public void setNazwy(String nazwyProduktow) {
        this.nazwy = nazwyProduktow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
