package tables;
public class TableTransakcjeUzytkownika {
    private String id_transakcji;
    private String data;
    private Double cena;
    private String nazwy;


    public TableTransakcjeUzytkownika(String idTransakcji, String data, String nazwyProduktow, Double cena) {
        this.id_transakcji=idTransakcji;
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

    public String getId_transakcji() {
        return id_transakcji;
    }

    public void setId_transakcji(String id_transakcji) {
        this.id_transakcji = id_transakcji;
    }
}
