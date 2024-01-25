package tables;

public class TableZestawy {
    private String nazwa;
    private double cena;

    public TableZestawy(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }
}
