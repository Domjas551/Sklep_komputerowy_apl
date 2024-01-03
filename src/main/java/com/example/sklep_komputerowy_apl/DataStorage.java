package com.example.sklep_komputerowy_apl;

import java.util.ArrayList;
import java.util.Arrays;

public class DataStorage {
    private static final DataStorage instance = new DataStorage();

    //zmienna informująca jaka strona jest aktywna
    //Wartośći: plyty, procesory, karty, ram, dyski, zestawy
    private String wyszukiwarka_activePage;
    private String ostatnieZapytaniePlyty = "";
    private String ostatnieZapytanieProcesory = "";
    private String ostatnieZapytanieKarty = "";
    private String ostatnieZapytanieRam = "";
    private String ostatnieZapytanieDyski = "";
    private String ostatnieZapytanieZestawy = "";
    //ostatnie zapytanie do tabeli uzupełnień komponentów
    private String ostatnieZapytanieUzupelnijTable = "";

    //ostatnie zapytanie do tabeli transakcji-zamówień
    private String ostatnieZapytanieTableTrazam = "";

    //ostatnie zapyranie do tabeli użytkowników
    private String ostatnieZapytanieTableUzytkownicy = "";

    //Id aktualnie zalogowanego uzytkownika. Jesli wynosi "0", oznacza to, ze uzytkownik nie jest zalogowany
    private String idZalogowanegoUzytkownika = "1";
    //Uzywane przez admina do wskazywanego wybranej przez niego osoby w ramach zarządzania
    private String idWybranegoUzytkownika = "2";
    //Id wybranego aktualnie produktu
    private String idWybranegoProduktu = "150";
    //Uzywany do powrotu ze strony logowania do przerwanej czynnosci
    private String destynacjaPowrotuZeStronyLogowania;
    //private ArrayList<String> idProduktowWKoszyku = new ArrayList<>(Arrays.asList("1", "1", "1")); //kategoria, typ_produktu, liczba egzemplarzy
    private ArrayList<String> idProduktowWKoszyku = new ArrayList<>();
    //private ArrayList<String> idZestawowWKoszyku = new ArrayList<>(Arrays.asList("1", "1")); // Typ zestawu, liczba egzemplarzy
    private ArrayList<String> idZestawowWKoszyku = new ArrayList<>();


    //strona zestawów
    private String idWybranegoZestawu = "1";

    public DataStorage() {
        this.wyszukiwarka_activePage = "plyty";
    }

    public static DataStorage getInstance() {
        return instance;
    }

    //zwraca ostatnie zapisane zapytanie
    public String uzyskajPoprzednieZapytanie(String s) {
        if (s.equals("plyty")) {
            return ostatnieZapytaniePlyty;
        } else if (s.equals("procesory")) {
            return ostatnieZapytanieProcesory;
        } else if (s.equals("karty")) {
            return ostatnieZapytanieKarty;
        } else if (s.equals("ram")) {
            return ostatnieZapytanieRam;
        } else if (s.equals("dyski")) {
            return ostatnieZapytanieDyski;
        } else if (s.equals("zestawy")) {
            return ostatnieZapytanieZestawy;
        }
        return "";
    }

    //zapisuje ostatnie wykonane zapytanie
    public void zapiszZapytanie(String zapytanie) {
        if (wyszukiwarka_activePage.equals("plyty")) {
            ostatnieZapytaniePlyty = zapytanie;
        } else if (wyszukiwarka_activePage.equals("procesory")) {
            ostatnieZapytanieProcesory = zapytanie;
        } else if (wyszukiwarka_activePage.equals("karty")) {
            ostatnieZapytanieKarty = zapytanie;
        } else if (wyszukiwarka_activePage.equals("ram")) {
            ostatnieZapytanieRam = zapytanie;
        } else if (wyszukiwarka_activePage.equals("dyski")) {
            ostatnieZapytanieDyski = zapytanie;
        } else if (wyszukiwarka_activePage.equals("zestawy")) {
            ostatnieZapytanieZestawy = zapytanie;
        }
    }

    //funkcja do zapisu aktualnie aktywnej strony
    public void zapiszAktualnaStrone(String s) {
        this.wyszukiwarka_activePage = s;
    }

    public String getWyszukiwarka_activePage() {
        return wyszukiwarka_activePage;
    }

    public String getOstatnieZapytanieUzupelnijTable() {
        return ostatnieZapytanieUzupelnijTable;
    }

    public void setOstatnieZapytanieUzupelnijTable(String ostatnieZapytanieUzupelnijTable) {
        this.ostatnieZapytanieUzupelnijTable = ostatnieZapytanieUzupelnijTable;
    }

    public String getOstatnieZapytanieTableTrazam() {
        return ostatnieZapytanieTableTrazam;
    }

    public void setOstatnieZapytanieTableTrazam(String ostatnieZapytanieTableTrazam) {
        this.ostatnieZapytanieTableTrazam = ostatnieZapytanieTableTrazam;
    }

    public String getOstatnieZapytanieTableUzytkownicy() {
        return ostatnieZapytanieTableUzytkownicy;
    }

    public void setOstatnieZapytanieTableUzytkownicy(String ostatnieZapytanieTableUzytkownicy) {
        this.ostatnieZapytanieTableUzytkownicy = ostatnieZapytanieTableUzytkownicy;
    }

    public String getIdZalogowanegoUzytkownika() {
        return idZalogowanegoUzytkownika;
    }

    public void setIdZalogowanegoUzytkownika(String idZalogowanegoUzytkownika) {
        this.idZalogowanegoUzytkownika = idZalogowanegoUzytkownika;
    }

    public String getIdWybranegoUzytkownika() {
        return idWybranegoUzytkownika;
    }

    public void setIdWybranegoUzytkownika(String idWybranegoUzytkownika) {
        this.idWybranegoUzytkownika = idWybranegoUzytkownika;
    }

    public ArrayList<String> getIdProduktowWKoszyku() {
        return idProduktowWKoszyku;
    }

    public void setIdProduktowWKoszyku(ArrayList<String> idProduktowWKoszyku) {
        this.idProduktowWKoszyku = idProduktowWKoszyku;
    }

    public String getIdWybranegoProduktu() {
        return idWybranegoProduktu;
    }

    public void setIdWybranegoProduktu(String idWybranegoProduktu) {
        this.idWybranegoProduktu = idWybranegoProduktu;
    }

    public String getIdWybranegoZestawu() {
        return idWybranegoZestawu;
    }

    public void setIdWybranegoZestawu(String idWybranegoZestawu) {
        this.idWybranegoZestawu = idWybranegoZestawu;
    }

    public ArrayList<String> getIdZestawowWKoszyku() {
        return idZestawowWKoszyku;
    }

    public void setIdZestawowWKoszyku(ArrayList<String> idZestawowWKoszyku) {
        this.idZestawowWKoszyku = idZestawowWKoszyku;
    }

    public String getDestynacjaPowrotuZeStronyLogowania() {
        return destynacjaPowrotuZeStronyLogowania;
    }

    public void setDestynacjaPowrotuZeStronyLogowania(String destynacjaPowrotuZeStronyLogowania) {
        this.destynacjaPowrotuZeStronyLogowania = destynacjaPowrotuZeStronyLogowania;
    }
}
