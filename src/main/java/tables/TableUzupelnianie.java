package tables;

public class TableUzupelnianie {
    private String nazwa_produktu;
    private String typ;
    private Integer ilosc;

    public TableUzupelnianie(String nazwa_produktu, String typ, Integer ilosc) {
        this.nazwa_produktu = nazwa_produktu;
        this.typ = typ;
        this.ilosc = ilosc;
    }

    public String getNazwa_produktu() {
        return nazwa_produktu;
    }

    public String getTyp() {
        return typ;
    }

    public Integer getIlosc() {
        return ilosc;
    }
}
