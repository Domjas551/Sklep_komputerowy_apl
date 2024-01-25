package tables;

public class TablePlyty {

    private String nazwa;
    private String chipset;
    private double cena;

    public TablePlyty(String nazwa, String chipset, double cena) {
        this.nazwa = nazwa;
        this.chipset = chipset;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getChipset() {
        return chipset;
    }

    public double getCena() {
        return cena;
    }
}
