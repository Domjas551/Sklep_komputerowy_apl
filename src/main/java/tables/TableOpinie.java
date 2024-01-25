package tables;

public class TableOpinie {
    private String nazwa;
    private Double ocena;
    private String komentarz;

    public TableOpinie(Double ocena, String nazwaProduktu, String tresc) {
        this.nazwa=nazwaProduktu;
        this.ocena=ocena;
        this.komentarz = tresc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwaProduktu) {
        this.nazwa = nazwaProduktu;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(String tresc) {
        this.komentarz = tresc;
    }
}
