package com.example.sklep_komputerowy_apl;

public class DataStorage {
    private static final DataStorage instance=new DataStorage();

    //zmienna informująca jaka strona jest aktywna
    //Wartośći: plyty, procesory, karty, ram, dyski, zestawy
    private String activePage;
    private String ostatnieZapytaniePlyty="";
    private String ostatnieZapytanieProcesory="";
    private String ostatnieZapytanieKarty="";
    private String ostatnieZapytanieRam="";
    private String ostatnieZapytanieDyski="";
    private String ostatnieZapytanieZestawy="";
    public DataStorage(){
        this.activePage="plyty";
    }

    public static DataStorage getInstance(){
        return instance;
    }

    //zwraca ostatnie zapisane zapytanie
    public String uzyskajPoprzednieZapytanie(){
        if(activePage.equals("plyty")){
            return ostatnieZapytaniePlyty;
        }else if(activePage.equals("procesory")){
            return ostatnieZapytanieProcesory;
        }else if(activePage.equals("karty")){
            return ostatnieZapytanieKarty;
        }else if(activePage.equals("ram")){
            return ostatnieZapytanieRam;
        }else if(activePage.equals("dyski")){
            return ostatnieZapytanieDyski;
        }else if(activePage.equals("zestawy")){
            return ostatnieZapytanieZestawy;
        }
        return "";
    }

    //zapisuje ostatnie wykonane zapytanie
    public void zapiszZapytanie(String zapytanie){
        if(activePage.equals("plyty")){
            ostatnieZapytaniePlyty=zapytanie;
        }else if(activePage.equals("procesory")){
            ostatnieZapytanieProcesory=zapytanie;
        }else if(activePage.equals("karty")){
            ostatnieZapytanieKarty=zapytanie;
        }else if(activePage.equals("ram")){
            ostatnieZapytanieRam=zapytanie;
        }else if(activePage.equals("dyski")){
            ostatnieZapytanieDyski=zapytanie;
        }else if(activePage.equals("zestawy")){
            ostatnieZapytanieZestawy=zapytanie;
        }
    }

    //funkcja do zapisu aktualnie aktywnej strony
    public void zapiszAktualnaStrone(String s){
        this.activePage=s;
    }

    public String getActivePage() {
        return activePage;
    }
}
