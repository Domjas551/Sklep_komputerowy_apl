package com.example.sklep_komputerowy_apl;

public class TableUzytkownicy {
    private String email;
    private String rabat5;
    private String rabat10;
    private String rabat15;
    private String rabat25;

    public TableUzytkownicy(String email, String rabat5, String rabat10, String rabat15, String rabat25) {
        this.email = email;
        this.rabat5 = rabat5;
        this.rabat10 = rabat10;
        this.rabat15 = rabat15;
        this.rabat25 = rabat25;
    }

    public String getEmail() {
        return email;
    }

    public String getRabat5() {
        return rabat5;
    }

    public String getRabat10() {
        return rabat10;
    }

    public String getRabat15() {
        return rabat15;
    }

    public String getRabat25() {
        return rabat25;
    }
}
