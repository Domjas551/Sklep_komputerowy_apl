package com.example.sklep_komputerowy_apl;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerAdmin implements Initializable {

    //podłączenie do klas z danymi oraz komunikacją z BD
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;

    //komponenty
    @FXML
    private Button button_szukaj;
    @FXML
    private Button button_szukaj_trazam;
    @FXML
    private Button button_trazam_odrzuc;
    @FXML
    private Button button_trazam_zatrwierdz;
    @FXML
    private Button button_uzupelnij;
    @FXML
    private Button button_uzupelnij_wszystko;
    @FXML
    private ChoiceBox<String> choicebox_typ_statystyk;
    @FXML
    private ChoiceBox<String> choicebox_st_uz;
    @FXML
    private ChoiceBox<String> choicebox_st_prod;
    @FXML
    private ChoiceBox<String> f_typ_produktu;
    @FXML
    private TextArea fp_opis;
    @FXML
    private TextField fp_wysokosc;
    @FXML
    private Button fd_button_dodaj;
    @FXML
    private TextField fd_cena;
    @FXML
    private TextField fd_glebokosc;
    @FXML
    private TextField fd_nazwa;
    @FXML
    private TextArea fd_opis;
    @FXML
    private ChoiceBox<String> fd_pojemnosc;
    @FXML
    private ChoiceBox<String> fd_producent;
    @FXML
    private TextField fd_szerokosc;
    @FXML
    private ChoiceBox<String> fd_typ;
    @FXML
    private TextField fd_wysokosc;
    @FXML
    private Button fk_button_dodaj;
    @FXML
    private TextField fk_cena;
    @FXML
    private TextField fk_glebokosc;
    @FXML
    private TextField fk_moc;
    @FXML
    private TextField fk_nazwa;
    @FXML
    private TextArea fk_opis;
    @FXML
    private TextField fk_pojemnosc_pam;
    @FXML
    private ChoiceBox<String> fk_producent;
    @FXML
    private ChoiceBox<String> fk_rodzaj_pam;
    @FXML
    private TextField fk_szerokosc;
    @FXML
    private TextField fk_taktowanie;
    @FXML
    private ChoiceBox<String> fk_uklad;
    @FXML
    private TextField fk_wysokosc;
    @FXML
    private ChoiceBox<String> fk_zlacze;
    @FXML
    private ChoiceBox<String> fp_banki;
    @FXML
    private Button fp_button_dodaj;
    @FXML
    private TextField fp_cena;
    @FXML
    private ChoiceBox<String> fp_chipset;
    @FXML
    private ChoiceBox<String> fp_gniazdo;
    @FXML
    private TextField fp_max_pam;
    @FXML
    private TextField fp_nazwa;
    @FXML
    private ChoiceBox<String> fp_producent;
    @FXML
    private TextField fp_szerokosc;
    @FXML
    private ChoiceBox<String> fp_typ;
    @FXML
    private Button fpr_button_dodaj;
    @FXML
    private TextField fpr_cena;
    @FXML
    private ChoiceBox<String> fpr_gniazdo;
    @FXML
    private ChoiceBox<String> fpr_liczba_rdzeni;
    @FXML
    private TextField fpr_moc;
    @FXML
    private TextField fpr_nazwa;
    @FXML
    private TextArea fpr_opis;
    @FXML
    private ChoiceBox<String> fpr_producent;
    @FXML
    private ChoiceBox<String> fpr_rodzina;
    @FXML
    private TextField fpr_taktowanie;
    @FXML
    private TextField fpr_watki;
    @FXML
    private Button fr_button_dodaj;
    @FXML
    private TextField fr_cena;
    @FXML
    private TextField fr_napiecie;
    @FXML
    private TextField fr_nazwa;
    @FXML
    private TextArea fr_opis;
    @FXML
    private ChoiceBox<String> fr_pamiec;
    @FXML
    private ChoiceBox<String> fr_pojemnosc;
    @FXML
    private ChoiceBox<String> fr_producent;
    @FXML
    private TextField fr_taktowanie;
    @FXML
    private Button fz_button_dodaj;
    @FXML
    private TextField fz_cena;
    @FXML
    private ChoiceBox<String> fz_dysk;
    @FXML
    private ChoiceBox<String> fz_karta;
    @FXML
    private ChoiceBox<String> fz_pamiec;
    @FXML
    private ChoiceBox<String> fz_plyta;
    @FXML
    private ChoiceBox<String> fz_procesor;
    @FXML
    private TextField fz_nazwa;
    @FXML
    private AnchorPane magazyn;
    @FXML
    private ChoiceBox<String> magazyn_akcja;
    @FXML
    private AnchorPane opcje_dodania;
    @FXML
    private ChoiceBox<String> scrollbar_typ_rabatu;
    @FXML
    private ScrollPane scrollpane_form_dyski;
    @FXML
    private ScrollPane scrollpane_form_karty;
    @FXML
    private ScrollPane scrollpane_form_pamiec;
    @FXML
    private ScrollPane scrollpane_form_plyty;
    @FXML
    private ScrollPane scrollpane_form_procesory;
    @FXML
    private ScrollPane scrollpane_form_zestawy;
    @FXML
    private ScrollPane scrollpane_table;
    @FXML
    private AnchorPane statystyki;
    @FXML
    private TextField szukaj_nazwa;
    @FXML
    private TableView<TableTrazam> table_tra_zam;
    @FXML
    private TableView<TableUzytkownicy> table_uzytkownicy;
    @FXML
    private TextField textarea_trazam_email;
    @FXML
    private TextField textarea_trazam_id;
    @FXML
    private TextField textfield_email;
    @FXML
    private ChoiceBox<String> trazam_typ;
    @FXML
    private TableColumn<TableTrazam, Double> ttz_cena;
    @FXML
    private TableColumn<TableTrazam, String> ttz_email;
    @FXML
    private TableColumn<TableTrazam, Integer> ttz_id;
    @FXML
    private TableColumn<TableTrazam, String> ttz_typ;
    @FXML
    private TableColumn<TableTrazam, Integer> ttz_ilosc;
    @FXML
    private TableColumn<TableUzytkownicy, String> tu_email;
    @FXML
    private TableColumn<TableUzytkownicy, String> tu_r10;
    @FXML
    private TableColumn<TableUzytkownicy, String> tu_r15;
    @FXML
    private TableColumn<TableUzytkownicy, String> tu_r25;
    @FXML
    private TableColumn<TableUzytkownicy, String> tu_r5;
    @FXML
    private AnchorPane uzupelnianie;
    @FXML
    private TextField uzupelnij_ilosc;
    @FXML
    private AnchorPane uzytkownicy;
    @FXML
    private AnchorPane zamawianie_transakcje;
    @FXML
    private TableView<TableUzupelnianie> table_uzupelnianie;
    @FXML
    private TableColumn<TableUzupelnianie, Integer> tuz_ilosc;
    @FXML
    private TableColumn<TableUzupelnianie, String> tuz_nazwa;
    @FXML
    private TableColumn<TableUzupelnianie, String> tuz_typ;
    @FXML
    private AnchorPane st_pie_produkty;
    @FXML
    private AnchorPane st_chart_uzOba;
    @FXML
    private AnchorPane st_chart_uzTran;
    @FXML
    private AnchorPane st_chart_uzZam;
    @FXML
    private AnchorPane st_chart_plyty;
    @FXML
    private AnchorPane st_chart_procesory;
    @FXML
    private AnchorPane st_chart_karty;
    @FXML
    private AnchorPane st_chart_pamiec;
    @FXML
    private AnchorPane st_chart_dyski;
    @FXML
    private PieChart pie_produkty;
    @FXML
    private BarChart<String,Double> bar_user_zakupy;
    @FXML
    private BarChart<String,Double> bar_user_tran;
    @FXML
    private BarChart<String,Double> bar_user_zam;
    @FXML
    private BarChart<String,Double> bar_plyty;
    @FXML
    private BarChart<String,Double> bar_procesory;
    @FXML
    private BarChart<String,Double> bar_karty;
    @FXML
    private BarChart<String,Double> bar_pamiec;
    @FXML
    private BarChart<String,Double> bar_dyski;
    @FXML
    private Button button_value_of_name;

    //dane
    private String nazwy_plyty[];
    private String nazwy_procesory[];
    private String nazwy_karty[];
    private String nazwy_pamiec[];
    private String nazwy_dyski[];
    private String nazwy_zestawy[];

    private String email="";
    private String r5;
    private String r10;
    private String r15;
    private String r25;

    private ArrayList<String> nazwyProduktowUzupelnij=new ArrayList<>();
    private ArrayList<String> typProduktowUzupelnij=new ArrayList<>();
    private ArrayList<Integer> iloscProduktowUzupelnij=new ArrayList<>();

    private String nazwaWybranyProdukt="";
    private String typWybranyProdukt;

    private String trazamId="";
    private String trazamTyp;

    //wprowadzenie wartości do tabel
    //tabela uzupełnień
    public void odswiezTableUzupelnij(){
        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableUzupelnianie> tuz_list= FXCollections.observableArrayList();

        //wypełnienie danymi z BD
        if(dane.getOstatnieZapytanieUzupelnijTable().equals("")){

            String wynik[]= connection.uzyskajDane("Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
                    "union "+
                    "Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, 0 as ilosc from produkt "+
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
                    "where (id_transakcji is not null or id_zamowienia is not null) " +
                    "and id_zestawu is null and plyta_glowna.nazwa_produktu not in (" +
                    "Select nazwa_produktu from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu))" +
                    " group by nazwa_produktu" +
                    " union " +
                    "select procesor.nazwa_produktu, 'Procesor' as typ, count(*) as ilosc from produkt " +
                    "join procesor on produkt.id_procesora=procesor.id_procesora " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
                    "union "+
                    "select procesor.nazwa_produktu, 'Procesor' as typ, 0 as ilosc from produkt " +
                    "join procesor on produkt.id_procesora=procesor.id_procesora " +
                    "where (id_transakcji is  not null or id_zamowienia is null) " +
                    "and id_zestawu is null and procesor.nazwa_produktu not in ("+
                    "select nazwa_produktu from (select procesor.nazwa_produktu, 'Procesor' as typ, count(*) as ilosc from produkt " +
                    "join procesor on produkt.id_procesora=procesor.id_procesora " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu)) "+
                    "group by nazwa_produktu " +
                    "union " +
                    "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, count(*) as ilosc from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
                    "union "+
                    "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, 0 as ilosc from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
                    "where (id_transakcji is not null or id_zamowienia is not null) " +
                    "and id_zestawu is null and karta_graficzna.nazwa_produktu not in (" +
                    "select nazwa_produktu from (" +
                    "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, count(*) as ilosc from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu ))"+
                    "group by nazwa_produktu " +
                    "union " +
                    "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, count(*) as ilosc from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
                    "union "+
                    "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, 0 as ilosc from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
                    "where (id_transakcji is not null or id_zamowienia is not null)" +
                    "and id_zestawu is null and pamiec_ram.nazwa_produktu not in (" +
                    "select nazwa_produktu from ("+
                    "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, count(*) as ilosc from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu ))"+
                    "group by nazwa_produktu "+
                    "union " +
                    "select dysk.nazwa_produktu, 'Dysk' as typ, count(*) as ilosc from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
                    "union "+
                    "select dysk.nazwa_produktu, 'Dysk' as typ, 0 as ilosc from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku " +
                    "where (id_transakcji is not null or id_zamowienia is not null) " +
                    "and id_zestawu is null and dysk.nazwa_produktu not in (" +
                    "select nazwa_produktu from ("+
                    "select dysk.nazwa_produktu, 'Dysk' as typ, count(*) as ilosc from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku " +
                    "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu)) "+
                    "group by nazwa_produktu " +
                    "union "+
                    "Select zestaw.nazwa_zestawu, 'Zestaw' as typ, count(*)/5 as ilosc "+
                    "from produkt join zestaw on produkt.id_typu_zestawu=zestaw.id_zestawu "+
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_zestawu "+
                    "union "+
                    "Select zestaw.nazwa_zestawu, 'Zestaw' as typ, 0 as ilosc "+
                    "from produkt join zestaw on produkt.id_typu_zestawu=zestaw.id_zestawu "+
                    "where (id_transakcji is not null or id_zamowienia is not null) and zestaw.nazwa_zestawu not in "+
                    "(Select nazwa_zestawu from (Select zestaw.nazwa_zestawu, 'Zestaw' as typ, count(*)/5 as ilosc "+
                    "from produkt join zestaw on produkt.id_typu_zestawu=zestaw.id_zestawu "+
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_zestawu)) group by nazwa_zestawu "+
                    ") order by ilosc asc");

            //wyczyszczenie tablic przed ponownym uzupełnieniem
            nazwyProduktowUzupelnij.clear();
            typProduktowUzupelnij.clear();
            iloscProduktowUzupelnij.clear();

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników

                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");
                }

                tuz_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                    nazwyProduktowUzupelnij.add(wynik[i]);
                    typProduktowUzupelnij.add(wynik[i+1]);
                    iloscProduktowUzupelnij.add(Integer.parseInt(wynik[i+2]));
                }
            }

            table_uzupelnianie.setItems(tuz_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieUzupelnijTable());
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników

                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");
                    tuz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                }
            }

            table_uzupelnianie.setItems(tuz_list);
        }
    }

    //tabela transakcji-zamówień
    public void uzupelnijTableTrazam(){

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableTrazam> ttz_list= FXCollections.observableArrayList();

        //wypełnienie danymi z BD
        if(dane.getOstatnieZapytanieTableTrazam().equals("")){
            String wynik[]= connection.uzyskajDane("Select id_transakcji as id, email,'T' as typ, " +
                    "(Select count(*) from produkt where id_transakcji=t.id_transakcji) as ilosc, cena_calkowita from transakcja t " +
                    "join uzytkownik on t.id_uzytkownika=uzytkownik.id_uzytkownika  where status='oczekująca'" +
                    "union " +
                    "Select id_zamowienia as id, email,'Z' as typ, (Select count(*) from produkt where id_zamowienia=z.id_zamowienia) as ilosc, cena_calkowita from zamowienie z " +
                    "join uzytkownik on z.id_uzytkownika=uzytkownik.id_uzytkownika where status_odbioru='oczekujace'");

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żadnych wyników

                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    ttz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=5){
                    ttz_list.add(new TableTrazam(Integer.parseInt(wynik[i]),wynik[i+1],wynik[i+2],Integer.parseInt(wynik[i+3]),Double.parseDouble(wynik[i+4])));
                }
            }

            table_tra_zam.setItems(ttz_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieTableTrazam());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    ttz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=5){
                    ttz_list.add(new TableTrazam(Integer.parseInt(wynik[i]),wynik[i+1],wynik[i+2],Integer.parseInt(wynik[i+3]),Double.parseDouble(wynik[i+4])));
                }
            }

            table_tra_zam.setItems(ttz_list);
        }
    }

    //tabela użtkowników i rabatów
    public void uzupelnijTableUzytkownicy(){
        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableUzytkownicy> tu_list= FXCollections.observableArrayList();

        //wypełnienie danymi z BD
        if(dane.getOstatnieZapytanieTableUzytkownicy().equals("")){
            String wynik[]= connection.uzyskajDane("Select email, " +
                    "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=1 and czy_wazny=1) then 'P' else 'N' end as \"0.05\", " +
                "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=2 and czy_wazny=1) then 'P' else 'N' end as \"0.1\", " +
                "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=3 and czy_wazny=1) then 'P' else 'N' end as \"0.15\", " +
                "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=4 and czy_wazny=1) then 'P' else 'N' end as \"0.25\" from uzytkownik u " +
                    "where czy_aktywny=1");

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tu_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=5){
                    tu_list.add(new TableUzytkownicy(wynik[i],wynik[i+1],wynik[i+2],wynik[i+3],wynik[i+4]));
                }
            }

            table_uzytkownicy.setItems(tu_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieTableUzytkownicy());
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tu_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=5){
                    tu_list.add(new TableUzytkownicy(wynik[i],wynik[i+1],wynik[i+2],wynik[i+3],wynik[i+4]));
                }
            }

            table_uzytkownicy.setItems(tu_list);
        }
    }

    //inicializacja strony admina
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ustawienie wartości w choiceboxach
        magazyn_akcja.getItems().addAll("Uzupełnij","Dodaj");
        f_typ_produktu.getItems().addAll("Płyta główna","Procesor","Karta graficzna","Pamięć RAM","Dysk","Zestaw");
        trazam_typ.getItems().addAll("Transakcja","Zamówienie","Oba");
        scrollbar_typ_rabatu.getItems().addAll("5%","10%","15%","25%");
        choicebox_typ_statystyk.getItems().addAll("Top 5 Użytkownicy", "Produkty", "Top 5 produkty");
        choicebox_st_uz.getItems().addAll("Transakcje","Zamówienia","Oba");
        choicebox_st_prod.getItems().addAll("Płyty główne","Procesory","Karty graficzne","Pamięci RAM","Dyski");

        //ustawienie domyślnej wartości
        magazyn_akcja.setValue("Uzupełnij");
        f_typ_produktu.setValue("Płyta główna");
        scrollbar_typ_rabatu.setValue("5%");
        choicebox_typ_statystyk.setValue("Produkty");
        choicebox_st_uz.setValue("Oba");
        choicebox_st_prod.setValue("Płyty główne");
        //ustawienie wykonywanych funkcji przy zmianie wartości w choiceboxach
        magazyn_akcja.setOnAction(actionEvent -> formZmienStrone());
        f_typ_produktu.setOnAction(actionEvent -> formZmienStroneForm());
        choicebox_typ_statystyk.setOnAction(actionEvent -> pokazStatystykiI());
        choicebox_st_uz.setOnAction(actionEvent -> pokazStatystykiII());
        choicebox_st_prod.setOnAction(actionEvent -> pokazStatystykiIII());

        //pobranie nazw istniejących produktów
        nazwy_plyty=connection.uzyskajDane("Select nazwa_produktu from PLYTA_GLOWNA");
        nazwy_procesory=connection.uzyskajDane("Select nazwa_produktu from PROCESOR");
        nazwy_karty=connection.uzyskajDane("Select nazwa_produktu from KARTA_GRAFICZNA");
        nazwy_pamiec=connection.uzyskajDane("Select nazwa_produktu from PAMIEC_RAM");
        nazwy_dyski=connection.uzyskajDane("Select nazwa_produktu from DYSK");
        nazwy_zestawy=connection.uzyskajDane("Select nazwa_zestawu from ZESTAW");

        //wstawienie wartości do choiceboxów w formularzu
        //zmienne pomocnicze
        String s1[];
        //Płyty główne
        s1=connection.uzyskajDane("Select distinct producent from PLYTA_GLOWNA");
        fp_producent.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct gniazdo_procesora from PLYTA_GLOWNA");
        fp_gniazdo.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct chipset from PLYTA_GLOWNA");
        fp_chipset.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct typ_obslugiwanej_pamieci from PLYTA_GLOWNA");
        fp_typ.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct liczba_bankow_pamieci from PLYTA_GLOWNA order by liczba_bankow_pamieci asc");
        fp_banki.getItems().addAll(s1);

        //procesory
        s1=connection.uzyskajDane("Select distinct producent from PROCESOR");
        fpr_producent.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct rodzina from PROCESOR");
        fpr_rodzina.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct gniazdo_procesora from PROCESOR");
        fpr_gniazdo.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct liczba_rdzeni from PROCESOR order by liczba_rdzeni asc");
        fpr_liczba_rdzeni.getItems().addAll(s1);

        //karta graficzna
        s1=connection.uzyskajDane("Select distinct producent from KARTA_GRAFICZNA");
        fk_producent.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct uklad_graficzny from KARTA_GRAFICZNA");
        fk_uklad.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct rodzaj_zlacza from KARTA_GRAFICZNA");
        fk_zlacze.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct rodzaj_pamieci from KARTA_GRAFICZNA");
        fk_rodzaj_pam.getItems().addAll(s1);

        //pamiec ram
        s1=connection.uzyskajDane("Select distinct producent from PAMIEC_RAM");
        fr_producent.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct rodzaj_pamieci from PAMIEC_RAM");
        fr_pamiec.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct pojemnosc from PAMIEC_RAM order by pojemnosc asc");
        fr_pojemnosc.getItems().addAll(s1);

        //dysk
        s1=connection.uzyskajDane("Select distinct producent from DYSK");
        fd_producent.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct typ_dysku from DYSK");
        fd_typ.getItems().addAll(s1);

        s1=connection.uzyskajDane("Select distinct pojemnosc from DYSK order by pojemnosc asc");
        fd_pojemnosc.getItems().addAll(s1);

        //zestaw
        fz_plyta.getItems().addAll(nazwy_plyty);
        fz_procesor.getItems().addAll(nazwy_procesory);
        fz_karta.getItems().addAll(nazwy_karty);
        fz_pamiec.getItems().addAll(nazwy_pamiec);
        fz_dysk.getItems().addAll(nazwy_dyski);

        //ustawienie tabel

        //tabela uzupełniania
        tuz_nazwa.setCellValueFactory(new PropertyValueFactory<TableUzupelnianie,String>("nazwa_produktu"));
        tuz_typ.setCellValueFactory(new PropertyValueFactory<TableUzupelnianie,String>("typ"));
        tuz_ilosc.setCellValueFactory(new PropertyValueFactory<TableUzupelnianie,Integer>("ilosc"));

        //tabela trazam
        ttz_id.setCellValueFactory(new PropertyValueFactory<TableTrazam,Integer>("id"));
        ttz_email.setCellValueFactory(new PropertyValueFactory<TableTrazam,String>("email"));
        ttz_typ.setCellValueFactory(new PropertyValueFactory<TableTrazam,String>("typ"));
        ttz_ilosc.setCellValueFactory(new PropertyValueFactory<TableTrazam,Integer>("ilosc"));
        ttz_cena.setCellValueFactory(new PropertyValueFactory<TableTrazam,Double>("cena"));

        //tabela uzytkowników i rabatów
        tu_email.setCellValueFactory(new PropertyValueFactory<TableUzytkownicy,String>("email"));
        tu_r5.setCellValueFactory(new PropertyValueFactory<TableUzytkownicy,String>("rabat5"));
        tu_r10.setCellValueFactory(new PropertyValueFactory<TableUzytkownicy,String>("rabat10"));
        tu_r15.setCellValueFactory(new PropertyValueFactory<TableUzytkownicy,String>("rabat15"));
        tu_r25.setCellValueFactory(new PropertyValueFactory<TableUzytkownicy,String>("rabat25"));

        //wstawienie wartości do tabel

        //tabela uzupełniania komponentów
        odswiezTableUzupelnij();

        //tabela transakcji-zamówień
        uzupelnijTableTrazam();

        //tabela użytkowników
        uzupelnijTableUzytkownicy();

        //statystyki

        //wykres produktów

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<PieChart.Data> pieU_list= FXCollections.observableArrayList();

        //wypełnienie danymi z BD

        String wynik[]= connection.uzyskajDane("Select 'Płyta główna' as typ, count(*) as ilosc from produkt where id_plyty_glownej is not null and "+
            "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
            "union "+
            "select 'Procesor' as typ, count(*) as ilosc from produkt where id_procesora is not null and "+
            "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
            "union "+
            "select 'Karta graficzna' as typ, count(*) as ilosc from produkt where id_karty_graficznej is not null and "+
            "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
            "union "+
            "select 'Pamięć RAM' as typ, count(*) as ilosc from produkt where id_pamieci_ram is not null and "+
            "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
            "union "+
            "select 'Dysk' as typ, count(*) as ilosc from produkt where id_dysku is not null and "+
            "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane'))");

        if(wynik.length<1){
            //gdy zapytanie nie zwróciło żądnych wyników
            if(dane.getCzyOffline()==0){
                //wyświetlenie alertu informacyjnego
                informationAlert("Brak danych do wyświetlenia");
                pieU_list.clear();
            }

        }else{
            for(int i=0;i<wynik.length;i+=2){
                pieU_list.add(new PieChart.Data(wynik[i],Integer.parseInt(wynik[i+1])));
                //seria1.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
            }
        }

        //dodanie napisu do legendy
        pieU_list.forEach(data -> data.nameProperty().bind(
                Bindings.concat(data.getName()," liczba: ",data.getPieValue())
        ));

        pie_produkty.getData().addAll(pieU_list);

        //wykres użytkowników ogólny

        //utworzenie list do wypełniania odpowiednich typów
        XYChart.Series seria1=new XYChart.Series<>();
        seria1.setName("Zakupy");

        //wypełnienie danymi z BD

        wynik= connection.uzyskajDane("Select id_uzytkownika, count(id) as ilosc from " +
                "(select uzytkownik.id_uzytkownika, id_zamowienia as id from uzytkownik join zamowienie on uzytkownik.id_uzytkownika=zamowienie.id_uzytkownika where status_odbioru!='oczekujace'"+
                "union "+
                "select uzytkownik.id_uzytkownika, id_transakcji as id from uzytkownik join transakcja on uzytkownik.id_uzytkownika=transakcja.id_uzytkownika where status!='oczekująca') " +
                "group by id_uzytkownika order by ilosc desc fetch first 5 rows only");


        if(wynik.length<1){
            //gdy zapytanie nie zwróciło żądnych wyników
            if(dane.getCzyOffline()==0){
                //wyświetlenie alertu informacyjnego
                informationAlert("Brak danych do wyświetlenia");
            }

        }else{
            for(int i=0;i<wynik.length;i+=2){
                //pieU_list.add(new PieChart.Data(wynik[i],Double.parseDouble(wynik[i+1])));
                seria1.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
            }
        }

        bar_user_zakupy.getData().addAll(seria1);

        //wykres produktów: płyty główne

        //utworzenie list do wypełniania odpowiednich typów
        XYChart.Series seria2=new XYChart.Series<>();
        seria2.setName("Ilość wybrań");

        //wypełnienie danymi z BD

        wynik= connection.uzyskajDane("Select id_plyty_glownej, count(id_produktu) as id from produkt " +
                "where id_plyty_glownej is not null and (id_zamowienia is not null or id_transakcji is not null) " +
                "group by id_plyty_glownej order by id desc fetch first 5 rows only");

        if(wynik.length<1){
            //gdy zapytanie nie zwróciło żądnych wyników
            if(dane.getCzyOffline()==0){
                //wyświetlenie alertu informacyjnego
                informationAlert("Brak danych do wyświetlenia");
            }

        }else{
            for(int i=0;i<wynik.length;i+=2){
                //pieU_list.add(new PieChart.Data(wynik[i],Double.parseDouble(wynik[i+1])));
                seria2.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
            }
        }

        bar_plyty.getData().addAll(seria2);

        //wyświetlenie nazwy użytkownika
        wynik = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + dane.getIdZalogowanegoUzytkownika());
        button_value_of_name.setText(wynik[0]);

    };

    //wyświetlanie alertów
    public void informationAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(m);
        alert.showAndWait();
    }

    public void errorAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(m);
        alert.showAndWait();
    }

    //funkcja do wyświetlenia strony statystyk
    @FXML
    void showStatystyki(MouseEvent event) {

        statystyki.setVisible(true);
        uzytkownicy.setVisible(false);
        zamawianie_transakcje.setVisible(false);
        magazyn.setVisible(false);

    }

    //funkcja do wyświetlenia strony zamówień/transakcji
    @FXML
    void showZamtrans(MouseEvent event) {
        statystyki.setVisible(false);
        uzytkownicy.setVisible(false);
        zamawianie_transakcje.setVisible(true);
        magazyn.setVisible(false);

    }

    //funkcja do wyświetlenia strony użytkowników
    @FXML
    void showUzytkownicy(MouseEvent event) {
        statystyki.setVisible(false);
        uzytkownicy.setVisible(true);
        zamawianie_transakcje.setVisible(false);
        magazyn.setVisible(false);

    }

    //funkcja do wyświetlenia strony magazynu
    @FXML
    void showMagazyn(MouseEvent event) {
        statystyki.setVisible(false);
        uzytkownicy.setVisible(false);
        zamawianie_transakcje.setVisible(false);
        magazyn.setVisible(true);

    }

    //zmiana strony magazynu
    @FXML
    void formZmienStrone(){

        String wybor=magazyn_akcja.getSelectionModel().getSelectedItem();

        if(wybor.equals("Uzupełnij")){

            //wypełnienie danymi z BD
            if(dane.getOstatnieZapytanieUzupelnijTable().equals("")){

                 odswiezTableUzupelnij();
            }else{
                //danymi z uprzednio wykonanego zapytania
                String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieUzupelnijTable());

                //utworzenie list do wypełniania odpowiednich typów
                ObservableList<TableUzupelnianie> tuz_list= FXCollections.observableArrayList();

                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjengo
                        informationAlert("Brak danych do wyświetlenia");
                        tuz_list.clear();
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=3){
                        tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                    }
                }

                table_uzupelnianie.setItems(tuz_list);
            }

            scrollpane_table.setVisible(true);
            scrollpane_form_plyty.setVisible(false);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
            uzupelnianie.setVisible(true);
            opcje_dodania.setVisible(false);
        }else if(wybor.equals("Dodaj")){
            formZmienStroneForm();
            uzupelnianie.setVisible(false);
            opcje_dodania.setVisible(true);
        }
    }

    //zmiana strony formularza dodawania elementów
    @FXML
    void formZmienStroneForm(){
        String wybor=f_typ_produktu.getSelectionModel().getSelectedItem();

        if(wybor.equals("Płyta główna")){
            scrollpane_form_plyty.setVisible(true);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
        }else if(wybor.equals("Procesor")){
            scrollpane_form_plyty.setVisible(false);
            scrollpane_form_procesory.setVisible(true);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
        }else if(wybor.equals("Karta graficzna")){
            scrollpane_form_plyty.setVisible(false);
            scrollpane_form_karty.setVisible(true);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
        }else if(wybor.equals("Pamięć RAM")){
            scrollpane_form_plyty.setVisible(false);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_pamiec.setVisible(true);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
        }else if(wybor.equals("Dysk")){
            scrollpane_form_plyty.setVisible(false);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(true);
            scrollpane_form_zestawy.setVisible(false);
        }else if(wybor.equals("Zestaw")){
            scrollpane_form_plyty.setVisible(false);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(true);
        }else if(wybor.equals("")){
            scrollpane_form_plyty.setVisible(true);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
        }
    }

    //funkcja do dodawania nowego produktu do magazynu
    @FXML
    void dodaj_produkt(){

        String strona=f_typ_produktu.getValue();


        if(strona.equals("Płyta główna")){

            try{
                //sprawdzenie czy żadna wartość nie jest pusta
                if(fp_nazwa.getText().equals("")||fp_producent.getValue()==null||fp_chipset.getValue()==null
                ||fp_gniazdo.getValue()==null||fp_typ.getValue()==null||fp_banki.getValue()==null||
                fp_szerokosc.getText().equals("")||fp_wysokosc.getText().equals("")||
                fp_max_pam.getText().equals("")||fp_cena.getText().equals("")||fp_opis.getText().equals("")){
                    throw new EmptyValueException();
                }

                //sprawdzenie poprawności ceny

                //utworzenie ograniczenia na cene
                Pattern pat_cena = Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");
                Matcher matcher;


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(fp_cena.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmienienie znaku "," na "."
                if(fp_cena.getText().contains(",")){
                    fp_cena.setText(fp_cena.getText().replaceAll(",","."));
                }

                //sprawdzenie poprawności szerokości

                //utworzenie ograniczenia na dane
                Pattern pat_wymiar = Pattern.compile("^[0-9]*+$");

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fp_szerokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Szerokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności wysokości

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fp_wysokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Wysokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie unikalności nazwy

                for(int i=0;i<nazwy_plyty.length;i++){
                    if(nazwy_plyty[i].toUpperCase().equals(fp_nazwa.getText().toUpperCase())){
                        throw new NameTakenException();
                    }
                }

                //sprawdzenie poprawności max wielkości pamięci

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fp_max_pam.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Maksymalna wielkość pamięci: Dopuszczalne są tylko cyfry");
                }

                //utworzenie zapytania
                String zapytanie="Insert into PLYTA_GLOWNA values((select case when max(id_plyty_glownej)>0 then max(id_plyty_glownej)+1 else 1 end from PLYTA_GLOWNA),'"
                        +fp_gniazdo.getValue()+"','"+fp_chipset.getValue()+"','"+fp_typ.getValue()+"',"+fp_banki.getValue()+","+fp_max_pam.getText()+","+
                        fp_szerokosc.getText()+","+fp_wysokosc.getText()+",'"+fp_nazwa.getText()+"','"+fp_producent.getValue()+"','"+
                        fp_opis.getText()+"',"+fp_cena.getText()+")";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDane(zapytanie);

                //dodanie 5 sztuk nowego produktu do magazynu
                for(int i=0;i<5;i++){

                    wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null, null, " +
                            "(select id_plyty_glownej from plyta_glowna where nazwa_produktu='"+fp_nazwa.getText()+"'), " +
                            "null,null,null,null,null)");
                }

                if(wynik.equals("1")){
                    nazwy_plyty=connection.uzyskajDane("Select nazwa_produktu from PLYTA_GLOWNA");
                }

                //aktualizacja wartości w checkboxach
                fz_plyta.getItems().clear();
                fz_plyta.getItems().addAll(nazwy_plyty);

            }catch(EmptyValueException e){
                e.alert();
            }catch( CenaTypeException c){
                c.alert();
            }catch(TypeException t){
                t.alert();
            }catch(NameTakenException n){
                n.alert();
            }
        }else if(f_typ_produktu.getValue().equals("Procesor")){

            try{
                //sprawdzenie czy żadna wartość nie jest pusta
                if(fpr_nazwa.getText().equals("")||fpr_producent.getValue()==null||fpr_rodzina.getValue()==null
                        ||fpr_gniazdo.getValue()==null||fpr_liczba_rdzeni.getValue()==null||
                        fpr_taktowanie.getText().equals("")||fpr_watki.getText().equals("")||
                        fpr_moc.getText().equals("")||fpr_cena.getText().equals("")||fpr_opis.getText().equals("")){
                    throw new EmptyValueException();
                }

                //sprawdzenie poprawności ceny

                //utworzenie ograniczenia na cene
                Pattern pat_cena = Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");
                Matcher matcher;


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(fpr_cena.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmienienie znaku "," na "."
                if(fpr_cena.getText().contains(",")){
                    fpr_cena.setText(fpr_cena.getText().replaceAll(",","."));
                }

                //sprawdzenie poprawności taktowania

                //utworzenie ograniczenia na dane
                Pattern pat_wymiar = Pattern.compile("^[0-9]*+$");

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fpr_taktowanie.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Taktowanie: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności wysokości

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fpr_watki.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Wątki: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie unikalności nazwy

                for(int i=0;i<nazwy_procesory.length;i++){
                    if(nazwy_procesory[i].toUpperCase().equals(fpr_nazwa.getText().toUpperCase())){
                        throw new NameTakenException();
                    }
                }

                //sprawdzenie poprawności poboru mocy


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fpr_moc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Pobór mocy: Dopuszczalne są tylko cyfry");
                }

                //utworzenie zapytania
                String zapytanie="Insert into PROCESOR values((select case when max(id_procesora)>0 then max(id_procesora)+1 else 1 end from PROCESOR),'"
                        +fpr_rodzina.getValue()+"','"+fpr_gniazdo.getValue()+"','"+fpr_taktowanie.getText()+"',"+fpr_liczba_rdzeni.getValue()+","+fpr_watki.getText()+","+
                        fpr_moc.getText()+",'"+fpr_nazwa.getText()+"','"+fpr_producent.getValue()+"','"+
                        fpr_opis.getText()+"',"+fpr_cena.getText()+")";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDane(zapytanie);

                //dodanie 5 sztuk nowego produktu do magazynu
                for(int i=0;i<5;i++){

                    wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null, null, " +
                            "null, " +
                            "null," +
                            "(select id_procesora from procesor where nazwa_produktu='"+fpr_nazwa.getText()+"')," +
                            "null,null,null)");
                }

                if(wynik.equals("1")){
                    nazwy_procesory=connection.uzyskajDane("Select nazwa_produktu from PROCESOR");
                }

                //aktualizacja wartości w checkboxach
                fz_procesor.getItems().clear();
                fz_procesor.getItems().addAll(nazwy_procesory);

            }catch(EmptyValueException e){
                e.alert();
            }catch( CenaTypeException c){
                c.alert();
            }catch(TypeException t){
                t.alert();
            }catch(NameTakenException n){
                n.alert();
            }
        }else if(f_typ_produktu.getValue().equals("Karta graficzna")){

            try{
                //sprawdzenie czy żadna wartość nie jest pusta
                if(fk_nazwa.getText().equals("")||fk_producent.getValue()==null||fk_uklad.getValue()==null
                        ||fk_zlacze.getValue()==null||fk_rodzaj_pam.getValue()==null||fk_pojemnosc_pam.getText().equals("")||
                        fk_szerokosc.getText().equals("")||fk_wysokosc.getText().equals("")||fk_glebokosc.getText().equals("")||
                        fk_taktowanie.getText().equals("")||fk_cena.getText().equals("")||fk_opis.getText().equals("")||fk_moc.getText().equals("")){
                    throw new EmptyValueException();
                }

                //sprawdzenie poprawności ceny

                //utworzenie ograniczenia na cene
                Pattern pat_cena = Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");
                Matcher matcher;


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(fk_cena.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmienienie znaku "," na "."
                if(fk_cena.getText().contains(",")){
                    fk_cena.setText(fk_cena.getText().replaceAll(",","."));
                }

                //sprawdzenie poprawności szerokości

                //utworzenie ograniczenia na szerokość
                Pattern pat_wymiar = Pattern.compile("^[0-9]*+$");

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fk_szerokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Szerokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności wysokości

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fk_wysokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Wysokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności głębokości

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fk_glebokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Głębokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie unikalności nazwy

                for(int i=0;i<nazwy_karty.length;i++){
                    if(nazwy_karty[i].toUpperCase().equals(fk_nazwa.getText().toUpperCase())){
                        throw new NameTakenException();
                    }
                }

                //sprawdzenie poprawności wielkości pamięci

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fk_pojemnosc_pam.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Pojemność pamięci: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności taktowania

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fk_taktowanie.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Taktowanie: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności poboru mocy

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fk_moc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Pobór mocy: Dopuszczalne są tylko cyfry");
                }

                //utworzenie zapytania
                String zapytanie="Insert into KARTA_GRAFICZNA values((select case when max(id_karty_graficznej)>0 then max(id_karty_graficznej)+1 else 1 end from KARTA_GRAFICZNA),'"
                        +fk_uklad.getValue()+"','"+fk_zlacze.getValue()+"','"+fk_pojemnosc_pam.getText()+"','"+fk_rodzaj_pam.getValue()+"',"+fk_taktowanie.getText()+","+
                        fk_szerokosc.getText()+","+fk_wysokosc.getText()+","+fk_glebokosc.getText()+","+fk_moc.getText()+",'"+fk_nazwa.getText()+"','"+fk_producent.getValue()+"','"+
                        fk_opis.getText()+"',"+fk_cena.getText()+")";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDane(zapytanie);

                //dodanie 5 sztuk nowego produktu do magazynu
                for(int i=0;i<5;i++){

                    wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null," +
                            " null, " +
                            "null, " +
                            "(select id_karty_graficznej from karta_graficzna where nazwa_produktu='"+fk_nazwa.getText()+"')," +
                            "null," +
                            "null,null,null)");
                }

                if(wynik.equals("1")){
                    nazwy_karty=connection.uzyskajDane("Select nazwa_produktu from KARTA_GRAFICZNA");
                }

                //aktualizacja wartości w checkboxach
                fz_karta.getItems().clear();
                fz_karta.getItems().addAll(nazwy_karty);


            }catch(EmptyValueException e){
                e.alert();
            }catch( CenaTypeException c){
                c.alert();
            }catch(TypeException t){
                t.alert();
            }catch(NameTakenException n){
                n.alert();
            }
        }else if(f_typ_produktu.getValue().equals("Pamięć RAM")){

            try{
                //sprawdzenie czy żadna wartość nie jest pusta
                if(fr_nazwa.getText().equals("")||fr_producent.getValue()==null||fr_pamiec.getValue()==null
                        ||fr_pojemnosc.getValue()==null||fr_taktowanie.getText().equals("")||fr_napiecie.getText().equals("")||
                        fr_cena.getText().equals("")||fr_opis.getText().equals("")){
                    throw new EmptyValueException();
                }

                //sprawdzenie poprawności ceny

                //utworzenie ograniczenia na cene
                Pattern pat_cena = Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");
                Matcher matcher;


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(fr_cena.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmienienie znaku "," na "."
                if(fr_cena.getText().contains(",")){
                    fr_cena.setText(fr_cena.getText().replaceAll(",","."));
                }

                //sprawdzenie poprawności taktowania

                //utworzenie ograniczenia na dane
                Pattern pat_wymiar = Pattern.compile("^[0-9]*+$");

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fr_taktowanie.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Taktowanie: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności napięcia

                //utworzenie ograniczenia na napięcie
                Pattern pat_nap=Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_nap.matcher(fr_napiecie.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Napięcie: Dopuszczalne są tylko cyfry, w tym max " +
                            "dwa miejsca po przecinku");
                }

                //podmienienie znaku "," na "."
                if(fr_napiecie.getText().contains(",")){
                    fr_napiecie.setText(fr_napiecie.getText().replaceAll(",","."));
                }

                //sprawdzenie unikalności nazwy

                for(int i=0;i<nazwy_pamiec.length;i++){
                    if(nazwy_pamiec[i].toUpperCase().equals(fr_nazwa.getText().toUpperCase())){
                        throw new NameTakenException();
                    }
                }

                //utworzenie zapytania
                String zapytanie="Insert into PAMIEC_RAM values((select case when max(id_pamieci_ram)>0 then max(id_pamieci_ram)+1 else 1 end from PAMIEC_RAM),'"
                        +fr_pamiec.getValue()+"','"+fr_pojemnosc.getValue()+"','"+fr_taktowanie.getText()+"',"+fr_napiecie.getText()+",'"+fr_nazwa.getText()+"','"+fr_producent.getValue()+"','"+
                        fr_opis.getText()+"',"+fr_cena.getText()+")";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDane(zapytanie);

                //dodanie 5 sztuk nowego produktu do magazynu
                for(int i=0;i<5;i++){

                    wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null," +
                            " (select id_pamieci_ram from pamiec_ram where nazwa_produktu='"+fr_nazwa.getText()+"'), " +
                            "null, " +
                            "null," +
                            "null," +
                            "null,null,null)");
                }

                if(wynik.equals("1")){
                    nazwy_pamiec=connection.uzyskajDane("Select nazwa_produktu from PAMIEC_RAM");
                }

                //aktualizacja wartości w checkboxach
                fz_pamiec.getItems().clear();
                fz_pamiec.getItems().addAll(nazwy_pamiec);

            }catch(EmptyValueException e){
                e.alert();
            }catch( CenaTypeException c){
                c.alert();
            }catch(TypeException t){
                t.alert();
            }catch(NameTakenException n){
                n.alert();
            }
        }else if(f_typ_produktu.getValue().equals("Dysk")){

            try{
                //sprawdzenie czy żadna wartość nie jest pusta
                if(fd_nazwa.getText().equals("")||fd_producent.getValue()==null||fd_typ.getValue()==null
                        ||fd_pojemnosc.getValue()==null||fd_szerokosc.getText().equals("")||fd_wysokosc.getText().equals("")||fd_glebokosc.equals("")||
                        fd_cena.getText().equals("")||fd_opis.getText().equals("")){
                    throw new EmptyValueException();
                }

                //sprawdzenie poprawności ceny

                //utworzenie ograniczenia na cene
                Pattern pat_cena = Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");
                Matcher matcher;


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(fd_cena.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmienienie znaku "," na "."
                if(fd_cena.getText().contains(",")){
                    fd_cena.setText(fd_cena.getText().replaceAll(",","."));
                }

                //sprawdzenie poprawności wysokości

                //utworzenie ograniczenia na dane
                Pattern pat_wymiar = Pattern.compile("^[0-9]*+$");

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fd_wysokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Wysokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności szerokości

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fd_szerokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Szerokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie poprawności głębokości

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_wymiar.matcher(fd_glebokosc.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Głębokość: Dopuszczalne są tylko cyfry");
                }

                //sprawdzenie unikalności nazwy

                for(int i=0;i<nazwy_dyski.length;i++){
                    if(nazwy_dyski[i].toUpperCase().equals(fd_nazwa.getText().toUpperCase())){
                        throw new NameTakenException();
                    }
                }

                //utworzenie zapytania
                String zapytanie="Insert into DYSK values((select case when max(id_dysku)>0 then max(id_dysku)+1 else 1 end from DYSK),'"
                        +fd_pojemnosc.getValue()+"','"+fd_typ.getValue()+"','"+fd_szerokosc.getText()+"',"+fd_wysokosc.getText()+","+fd_glebokosc.getText()+
                        ",'"+fd_nazwa.getText()+"','"+fd_producent.getValue()+"','"+
                        fd_opis.getText()+"',"+fd_cena.getText()+")";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDane(zapytanie);

                //dodanie 5 sztuk nowego produktu do magazynu
                for(int i=0;i<5;i++){

                    wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null," +
                            " null, " +
                            "null, " +
                            "null," +
                            "null," +
                            "(select id_dysku from dysk where nazwa_produktu='"+fd_nazwa.getText()+"'),null,null)");
                }

                if(wynik.equals("1")){
                    System.out.println(nazwy_dyski.length);
                    nazwy_dyski=connection.uzyskajDane("Select nazwa_produktu from DYSK");
                    System.out.println(nazwy_dyski.length);
                }

                //aktualizacja wartości w checkboxach
                fz_dysk.getItems().clear();
                fz_dysk.getItems().addAll(nazwy_dyski);

            }catch(EmptyValueException e){
                e.alert();
            }catch( CenaTypeException c){
                c.alert();
            }catch(TypeException t){
                t.alert();
            }catch(NameTakenException n){
                n.alert();
            }
        }else if(f_typ_produktu.getValue().equals("Zestaw")){
            try{
                //sprawdzenie czy żadna wartość nie jest pusta
                if(fz_nazwa.getText().equals("")||fz_plyta.getValue()==null||fz_procesor.getValue()==null
                        ||fz_karta.getValue()==null||fz_pamiec.getValue()==null||fz_dysk.getValue()==null||
                        fz_cena.getText().equals("")){
                    throw new EmptyValueException();
                }

                //sprawdzenie poprawności ceny

                //utworzenie ograniczenia na cene
                Pattern pat_cena = Pattern.compile("^[0-9]*[.,]?[0-9]?[0-9]?+$");
                Matcher matcher;


                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(fz_cena.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmienienie znaku "," na "."
                if(fd_cena.getText().contains(",")){
                    fd_cena.setText(fd_cena.getText().replaceAll(",","."));
                }

                //sprawdzenie unikalności nazwy

                for(int i=0;i<nazwy_zestawy.length;i++){
                    if(nazwy_zestawy[i].toUpperCase().equals(fz_nazwa.getText().toUpperCase())){
                        throw new NameTakenException();
                    }
                }

                //utworzenie zapytania
                /*
                String zapytanie="Insert into ZESTAW values((select case when max(id_zestawu)>0 then max(id_zestawu)+1 else 1 end from ZESTAW),"
                        +"(Select id_plyty_glownej from PLYTA_GLOWNA where nazwa_produktu='"+fz_plyta.getValue()+"'),"
                        +"(Select id_procesora from PROCESOR where nazwa_produktu='"+fz_procesor.getValue()+"'),"
                        +"(Select id_dysku from DYSK where nazwa_produktu='"+fz_dysk.getValue()+"'),"
                        +"(Select id_pamieci_ram from PAMIEC_RAM where nazwa_produktu='"+fz_pamiec.getValue()+"'),"
                        +"(Select id_karty_graficznej from KARTA_GRAFICZNA where nazwa_produktu='"+fz_karta.getValue()+"'),"
                        +fz_cena.getText()+",'"+fz_nazwa.getText()+"')";
                */
                String[] plyta=connection.uzyskajDane("Select id_plyty_glownej from PLYTA_GLOWNA where nazwa_produktu='"+fz_plyta.getValue()+"'");
                String[] procesor=connection.uzyskajDane("Select id_procesora from PROCESOR where nazwa_produktu='"+fz_procesor.getValue()+"'");
                String[] karta=connection.uzyskajDane("Select id_karty_graficznej from KARTA_GRAFICZNA where nazwa_produktu='"+fz_karta.getValue()+"'");
                String[] pamiec=connection.uzyskajDane("Select id_pamieci_ram from PAMIEC_RAM where nazwa_produktu='"+fz_pamiec.getValue()+"'");
                String[] dysk=connection.uzyskajDane("Select id_dysku from DYSK where nazwa_produktu='"+fz_dysk.getValue()+"'");


                String zapytanie="Insert into ZESTAW values((select case when max(id_zestawu)>0 then max(id_zestawu)+1 else 1 end from ZESTAW),"
                        +plyta[0]+","
                        +procesor[0]+","
                        +dysk[0]+","
                        +pamiec[0]+","
                        +karta[0]+","
                        +fz_cena.getText()+",'"+fz_nazwa.getText()+"')";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDaneBezAlert(zapytanie);

                if(wynik.equals("1")){
                    nazwy_zestawy=connection.uzyskajDane("Select nazwa_zestawu from ZESTAW");

                    //dodanie 5 instancji zestawu
                    for(int i=0;i<5;i++){

                        String idZestawu=connection.uzyskajDane("Select case " +
                                "when max(id_zestawu)>0 then max(id_zestawu)+1 else 1 end from produkt")[0];
                        String idTypuZestawu=connection.uzyskajDane("Select id_zestawu " +
                                "from zestaw where nazwa_zestawu='"+fz_nazwa.getText()+"'")[0];
                        String idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        String idKomponentu=connection.uzyskajDane("Select id_pamiec_ram " +
                                "from zestaw where nazwa_zestawu='"+fz_nazwa.getText()+"'")[0];

                        //dodanie pamięci ram zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                idKomponentu+", " +
                                "null, " +
                                "null," +
                                "null," +
                                "null," +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_plyta_glowna " +
                                "from zestaw where nazwa_zestawu='"+fz_nazwa.getText()+"'")[0];


                        //dodanie płyty głównej zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null," +
                                idKomponentu+", " +
                                "null," +
                                "null," +
                                "null," +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_karta_graficzna from zestaw where nazwa_zestawu='"+fz_nazwa.getText()+"'")[0];


                        //dodanie karty graficznej zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null, " +
                                "null, " +
                                idKomponentu+", " +
                                "null, " +
                                "null, " +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_procesor from zestaw where nazwa_zestawu='"+fz_nazwa.getText()+"'")[0];


                        //dodanie procesora zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null, " +
                                "null, " +
                                "null, " +
                                idKomponentu+", " +
                                "null," +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_dysk from zestaw where nazwa_zestawu='"+fz_nazwa.getText()+"'")[0];


                        //dodanie dysku zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null, " +
                                "null, " +
                                "null, " +
                                "null, " +
                                idKomponentu+", " +
                                idTypuZestawu+", "+
                                idZestawu+")");
                    }

                    informationAlert("Wprowadzenie/modyfikacja danych zakończona pomyślnie");
                }

            }catch(EmptyValueException e){
                e.alert();
            }catch( CenaTypeException c){
                c.alert();
            }catch(NameTakenException n){
                n.alert();
            }

        }

    }

    //przesianie uzupełnianych produktów po nazwie
    @FXML
    void wyszukaj_nazwa_uzupelnianie(){
        String nazwa=szukaj_nazwa.getText();

        //wyszukanie po nazwie
        if(!nazwa.equals("")){
            //utworzenie list do wypełniania odpowiednich typów
            ObservableList<TableUzupelnianie> tuz_list= FXCollections.observableArrayList();
            //wypełnienie danymi z BD

            String zapytanie="Select nazwa_produktu, typ, ilosc from (Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
            "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
            "union "+
            "Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, 0 as ilosc from produkt "+
            "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
            "where (id_transakcji is not null or id_zamowienia is not null) " +
            "and id_zestawu is null and plyta_glowna.nazwa_produktu not in (" +
            "Select nazwa_produktu from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
            "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu))" +
            " group by nazwa_produktu" +
            " union " +
            "select procesor.nazwa_produktu, 'Procesor' as typ, count(*) as ilosc from produkt " +
            "join procesor on produkt.id_procesora=procesor.id_procesora " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
            "union "+
            "select procesor.nazwa_produktu, 'Procesor' as typ, 0 as ilosc from produkt " +
            "join procesor on produkt.id_procesora=procesor.id_procesora " +
            "where (id_transakcji is  not null or id_zamowienia is null) " +
            "and id_zestawu is null and procesor.nazwa_produktu not in ("+
            "select nazwa_produktu from (select procesor.nazwa_produktu, 'Procesor' as typ, count(*) as ilosc from produkt " +
            "join procesor on produkt.id_procesora=procesor.id_procesora " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu)) "+
            "group by nazwa_produktu " +
            "union " +
            "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, count(*) as ilosc from produkt " +
            "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
            "union "+
            "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, 0 as ilosc from produkt " +
            "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
            "where (id_transakcji is not null or id_zamowienia is not null) " +
            "and id_zestawu is null and karta_graficzna.nazwa_produktu not in (" +
            "select nazwa_produktu from (" +
            "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, count(*) as ilosc from produkt " +
            "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu ))"+
            "group by nazwa_produktu " +
            "union " +
            "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, count(*) as ilosc from produkt " +
            "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
            "union "+
            "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, 0 as ilosc from produkt " +
            "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
            "where (id_transakcji is not null or id_zamowienia is not null)" +
            "and id_zestawu is null and pamiec_ram.nazwa_produktu not in (" +
            "select nazwa_produktu from ("+
            "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, count(*) as ilosc from produkt " +
            "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu ))"+
            "group by nazwa_produktu "+
            "union " +
            "select dysk.nazwa_produktu, 'Dysk' as typ, count(*) as ilosc from produkt " +
            "join dysk on produkt.id_dysku=dysk.id_dysku " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu " +
            "union "+
            "select dysk.nazwa_produktu, 'Dysk' as typ, 0 as ilosc from produkt " +
            "join dysk on produkt.id_dysku=dysk.id_dysku " +
            "where (id_transakcji is not null or id_zamowienia is not null) " +
            "and id_zestawu is null and dysk.nazwa_produktu not in (" +
            "select nazwa_produktu from ("+
            "select dysk.nazwa_produktu, 'Dysk' as typ, count(*) as ilosc from produkt " +
            "join dysk on produkt.id_dysku=dysk.id_dysku " +
            "where id_transakcji is null and id_zamowienia is null and id_zestawu is null group by nazwa_produktu)) "+
            "group by nazwa_produktu " +
            "union "+
            "Select zestaw.nazwa_zestawu, 'Zestaw' as typ, count(*)/5 as ilosc "+
            "from produkt join zestaw on produkt.id_typu_zestawu=zestaw.id_zestawu "+
            "where id_transakcji is null and id_zamowienia is null group by nazwa_zestawu "+
            "union "+
            "Select zestaw.nazwa_zestawu, 'Zestaw' as typ, 0 as ilosc "+
            "from produkt join zestaw on produkt.id_typu_zestawu=zestaw.id_zestawu "+
            "where (id_transakcji is not null or id_zamowienia is not null) and zestaw.nazwa_zestawu not in "+
            "(Select nazwa_zestawu from (Select zestaw.nazwa_zestawu, 'Zestaw' as typ, count(*)/5 as ilosc "+
            "from produkt join zestaw on produkt.id_typu_zestawu=zestaw.id_zestawu "+
            "where id_transakcji is null and id_zamowienia is null group by nazwa_zestawu)) group by nazwa_zestawu "+
            ") order by ilosc asc"+
            ") where nazwa_produktu like('%"+nazwa+"%') order by ilosc asc";
