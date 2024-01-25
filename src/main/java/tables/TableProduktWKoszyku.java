package tables;

public class TableProduktWKoszyku {
    private String nazwa_produktu;
    private String typ;
    private String producent;
    private Double cena;


    public TableProduktWKoszyku(String nazwa_produktu, String typ, String producent, Double cena) {
        this.nazwa_produktu=nazwa_produktu;
        this.typ=typ;
        this.producent=producent;
        this.cena=cena;
    }

    public String getNazwa_produktu() {
        return nazwa_produktu;
    }

    public void setNazwa_produktu(String nazwa_produktu) {
        this.nazwa_produktu = nazwa_produktu;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
