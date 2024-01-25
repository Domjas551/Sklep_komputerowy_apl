package tables;

public class TableKarty {
    private String nazwa;
    private String uklad;
    private double cena;

    public TableKarty(String nazwa, String uklad, double cena) {
        this.nazwa = nazwa;
        this.uklad = uklad;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getUklad() {
        return uklad;
    }

    public double getCena() {
        return cena;
    }
}
