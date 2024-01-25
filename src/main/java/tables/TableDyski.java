package tables;

public class TableDyski {
    private String nazwa;
    private String pojemnosc;
    private double cena;

    public TableDyski(String nazwa, String pojemnosc, double cena) {
        this.nazwa = nazwa;
        this.pojemnosc = pojemnosc;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getPojemnosc() {
        return pojemnosc;
    }

    public double getCena() {
        return cena;
    }
}