/*"Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu" +
                    " union " +
                    "select procesor.nazwa_produktu, 'Procesor' as typ, count(*) as ilosc from produkt " +
                    "join procesor on produkt.id_procesora=procesor.id_procesora " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu " +
                    "union " +
                    "select karta_graficzna.nazwa_produktu, 'Karta graficzna' as typ, count(*) as ilosc from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu " +
                    "union " +
                    "select pamiec_ram.nazwa_produktu, 'Pamieć RAM' as typ, count(*) as ilosc from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu " +
                    "union " +
                    "select dysk.nazwa_produktu, 'Dysk' as typ, count(*) as ilosc from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu) where nazwa_produktu like('%"+nazwa+"%') order by ilosc asc";*/
            String wynik[]= connection.uzyskajDane(zapytanie);

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tuz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                }
            }

                table_uzupelnianie.setItems(tuz_list);
                dane.setOstatnieZapytanieUzupelnijTable(zapytanie);
        }else{

            dane.setOstatnieZapytanieUzupelnijTable("");
            odswiezTableUzupelnij();
        }
    }

    //funkcja do wybierania produktu z listy uzupełnień

    @FXML
    void wybierzProdukt(MouseEvent event){
        Integer index=table_uzupelnianie.getSelectionModel().getSelectedIndex();

        if(!(tuz_nazwa.getCellData(index)==null)){
            nazwaWybranyProdukt=tuz_nazwa.getCellData(index);
        }
        if(!(tuz_typ.getCellData(index)==null)){
            typWybranyProdukt=tuz_typ.getCellData(index);
        }
    }

    //funkcja do uzupełniania wybranego produktu o podaną ilość
    @FXML
    void uzupelnijWybranyProdukt(){
        if(nazwaWybranyProdukt.equals("")){
            informationAlert("Nie wybrano produktu");
        }else{
            if(!uzupelnij_ilosc.getText().equals("")){
                int ilosc=Integer.parseInt(uzupelnij_ilosc.getText());
                String wynik="0";

                if(typWybranyProdukt.equals("Płyta główna")){

                    for(int i=0;i<ilosc;i++){

                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                " null, null, null, " +
                                "(select id_plyty_glownej from plyta_glowna where nazwa_produktu='"+nazwaWybranyProdukt+"'), " +
                                "null,null,null,null,null)");
                    }
                }else if(typWybranyProdukt.equals("Procesor")){
                    for(int i=0;i<ilosc;i++){

                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                " null, null, null, " +
                                "null, " +
                                "null," +
                                "(select id_procesora from procesor where nazwa_produktu='"+nazwaWybranyProdukt+"')," +
                                "null,null,null)");
                    }
                }else if(typWybranyProdukt.equals("Pamięć RAM")){
                    for(int i=0;i<ilosc;i++){

                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                " null, null," +
                                " (select id_pamieci_ram from pamiec_ram where nazwa_produktu='"+nazwaWybranyProdukt+"'), " +
                                "null, " +
                                "null," +
                                "null," +
                                "null,null,null)");
                    }
                }else if(typWybranyProdukt.equals("Karta graficzna")){
                    for(int i=0;i<ilosc;i++){

                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                " null, null," +
                                " null, " +
                                "null, " +
                                "(select id_karty_graficznej from karta_graficzna where nazwa_produktu='"+nazwaWybranyProdukt+"')," +
                                "null," +
                                "null,null,null)");
                    }
                }else if(typWybranyProdukt.equals("Dysk")){
                    for(int i=0;i<ilosc;i++){

                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                " null, null," +
                                " null, " +
                                "null, " +
                                "null," +
                                "null," +
                                "(select id_dysku from dysk where nazwa_produktu='"+nazwaWybranyProdukt+"'),null,null)");
                    }
                }else if(typWybranyProdukt.equals("Zestaw")){
                    for(int i=0;i<ilosc;i++){

                        String idZestawu=connection.uzyskajDane("Select case " +
                                "when max(id_zestawu)>0 then max(id_zestawu)+1 else 1 end from produkt")[0];
                        String idTypuZestawu=connection.uzyskajDane("Select id_zestawu " +
                                        "from zestaw where nazwa_zestawu='"+nazwaWybranyProdukt+"'")[0];
                        String idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        String idKomponentu=connection.uzyskajDane("Select id_pamiec_ram " +
                                "from zestaw where nazwa_zestawu='"+nazwaWybranyProdukt+"'")[0];

                        //dodanie pamięci ram zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                idKomponentu+", " +
                                "null, " +
                                "null," +
                                "null," +
                                "null," +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_plyta_glowna " +
                                "from zestaw where nazwa_zestawu='"+nazwaWybranyProdukt+"'")[0];


                        //dodanie płyty głównej zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null," +
                                idKomponentu+", " +
                                "null," +
                                "null," +
                                "null," +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_karta_graficzna from zestaw where nazwa_zestawu='"+nazwaWybranyProdukt+"'")[0];


                        //dodanie karty graficznej zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null, " +
                                "null, " +
                                idKomponentu+", " +
                                "null, " +
                                "null, " +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_procesor from zestaw where nazwa_zestawu='"+nazwaWybranyProdukt+"'")[0];


                        //dodanie procesora zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null, " +
                                "null, " +
                                "null, " +
                                idKomponentu+", " +
                                "null," +
                                idTypuZestawu+", "+
                                idZestawu+")");

                        idProduktu=connection.uzyskajDane("Select case " +
                                "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                        idKomponentu=connection.uzyskajDane("Select id_dysk from zestaw where nazwa_zestawu='"+nazwaWybranyProdukt+"'")[0];


                        //dodanie dysku zestawu
                        wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                "values("+idProduktu+"," +
                                " null, null," +
                                "null, " +
                                "null, " +
                                "null, " +
                                "null, " +
                                idKomponentu+", " +
                                idTypuZestawu+", "+
                                idZestawu+")");
                    }
                }
                //wyświetlenie alertu
                if(wynik.equals("1")){
                    informationAlert("Wprowadzenie/modyfikacja danych zakończona pomyślnie");
                }else{
                    errorAlert("Wprowadzenie/modyfikacja danych nieudana");
                }

                //zaktualizowanie informacji w tabeli
                odswiezTableUzupelnij();

            }
        }
    }

    //funkcja do uzupełniania stanu wszystkich produktów których ilość jest mniejsza od 10
    @FXML
    void uzupelnijWszystkieProdukty(){

        for(int j=0;j<nazwyProduktowUzupelnij.size();j++) {
            if(iloscProduktowUzupelnij.get(j)<10) {
                {
                    if (typProduktowUzupelnij.get(j).equals("Płyta główna")) {

                        for (int i = 0; i < (10-iloscProduktowUzupelnij.get(j)); i++) {

                            String wynik = connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null, null, " +
                                    "(select id_plyty_glownej from plyta_glowna where nazwa_produktu='" + nazwyProduktowUzupelnij.get(j) + "'), " +
                                    "null,null,null,null,null)");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;

                            }
                        }
                    } else if (typProduktowUzupelnij.get(j).equals("Procesor")) {
                        for (int i = 0; i < (10-iloscProduktowUzupelnij.get(j)); i++) {

                            String wynik = connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null, null, " +
                                    "null, " +
                                    "null," +
                                    "(select id_procesora from procesor where nazwa_produktu='" + nazwyProduktowUzupelnij.get(j) + "')," +
                                    "null,null,null)");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }
                        }
                    } else if (typProduktowUzupelnij.get(j).equals("Pamięć RAM")) {
                        for (int i = 0; i < (10-iloscProduktowUzupelnij.get(j)); i++) {

                            String wynik = connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null," +
                                    " (select id_pamieci_ram from pamiec_ram where nazwa_produktu='" + nazwyProduktowUzupelnij.get(j) + "'), " +
                                    "null, " +
                                    "null," +
                                    "null," +
                                    "null,null,null)");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }
                        }
                    } else if (typProduktowUzupelnij.get(j).equals("Karta graficzna")) {
                        for (int i = 0; i < (10-iloscProduktowUzupelnij.get(j)); i++) {

                            String wynik = connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null," +
                                    " null, " +
                                    "null, " +
                                    "(select id_karty_graficznej from karta_graficzna where nazwa_produktu='" + nazwyProduktowUzupelnij.get(j) + "')," +
                                    "null," +
                                    "null,null,null)");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }
                        }
                    } else if (typProduktowUzupelnij.get(j).equals("Dysk")) {
                        for (int i = 0; i < (10-iloscProduktowUzupelnij.get(j)); i++) {

                            String wynik = connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null," +
                                    " null, " +
                                    "null, " +
                                    "null," +
                                    "null," +
                                    "(select id_dysku from dysk where nazwa_produktu='" + nazwyProduktowUzupelnij.get(j) + "'),null,null)");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }
                        }
                    }else if(typProduktowUzupelnij.get(j).equals("Zestaw")){

                        for (int i = 0; i < (10-iloscProduktowUzupelnij.get(j)); i++) {

                            String idZestawu=connection.uzyskajDane("Select case " +
                                    "when max(id_zestawu)>0 then max(id_zestawu)+1 else 1 end from produkt")[0];
                            String idTypuZestawu=connection.uzyskajDane("Select id_zestawu " +
                                    "from zestaw where nazwa_zestawu='"+nazwyProduktowUzupelnij.get(j)+"'")[0];
                            String idProduktu=connection.uzyskajDane("Select case " +
                                    "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                            String idKomponentu=connection.uzyskajDane("Select id_pamiec_ram " +
                                    "from zestaw where nazwa_zestawu='"+nazwyProduktowUzupelnij.get(j)+"'")[0];

                            //dodanie pamięci ram zestawu
                            String wynik= connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values("+idProduktu+"," +
                                    " null, null," +
                                    idKomponentu+", " +
                                    "null, " +
                                    "null," +
                                    "null," +
                                    "null," +
                                    idTypuZestawu+", "+
                                    idZestawu+")");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }

                            idProduktu=connection.uzyskajDane("Select case " +
                                    "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                            idKomponentu=connection.uzyskajDane("Select id_plyta_glowna " +
                                    "from zestaw where nazwa_zestawu='"+nazwyProduktowUzupelnij.get(j)+"'")[0];


                            //dodanie płyty głównej zestawu
                            wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values("+idProduktu+"," +
                                    " null, null," +
                                    "null," +
                                    idKomponentu+", " +
                                    "null," +
                                    "null," +
                                    "null," +
                                    idTypuZestawu+", "+
                                    idZestawu+")");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }

                            idProduktu=connection.uzyskajDane("Select case " +
                                    "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                            idKomponentu=connection.uzyskajDane("Select id_karta_graficzna from zestaw where nazwa_zestawu='"+nazwyProduktowUzupelnij.get(j)+"'")[0];


                            //dodanie karty graficznej zestawu
                            wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values("+idProduktu+"," +
                                    " null, null," +
                                    "null, " +
                                    "null, " +
                                    idKomponentu+", " +
                                    "null, " +
                                    "null, " +
                                    idTypuZestawu+", "+
                                    idZestawu+")");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }

                            idProduktu=connection.uzyskajDane("Select case " +
                                    "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                            idKomponentu=connection.uzyskajDane("Select id_procesor from zestaw where nazwa_zestawu='"+nazwyProduktowUzupelnij.get(j)+"'")[0];


                            //dodanie procesora zestawu
                            wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values("+idProduktu+"," +
                                    " null, null," +
                                    "null, " +
                                    "null, " +
                                    "null, " +
                                    idKomponentu+", " +
                                    "null," +
                                    idTypuZestawu+", "+
                                    idZestawu+")");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }

                            idProduktu=connection.uzyskajDane("Select case " +
                                    "when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt")[0];
                            idKomponentu=connection.uzyskajDane("Select id_dysk from zestaw where nazwa_zestawu='"+nazwyProduktowUzupelnij.get(j)+"'")[0];


                            //dodanie dysku zestawu
                            wynik=connection.wprowadzDaneBezAlert("Insert into produkt " +
                                    "values("+idProduktu+"," +
                                    " null, null," +
                                    "null, " +
                                    "null, " +
                                    "null, " +
                                    "null, " +
                                    idKomponentu+", " +
                                    idTypuZestawu+", "+
                                    idZestawu+")");

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("0")){
                                errorAlert("Błąd przy uzupełnianiu magazynu");
                                break;
                            }
                        }

                    }
                }
            }
        }

        //zaktualizowanie informacji w tabeli
        odswiezTableUzupelnij();
    }

    //funkcja do przesiewania danych w tabeli transakcji-zamówień
    @FXML
    void przesiejTableTrazam(){

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableTrazam> ttz_list = FXCollections.observableArrayList();

        //pobranie wartości z pól wyszukiwania
        String email = textarea_trazam_email.getText();

        //sprawdzenie poprawności id

        //utworzenie ograniczenia na cene
        Pattern pat_cena = Pattern.compile("^[0-9]*+$");
        Matcher matcher;
        try {
            if (!textarea_trazam_id.getText().equals("")) {
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_cena.matcher(textarea_trazam_id.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new TypeException("Id przyjmuje tylko cyfry");
                }
            }

            String id = textarea_trazam_id.getText();

            String zapytanie = "Select id, email, typ, ilosc, cena_calkowita from (Select id_transakcji as id, email,'T' as typ, " +
                    "(Select count(*) from produkt where id_transakcji=t.id_transakcji) as ilosc, cena_calkowita from transakcja t " +
                    "join uzytkownik on t.id_uzytkownika=uzytkownik.id_uzytkownika  where status='oczekująca' " +
                    "union " +
                    "Select id_zamowienia as id, email,'Z' as typ, (Select count(*) from produkt where id_zamowienia=z.id_zamowienia) as ilosc, cena_calkowita from zamowienie z " +
                    "join uzytkownik on z.id_uzytkownika=uzytkownik.id_uzytkownika where status_odbioru='oczekujace') t where ";

            ArrayList<String> sl = new ArrayList<>();

            if (!email.equals("")) {
                sl.add("email like('%" + email + "%')");
            }
            if (trazam_typ.getSelectionModel().getSelectedItem() != null && !trazam_typ.getSelectionModel().getSelectedItem().equals("Oba")) {
                sl.add("typ='" + (trazam_typ.getSelectionModel().getSelectedItem().equals("Transakcja") ? "T'" : "Z'"));
            }
            if (!id.equals("")) {
                sl.add("id=" + id);
            }

            for (int i = 0; i < sl.size(); i++) {
                if (i == 0) {
                    zapytanie += sl.get(i);
                } else {
                    zapytanie += " and " + sl.get(i);
                }
            }
            if (sl.size() > 0) {
                //wprowadzenie elementów do tabeli
                String wynik[] = connection.uzyskajDane(zapytanie);

                if (wynik.length <= 1) {
                    //gdy zapytanie nie zwróciło żądnych wyników

                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    ttz_list.clear();
                } else {
                    for (int i = 0; i < wynik.length; i += 5) {
                        ttz_list.add(new TableTrazam(Integer.parseInt(wynik[i]), wynik[i + 1], wynik[i + 2], Integer.parseInt(wynik[i + 3]), Double.parseDouble(wynik[i + 4])));
                    }
                }

                table_tra_zam.setItems(ttz_list);

                dane.setOstatnieZapytanieTableTrazam(zapytanie);
            } else {
                dane.setOstatnieZapytanieTableTrazam("");
                uzupelnijTableTrazam();
            }
        }catch(TypeException t){
            t.alert();
        }

    }

    //funkcja do wybierania transakcji/zamówienia z tabeli
    @FXML
    void wybierzTrazam(){
        Integer index=table_tra_zam.getSelectionModel().getSelectedIndex();

        //w przypadku wartośći null nic nie robi

        if(!(ttz_id.getCellData(index)==null)){
            trazamId=Integer.toString(ttz_id.getCellData(index));
        }
        if(!(ttz_typ.getCellData(index)==null)){
            trazamTyp=ttz_typ.getCellData(index);
        }

    }

    //funkcja do zatwierdzania transakcji/zamówień
    @FXML
    void zatwierdzTrazam(){
        String id=trazamId;
        String typ=trazamTyp;

        if(id.equals("")){
            informationAlert("Nie wybrano transakcji/zamówienia");
        }else{
            if(typ.equals("T")){
                connection.wprowadzDane("Update transakcja " +
                        "set status='zatwierdzona' where id_transakcji="+id);
            }else{

                connection.wprowadzDane("Update zamowienie " +
                        "set status_odbioru='do odbioru' where id_zamowienia="+id);
            }

            uzupelnijTableTrazam();
        }
    }

    //funkcja do odrzucania transakcji/zamówień
    @FXML
    void odrzucTrazam(){
        String id=trazamId;
        String typ=trazamTyp;

        if(id.equals("")){
            informationAlert("Nie wybrano transakcji/zamówienia");
        }else{
            if(typ.equals("T")){

                String wynik[]=connection.uzyskajDane("Select * from produkt where id_transakcji="+id);
                String wynikWew="0";
                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Transakcja pusta");
                    }


                }else{
                    for(int i=0;i<wynik.length;i+=10){
                        wynikWew=connection.wprowadzDaneBezAlert("Insert into produkt values(" +
                                "(select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                wynik[i+1]+", "+
                                "null, "+
                                wynik[i+3]+", "+
                                wynik[i+4]+", "+
                                wynik[i+5]+", "+
                                wynik[i+6]+", "+
                                wynik[i+7]+", "+
                                wynik[i+8]+", "+
                                wynik[i+9]+")"
                        );

                        if(wynikWew.equals("0")){
                            break;
                        }
                    }
                }

                if(wynikWew.equals("1")){
                    connection.wprowadzDane("Update transakcja " +
                            "set status='anulowana' where id_transakcji="+id);
                }else{
                    errorAlert("Błąd przy odrzucaniu transakcji");
                }


            }else{
                connection.wprowadzDane("Update zamowienie " +
                        "set status_odbioru='anulowane' where id_zamowienia="+id);
            }

            uzupelnijTableTrazam();
        }
    }

    //funkcja do przesiewania tabeli użytkownicy
    @FXML
    void przesiejTableUzytkownicy(){

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableUzytkownicy> tu_list = FXCollections.observableArrayList();

        //pobranie wartości z pól wyszukiwania
        String email = textfield_email.getText();

        String zapytanie = "Select email, " +
                "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=1 and czy_wazny=1) then 'P' else 'N' end as \"0.05\", " +
                "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=2 and czy_wazny=1) then 'P' else 'N' end as \"0.1\", " +
                "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=3 and czy_wazny=1) then 'P' else 'N' end as \"0.15\", " +
            "case when exists(select 1 from uzytkownik_rabat where id_uzytkownika=u.id_uzytkownika and id_rabatu=4 and czy_wazny=1) then 'P' else 'N' end as \"0.25\" from uzytkownik u where ";

        if (!email.equals("")) {
            zapytanie+="email like('%" + email + "%')";

            //wprowadzenie elementów do tabeli
            String wynik[]= connection.uzyskajDane(zapytanie);

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tu_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=5){
                    tu_list.add(new TableUzytkownicy(wynik[i],wynik[i+1],wynik[i+2],wynik[i+3],wynik[i+4]));
                }
            }

            table_uzytkownicy.setItems(tu_list);

            dane.setOstatnieZapytanieTableUzytkownicy(zapytanie);
        }else{
            dane.setOstatnieZapytanieTableUzytkownicy("");
            uzupelnijTableUzytkownicy();
        }

    }

    //funkcja do pobierania użytkownika
    @FXML
    void pobierzUzytkownika(MouseEvent event){
        Integer index=table_uzytkownicy.getSelectionModel().getSelectedIndex();

        email=tu_email.getCellData(index);
        r5=tu_r5.getCellData(index);
        r10=tu_r10.getCellData(index);
        r15=tu_r15.getCellData(index);
        r25=tu_r25.getCellData(index);
    }

    //funkcja do zarządzania użytkownikiem
    @FXML
    void zarzadzaj(MouseEvent event) throws IOException {

        if(email.equals("")){
            informationAlert("Nie wybrano konta użytkownika");
        }else{
            String wynik=connection.uzyskajDane(
                    "Select id_uzytkownika from uzytkownik where email='"+email+"'")[0];

            dane.setIdWybranegoUzytkownika(wynik);

            root = FXMLLoader.load(getClass().getResource("admin_uzytkownik" + ".fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    //funkcja do przyznawania rabatów
    @FXML
    void przyznajRabat(){
        if(email.equals("")){
            informationAlert("Nie wybrano konta użytkownika");
        }else{
            String rabat=scrollbar_typ_rabatu.getSelectionModel().getSelectedItem();
            String id_rabatu;
            String id_uz=connection.uzyskajDane("Select id_uzytkownika from uzytkownik where email='"+email+"'")[0];

            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatowanie = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
            String formattedDate2=date.format(formatowanie);

            switch(rabat){
                case "5%":
                    if(r5.equals("N")){
                        id_rabatu=connection.uzyskajDane("Select id_rabatu from typ_rabatu where kwota=0.05")[0];
                        connection.wprowadzDane("update uzytkownik_rabat set czy_wazny=1 "+
                                "where id_uzytkownika="+id_uz+" and "+"id_rabatu="+id_rabatu);

                        //wysłanie wiadomości do użytkownika odnośnie przyznania rabatu
                        String idwiadomosci=connection.uzyskajDane("Select max(id_wiadomosci)+1 from wiadomosc")[0];
                        String wynik=connection.wprowadzDaneBezAlert("Insert into wiadomosc values(" +
                                idwiadomosci+"," +
                                id_uz+", "+"'Gratulacje! Otrzymano rabat o wysokości 5%.','"+formattedDate2+"',0)");

                        if(wynik.equals("0")){
                            errorAlert("Błąd przy wysyłaniu wiadomości do użytkownika");
                        }

                    }else{
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Uzytkownik posiada już ten rabat");
                    }
                    break;
                case "10%":
                    if(r10.equals("N")){
                        id_rabatu=connection.uzyskajDane("Select id_rabatu from typ_rabatu where kwota=0.10")[0];
                        connection.wprowadzDane("update uzytkownik_rabat set czy_wazny=1 "+
                                "where id_uzytkownika="+id_uz+" and "+"id_rabatu="+id_rabatu);

                        //wysłanie wiadomości do użytkownika odnośnie przyznania rabatu
                        String idwiadomosci=connection.uzyskajDane("Select max(id_wiadomosci)+1 from wiadomosc")[0];
                        String wynik=connection.wprowadzDaneBezAlert("Insert into wiadomosc values(" +
                                idwiadomosci+"," +
                                id_uz+", "+"'Gratulacje! Otrzymano rabat o wysokości 10%.','"+formattedDate2+"',0)");

                        if(wynik.equals("0")){
                            errorAlert("Błąd przy wysyłaniu wiadomości do użytkownika");
                        }

                    }else{
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Uzytkownik posiada już ten rabat");
                    }
                    break;
                case "15%":
                    if(r15.equals("N")){
                        id_rabatu=connection.uzyskajDane("Select id_rabatu from typ_rabatu where kwota=0.15")[0];
                        connection.wprowadzDane("update uzytkownik_rabat set czy_wazny=1 "+
                                "where id_uzytkownika="+id_uz+" and "+"id_rabatu="+id_rabatu);

                        //wysłanie wiadomości do użytkownika odnośnie przyznania rabatu
                        String idwiadomosci=connection.uzyskajDane("Select max(id_wiadomosci)+1 from wiadomosc")[0];
                        String wynik=connection.wprowadzDaneBezAlert("Insert into wiadomosc values(" +
                                idwiadomosci+"," +
                                id_uz+", "+"'Gratulacje! Otrzymano rabat o wysokości 15%.','"+formattedDate2+"',0)");

                        if(wynik.equals("0")){
                            errorAlert("Błąd przy wysyłaniu wiadomości do użytkownika");
                        }

                    }else{
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Uzytkownik posiada już ten rabat");
                    }
                    break;
                case "25%":
                    if(r25.equals("N")){
                        id_rabatu=connection.uzyskajDane("Select id_rabatu from typ_rabatu where kwota=0.25")[0];
                        connection.wprowadzDane("update uzytkownik_rabat set czy_wazny=1 "+
                                "where id_uzytkownika="+id_uz+" and "+"id_rabatu="+id_rabatu);

                        //wysłanie wiadomości do użytkownika odnośnie przyznania rabatu
                        String idwiadomosci=connection.uzyskajDane("Select max(id_wiadomosci)+1 from wiadomosc")[0];
                        String wynik=connection.wprowadzDaneBezAlert("Insert into wiadomosc values(" +
                                idwiadomosci+"," +
                                id_uz+", "+"'Gratulacje! Otrzymano rabat o wysokości 25%.','"+formattedDate2+"',0)");

                        if(wynik.equals("0")){
                            errorAlert("Błąd przy wysyłaniu wiadomości do użytkownika");
                        }

                    }else{
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Uzytkownik posiada już ten rabat");
                    }
                    break;
            }

            uzupelnijTableUzytkownicy();
        }
    }

    //funkcje do wyświetlania statystyk
    @FXML
    void pokazStatystykiI(){

        String st=choicebox_typ_statystyk.getSelectionModel().getSelectedItem();

        switch(st){
            case "Produkty":
                //utworzenie list do wypełniania odpowiednich typów
                ObservableList<PieChart.Data> pieU_list= FXCollections.observableArrayList();

                //wypełnienie danymi z BD

                String wynik[]= connection.uzyskajDane("Select 'Płyta główna' as typ, count(*) as ilosc from produkt where id_plyty_glownej is not null and "+
                        "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
                        "union "+
                        "select 'Procesor' as typ, count(*) as ilosc from produkt where id_procesora is not null and "+
                        "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
                        "union "+
                        "select 'Karta graficzna' as typ, count(*) as ilosc from produkt where id_karty_graficznej is not null and "+
                        "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
                        "union "+
                        "select 'Pamięć RAM' as typ, count(*) as ilosc from produkt where id_pamieci_ram is not null and "+
                        "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane')) "+
                        "union "+
                        "select 'Dysk' as typ, count(*) as ilosc from produkt where id_dysku is not null and "+
                        "(id_transakcji in (select id_transakcji from transakcja where status='zatwierdzona') or id_zamowienia in (select id_zamowienia from zamowienie where status_odbioru='zrealizowane'))");
                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        pieU_list.clear();
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        pieU_list.add(new PieChart.Data(wynik[i],Integer.parseInt(wynik[i+1])));
                    }
                }

                pieU_list.forEach(data -> data.nameProperty().bind(
                        Bindings.concat(data.getName()," liczba: ",data.getPieValue())
                ));

                //wstawienie danych
                pie_produkty.getData().clear();
                pie_produkty.getData().addAll(pieU_list);

                choicebox_st_uz.setVisible(false);
                choicebox_st_prod.setVisible(false);
                st_pie_produkty.setVisible(true);
                st_chart_uzOba.setVisible(false);
                st_chart_uzTran.setVisible(false);
                st_chart_uzZam.setVisible(false);
                st_chart_plyty.setVisible(false);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(false);

                break;
            case "Top 5 Użytkownicy":

                choicebox_st_uz.setVisible(true);
                choicebox_st_prod.setVisible(false);
                st_pie_produkty.setVisible(false);
                st_chart_uzOba.setVisible(true);
                st_chart_uzTran.setVisible(false);
                st_chart_uzZam.setVisible(false);
                st_chart_plyty.setVisible(false);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(false);

                break;
            case "Top 5 produkty":

                choicebox_st_uz.setVisible(false);
                choicebox_st_prod.setVisible(true);
                st_pie_produkty.setVisible(false);
                st_chart_uzOba.setVisible(false);
                st_chart_uzTran.setVisible(false);
                st_chart_uzZam.setVisible(false);
                st_chart_plyty.setVisible(true);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(false);

                break;
        }
    }
    @FXML
    void pokazStatystykiII(){

        String stu=choicebox_st_uz.getSelectionModel().getSelectedItem();

        switch(stu){
            case "Oba":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria1=new XYChart.Series<>();
                seria1.setName("Zakupy");
                //wypełnienie danymi z BD

                String wynik[]= connection.uzyskajDane("Select id_uzytkownika, count(id) as ilosc from " +
                        "(select uzytkownik.id_uzytkownika, id_zamowienia as id from uzytkownik join zamowienie on uzytkownik.id_uzytkownika=zamowienie.id_uzytkownika where status_odbioru!='oczekujace'"+
                        "union "+
                        "select uzytkownik.id_uzytkownika, id_transakcji as id from uzytkownik join transakcja on uzytkownik.id_uzytkownika=transakcja.id_uzytkownika where status!='oczekująca') " +
                        "group by id_uzytkownika order by ilosc desc fetch first 5 rows only");


                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        seria1.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_user_zakupy.getData().clear();
                bar_user_zakupy.getData().addAll(seria1);

                st_chart_uzOba.setVisible(true);
                st_chart_uzTran.setVisible(false);
                st_chart_uzZam.setVisible(false);

                break;
            case "Transakcje":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria2=new XYChart.Series<>();
                seria2.setName("Transakcje");
                //wypełnienie danymi z BD

                wynik= connection.uzyskajDane("Select id_uzytkownika, count(id) as ilosc from " +
                        "(select uzytkownik.id_uzytkownika, id_transakcji as id from uzytkownik join transakcja on uzytkownik.id_uzytkownika=transakcja.id_uzytkownika where status!='oczekująca') " +
                        "group by id_uzytkownika order by ilosc desc fetch first 5 rows only");


                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        seria2.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_user_tran.getData().clear();
                bar_user_tran.getData().addAll(seria2);

                st_chart_uzOba.setVisible(false);
                st_chart_uzTran.setVisible(true);
                st_chart_uzZam.setVisible(false);

                break;
            case "Zamówienia":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria3=new XYChart.Series<>();
                seria3.setName("Zamówienia");
                //wypełnienie danymi z BD

                wynik= connection.uzyskajDane("Select id_uzytkownika, count(id) as ilosc from " +
                        "(select uzytkownik.id_uzytkownika, id_zamowienia as id from uzytkownik join zamowienie on uzytkownik.id_uzytkownika=zamowienie.id_uzytkownika where status_odbioru!='oczekujace') " +
                        "group by id_uzytkownika order by ilosc desc fetch first 5 rows only");


                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        seria3.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_user_zam.getData().clear();
                bar_user_zam.getData().addAll(seria3);

                st_chart_uzOba.setVisible(false);
                st_chart_uzTran.setVisible(false);
                st_chart_uzZam.setVisible(true);

                break;
        }
    }
    @FXML
    void pokazStatystykiIII(){

        String stp=choicebox_st_prod.getSelectionModel().getSelectedItem();

        switch(stp){
            case "Płyty główne":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria1=new XYChart.Series<>();
                seria1.setName("Ilość wybrań");

                //wypełnienie danymi z BD

                String wynik[]= connection.uzyskajDane("Select id_plyty_glownej, count(id_produktu) as id from produkt " +
                        "where id_plyty_glownej is not null and (id_zamowienia is not null or id_transakcji is not null) " +
                        "group by id_plyty_glownej order by id desc fetch first 5 rows only");

                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        seria1.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_plyty.getData().clear();
                bar_plyty.getData().addAll(seria1);

                st_chart_plyty.setVisible(true);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(false);

                break;
            case "Procesory":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria2=new XYChart.Series<>();
                seria2.setName("Ilość wybrań");

                //wypełnienie danymi z BD

                wynik= connection.uzyskajDane("Select id_procesora, count(id_produktu) as id from produkt " +
                        "where id_procesora is not null and (id_zamowienia is not null or id_transakcji is not null) " +
                        "group by id_procesora order by id desc fetch first 5 rows only");

                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        seria2.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_procesory.getData().clear();
                bar_procesory.getData().addAll(seria2);

                st_chart_plyty.setVisible(false);
                st_chart_procesory.setVisible(true);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(false);

                break;
            case "Karty graficzne":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria3=new XYChart.Series<>();
                seria3.setName("Ilość wybrań");

                //wypełnienie danymi z BD

                wynik= connection.uzyskajDane("Select id_karty_graficznej, count(id_produktu) as id from produkt " +
                        "where id_karty_graficznej is not null and (id_zamowienia is not null or id_transakcji is not null) " +
                        "group by id_karty_graficznej order by id desc fetch first 5 rows only");

                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        //pieU_list.add(new PieChart.Data(wynik[i],Double.parseDouble(wynik[i+1])));
                        seria3.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_karty.getData().clear();
                bar_karty.getData().addAll(seria3);

                st_chart_plyty.setVisible(false);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(true);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(false);

                break;
            case "Pamięci RAM":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria4=new XYChart.Series<>();
                seria4.setName("Ilość wybrań");

                //wypełnienie danymi z BD

                wynik= connection.uzyskajDane("Select id_pamieci_ram, count(id_produktu) as id from produkt " +
                        "where id_pamieci_ram is not null and (id_zamowienia is not null or id_transakcji is not null) " +
                        "group by id_pamieci_ram order by id desc fetch first 5 rows only");

                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        //pieU_list.add(new PieChart.Data(wynik[i],Double.parseDouble(wynik[i+1])));
                        seria4.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_pamiec.getData().clear();
                bar_pamiec.getData().addAll(seria4);

                st_chart_plyty.setVisible(false);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(true);
                st_chart_dyski.setVisible(false);

                break;
            case "Dyski":

                //utworzenie list do wypełniania odpowiednich typów
                XYChart.Series seria5=new XYChart.Series<>();
                seria5.setName("Ilość wybrań");

                //wypełnienie danymi z BD

                wynik= connection.uzyskajDane("Select id_dysku, count(id_produktu) as id from produkt " +
                        "where id_dysku is not null and (id_zamowienia is not null or id_transakcji is not null) " +
                        "group by id_dysku order by id desc fetch first 5 rows only");

                if(wynik.length<1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    if(dane.getCzyOffline()==0){
                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");
                    }

                }else{
                    for(int i=0;i<wynik.length;i+=2){
                        seria5.getData().add(new XYChart.Data<>(wynik[i], Double.parseDouble(wynik[i + 1])));
                    }
                }

                bar_dyski.getData().clear();
                bar_dyski.getData().addAll(seria5);

                st_chart_plyty.setVisible(false);
                st_chart_procesory.setVisible(false);
                st_chart_karty.setVisible(false);
                st_chart_pamiec.setVisible(false);
                st_chart_dyski.setVisible(true);

                break;
        }

    }

    //funkcje do wylogowania
    @FXML
    void wyloguj(MouseEvent event) throws IOException{

        dane.setIdZalogowanegoUzytkownika("0");

        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //funkcja do przejścia na profil użytkownika
    @FXML
    void goProfil(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("uzytkownik" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

