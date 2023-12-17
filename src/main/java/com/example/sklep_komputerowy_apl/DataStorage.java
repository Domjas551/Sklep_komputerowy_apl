package com.example.sklep_komputerowy_apl;

import java.util.ArrayList;

public class DataStorage {
    private static final DataStorage instance=new DataStorage();

    //zmienna informująca jaka strona jest aktywna
    //Wartośći: plyty, procesory, karty, ram, dyski, zestawy
    private String wyszukiwarka_activePage;
    private String ostatnieZapytaniePlyty="";
    private String ostatnieZapytanieProcesory="";
    private String ostatnieZapytanieKarty="";
    private String ostatnieZapytanieRam="";
    private String ostatnieZapytanieDyski="";
    private String ostatnieZapytanieZestawy="";
    private String ostatnieZapytanieUzupelnijTable="";
    private ArrayList<String> nazwyProduktowUzupelnij=new ArrayList<>();
    private ArrayList<String> typProduktowUzupelnij=new ArrayList<>();
    private ArrayList<Integer> iloscProduktowUzupelnij=new ArrayList<>();

    private String nazwaWybranyProdukt;
    private String typWybranyProdukt;

    public DataStorage(){
        this.wyszukiwarka_activePage ="plyty";
    }

    public static DataStorage getInstance(){
        return instance;
    }

    //zwraca ostatnie zapisane zapytanie
    public String uzyskajPoprzednieZapytanie(){
        if(wyszukiwarka_activePage.equals("plyty")){
            return ostatnieZapytaniePlyty;
        }else if(wyszukiwarka_activePage.equals("procesory")){
            return ostatnieZapytanieProcesory;
        }else if(wyszukiwarka_activePage.equals("karty")){
            return ostatnieZapytanieKarty;
        }else if(wyszukiwarka_activePage.equals("ram")){
            return ostatnieZapytanieRam;
        }else if(wyszukiwarka_activePage.equals("dyski")){
            return ostatnieZapytanieDyski;
        }else if(wyszukiwarka_activePage.equals("zestawy")){
            return ostatnieZapytanieZestawy;
        }
        return "";
    }

    //zapisuje ostatnie wykonane zapytanie
    public void zapiszZapytanie(String zapytanie){
        if(wyszukiwarka_activePage.equals("plyty")){
            ostatnieZapytaniePlyty=zapytanie;
        }else if(wyszukiwarka_activePage.equals("procesory")){
            ostatnieZapytanieProcesory=zapytanie;
        }else if(wyszukiwarka_activePage.equals("karty")){
            ostatnieZapytanieKarty=zapytanie;
        }else if(wyszukiwarka_activePage.equals("ram")){
            ostatnieZapytanieRam=zapytanie;
        }else if(wyszukiwarka_activePage.equals("dyski")){
            ostatnieZapytanieDyski=zapytanie;
        }else if(wyszukiwarka_activePage.equals("zestawy")){
            ostatnieZapytanieZestawy=zapytanie;
        }
    }

    //funkcja do zapisu aktualnie aktywnej strony
    public void zapiszAktualnaStrone(String s){
        this.wyszukiwarka_activePage =s;
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

    public void setNazwyProduktowUzupelnij(ArrayList<String> nazwyProduktowUzupelnij) {
        this.nazwyProduktowUzupelnij = nazwyProduktowUzupelnij;
    }

    public void setTypProduktowUzupelnij(ArrayList<String> typProduktowUzupelnij) {
        this.typProduktowUzupelnij = typProduktowUzupelnij;
    }

    public void setIloscProduktowUzupelnij(ArrayList<Integer> iloscProduktowUzupelnij) {
        this.iloscProduktowUzupelnij = iloscProduktowUzupelnij;
    }

    public ArrayList<String> getNazwyProduktowUzupelnij() {
        return nazwyProduktowUzupelnij;
    }

    public ArrayList<Integer> getIloscProduktowUzupelnij() {
        return iloscProduktowUzupelnij;
    }

    public ArrayList<String> getTypProduktowUzupelnij() {
        return typProduktowUzupelnij;
    }

    public void wybierzProdukt(String nazwa, String typ){
        this.nazwaWybranyProdukt=nazwa;
        this.typWybranyProdukt=typ;
    }

    public String getNazwaWybranyProdukt() {
        return nazwaWybranyProdukt;
    }

    public String getTypWybranyProdukt() {
        return typWybranyProdukt;
    }
}
