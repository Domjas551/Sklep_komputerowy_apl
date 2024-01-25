package tables;

public class TableProcesory {
    private String nazwa;
    private String seria;
    private double cena;

    public TableProcesory(String nazwa, String seria, double cena) {
        this.nazwa = nazwa;
        this.seria = seria;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getSeria() {
        return seria;
    }

    public double getCena() {
        return cena;
    }
}
