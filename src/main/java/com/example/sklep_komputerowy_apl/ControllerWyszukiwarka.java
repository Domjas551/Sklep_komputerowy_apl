package com.example.sklep_komputerowy_apl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerWyszukiwarka  implements Initializable{

    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();
    //komponenty
    @FXML
    private AnchorPane test;

    @FXML
    private Text testText;
    //elementy panelu wyszukiwania w zakładce płyty główne
    @FXML
    private TextField min_cena_plyty;
    @FXML
    private TextField max_cena_plyty;
    @FXML
    private CheckBox checkbox_plyty_producent_1;
    @FXML
    private CheckBox checkbox_plyty_producent_2;
    @FXML
    private CheckBox checkbox_plyty_producent_3;
    @FXML
    private CheckBox checkbox_plyty_gniazdo_1;
    @FXML
    private CheckBox checkbox_plyty_gniazdo_2;
    @FXML
    private CheckBox checkbox_plyty_gniazdo_3;
    @FXML
    private CheckBox checkbox_plyty_chipset_1;
    @FXML
    private CheckBox checkbox_plyty_chipset_2;
    @FXML
    private CheckBox checkbox_plyty_chipset_3;
    @FXML
    private CheckBox checkbox_plyty_pamiec_1;
    @FXML
    private CheckBox checkbox_plyty_pamiec_2;
    @FXML
    private CheckBox checkbox_plyty_pamiec_3;
    @FXML
    private CheckBox checkbox_plyty_banki_1;
    @FXML
    private CheckBox checkbox_plyty_banki_2;
    @FXML
    private CheckBox checkbox_plyty_banki_3;
    @FXML
    private CheckBox checkbox_plyty_banki_4;
    @FXML
    private ChoiceBox<String> ttyb_plyty;
    @FXML
    private Button button_szukaj_plyty;

    //tabela w zakładce płyty
    @FXML
    private TableView<TablePlyty> table_plyty;
    @FXML
    private TableColumn<TablePlyty, Double> tp_cena;
    @FXML
    private TableColumn<TablePlyty, String> tp_chipset;
    @FXML
    private TableColumn<TablePlyty, String> tp_nazwa;

    //elementy panelu wyszukiwania w zakładce procesory
    @FXML
    private TextField min_cena_procesory;
    @FXML
    private TextField max_cena_procesory;
    @FXML
    private CheckBox checkbox_procesory_producent_1;
    @FXML
    private CheckBox checkbox_procesory_producent_2;
    @FXML
    private CheckBox checkbox_procesory_rodzina_1;
    @FXML
    private CheckBox checkbox_procesory_rodzina_2;
    @FXML
    private CheckBox checkbox_procesory_rodzina_3;
    @FXML
    private CheckBox checkbox_procesory_gniazdo_1;
    @FXML
    private CheckBox checkbox_procesory_gniazdo_2;
    @FXML
    private CheckBox checkbox_procesory_rdzenie_1;
    @FXML
    private CheckBox checkbox_procesory_rdzenie_2;
    @FXML
    private CheckBox checkbox_procesory_rdzenie_3;
    @FXML
    private Button button_szukaj_procesory;
    @FXML
    private ChoiceBox<String> ttyb_procesory;

    //tabela w zakładce procesor
    @FXML
    private TableView<TableProcesory> table_procesory;
    @FXML
    private TableColumn<TableProcesory, Double> tpr_cena;
    @FXML
    private TableColumn<TableProcesory, String> tpr_seria;
    @FXML
    private TableColumn<TableProcesory, String> tpr_nazwa;

    //elementy panelu wyszukiwania w zakładce karty graficzne
    @FXML
    private TextField min_cena_karty;
    @FXML
    private TextField max_cena_karty;
    @FXML
    private CheckBox checkbox_karty_producent_1;
    @FXML
    private CheckBox checkbox_karty_producent_2;
    @FXML
    private CheckBox checkbox_karty_producent_3;
    @FXML
    private CheckBox checkbox_karty_producent_4;
    @FXML
    private CheckBox checkbox_karty_uklad_1;
    @FXML
    private CheckBox checkbox_karty_uklad_2;
    @FXML
    private CheckBox checkbox_karty_uklad_3;
    @FXML
    private CheckBox checkbox_karty_uklad_4;
    @FXML
    private CheckBox checkbox_karty_zlacze_1;
    @FXML
    private CheckBox checkbox_karty_zlacze_2;
    @FXML
    private CheckBox checkbox_karty_zlacze_3;
    @FXML
    private CheckBox checkbox_karty_rodzaj_1;
    @FXML
    private CheckBox checkbox_karty_rodzaj_2;
    @FXML
    private CheckBox checkbox_karty_rodzaj_3;
    @FXML
    private CheckBox checkbox_karty_rodzaj_4;
    @FXML
    private Button button_szukaj_karty;
    @FXML
    private ChoiceBox<String> ttyb_karty;

    //tabela w zakładce karty graficzne
    @FXML
    private TableView<TableKarty> table_karty;
    @FXML
    private TableColumn<TableKarty, Double> tk_cena;
    @FXML
    private TableColumn<TableKarty, String> tk_uklad;
    @FXML
    private TableColumn<TableKarty, String> tk_nazwa;

    //elementy panelu wyszukiwania w zakładce pamięć RAM
    @FXML
    private TextField min_cena_pamiec;
    @FXML
    private TextField max_cena_pamiec;
    @FXML
    private CheckBox checkbox_pamiec_producent_1;
    @FXML
    private CheckBox checkbox_pamiec_producent_2;
    @FXML
    private CheckBox checkbox_pamiec_producent_3;
    @FXML
    private CheckBox checkbox_pamiec_producent_4;
    @FXML
    private CheckBox checkbox_pamiec_producent_5;
    @FXML
    private CheckBox checkbox_pamiec_rodzaj_1;
    @FXML
    private CheckBox checkbox_pamiec_rodzaj_2;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_1;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_2;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_3;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_4;
    @FXML
    private CheckBox checkbox_pamiec_pojemnosc_5;
    @FXML
    private ChoiceBox<String> ttyb_pamiec;
    @FXML
    private Button button_szukaj_pamiec;

    //tabela w zakładce karty ram
    @FXML
    private TableView<TableRam> table_ram;
    @FXML
    private TableColumn<TableRam, Double> tr_cena;
    @FXML
    private TableColumn<TableRam, String> tr_rodzaj;
    @FXML
    private TableColumn<TableRam, String> tr_nazwa;

    //elementy panelu wyszukiwania w zakładce dyski
    @FXML
    private TextField max_cena_dyski;
    @FXML
    private TextField min_cena_dyski;
    @FXML
    private CheckBox checkbox_dyski_producent_1;
    @FXML
    private CheckBox checkbox_dyski_producent_2;
    @FXML
    private CheckBox checkbox_dyski_producent_3;
    @FXML
    private CheckBox checkbox_dyski_typ_1;
    @FXML
    private CheckBox checkbox_dyski_typ_2;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_1;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_2;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_3;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_4;
    @FXML
    private CheckBox checkbox_dyski_pojemnosc_5;
    @FXML
    private ChoiceBox<String> ttyb_dyski;
    @FXML
    private Button button_szukaj_dyski;

    //tabela w zakładce dyski
    @FXML
    private TableView<TableDyski> table_dyski;
    @FXML
    private TableColumn<TableDyski, Double> td_cena;
    @FXML
    private TableColumn<TableDyski, String> td_pojemnosc;
    @FXML
    private TableColumn<TableDyski, String> td_nazwa;

    //elementy panelu wyszukiwania w zakładce zestawy
    @FXML
    private TextField max_cena_zestawy;
    @FXML
    private TextField min_cena_zestawy;
    @FXML
    private Button button_szukaj_zestawy;

    //tabela w zakładce zestawy
    @FXML
    private TableView<TableZestawy> table_zestawy;
    @FXML
    private TableColumn<TableZestawy, Double> tz_cena;
    @FXML
    private TableColumn<TableZestawy, String> tz_nazwa;

    //główne przyciski
    @FXML
    private Button button_dyski;
    @FXML
    private Button button_kartyGraf;
    @FXML
    private Button button_pamRam;
    @FXML
    private Button button_plyty;
    @FXML
    private Button button_procesory;
    @FXML
    private Button button_zestawy;

    //panele
    @FXML
    private AnchorPane dyski;
    @FXML
    private AnchorPane kartyGraficzne;
    @FXML
    private AnchorPane pamiecRam;
    @FXML
    private AnchorPane plytyGl;
    @FXML
    private AnchorPane procesory;
    @FXML
    private AnchorPane zestawy;



    //funkcje

    //inicializacja wyszukiwarki
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //wprowadzenie wartości do choiceBoxów
        ttyb_plyty.getItems().addAll("Zakup","Zamówienie");
        ttyb_procesory.getItems().addAll("Zakup","Zamówienie");
        ttyb_karty.getItems().addAll("Zakup","Zamówienie");
        ttyb_pamiec.getItems().addAll("Zakup","Zamówienie");
        ttyb_dyski.getItems().addAll("Zakup","Zamówienie");

        //ustawienie tabel
        //płyty
        tp_nazwa.setCellValueFactory(new PropertyValueFactory<TablePlyty,String>("nazwa"));
        tp_chipset.setCellValueFactory(new PropertyValueFactory<TablePlyty,String>("chipset"));
        tp_cena.setCellValueFactory(new PropertyValueFactory<TablePlyty,Double>("cena"));
        //procesory
        tpr_nazwa.setCellValueFactory(new PropertyValueFactory<TableProcesory,String>("nazwa"));
        tpr_seria.setCellValueFactory(new PropertyValueFactory<TableProcesory,String>("seria"));
        tpr_cena.setCellValueFactory(new PropertyValueFactory<TableProcesory,Double>("cena"));
        //karty graficzne
        tk_nazwa.setCellValueFactory(new PropertyValueFactory<TableKarty,String>("nazwa"));
        tk_uklad.setCellValueFactory(new PropertyValueFactory<TableKarty,String>("uklad"));
        tk_cena.setCellValueFactory(new PropertyValueFactory<TableKarty,Double>("cena"));
        //pamiec ram
        tr_nazwa.setCellValueFactory(new PropertyValueFactory<TableRam,String>("nazwa"));
        tr_rodzaj.setCellValueFactory(new PropertyValueFactory<TableRam,String>("rodzaj"));
        tr_cena.setCellValueFactory(new PropertyValueFactory<TableRam,Double>("cena"));
        //dyski
        td_nazwa.setCellValueFactory(new PropertyValueFactory<TableDyski,String>("nazwa"));
        td_pojemnosc.setCellValueFactory(new PropertyValueFactory<TableDyski,String>("pojemnosc"));
        td_cena.setCellValueFactory(new PropertyValueFactory<TableDyski,Double>("cena"));
        //zestawy
        tz_nazwa.setCellValueFactory(new PropertyValueFactory<TableZestawy,String>("nazwa"));
        tz_cena.setCellValueFactory(new PropertyValueFactory<TableZestawy,Double>("cena"));

        //wypełnienie tabel
        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TablePlyty> tp_list= FXCollections.observableArrayList();
        ObservableList<TableProcesory> tpr_list= FXCollections.observableArrayList();
        ObservableList<TableKarty> tk_list= FXCollections.observableArrayList();
        ObservableList<TableRam> tr_list= FXCollections.observableArrayList();
        ObservableList<TableDyski> td_list= FXCollections.observableArrayList();
        ObservableList<TableZestawy> tz_list= FXCollections.observableArrayList();
        //plyty główne
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie().equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,chipset,cena from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej");
            for(int i=0;i<wynik.length;i+=3){
                tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
            }

            table_plyty.setItems(tp_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tpr_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_plyty.setItems(tp_list);
        }

        //procesory
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie().equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,seria,cena from produkt " +
                    "join procesor on produkt.id_procesora=procesor.id_procesora");
            for(int i=0;i<wynik.length;i+=3){
                tpr_list.add(new TableProcesory(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
            }

            table_procesory.setItems(tpr_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tpr_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tpr_list.add(new TableProcesory(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_procesory.setItems(tpr_list);
        }

        //karty graficzne
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie().equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,uklad_graficzny,cena from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficzne=karta_graficzna.id_karty_graficznej");
            for(int i=0;i<wynik.length;i+=3){
                tk_list.add(new TableKarty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
            }

            table_karty.setItems(tk_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tk_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tk_list.add(new TableKarty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_karty.setItems(tk_list);
        }
        //pamięć ram
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie().equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,rodzaj_pamieci,cena from produkt " +
                    "join procesor on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram");
            for(int i=0;i<wynik.length;i+=3){
                tr_list.add(new TableRam(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
            }

            table_ram.setItems(tr_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tr_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tr_list.add(new TableRam(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_ram.setItems(tr_list);
        }

        //dyski
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie().equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,pojemnosc,cena from produkt " +
                    "join procesor on produkt.id_dysku=dysk.id_dysku");
            for(int i=0;i<wynik.length;i+=3){
                td_list.add(new TableDyski(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
            }

            table_dyski.setItems(td_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                td_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    td_list.add(new TableDyski(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_dyski.setItems(td_list);
        }
        //zestawy
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie().equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_zestawu,cena from zestaw ");
            for(int i=0;i<wynik.length;i+=3){
                tz_list.add(new TableZestawy(wynik[i],Double.parseDouble(wynik[i+1])));
            }

            table_zestawy.setItems(tz_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tz_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tz_list.add(new TableZestawy(wynik[i],Double.parseDouble(wynik[i+1])));
                }
            }

            table_zestawy.setItems(tz_list);
        }
    }

    //funkcja wyświetlająca strone dysków w panelu wyszukiwarki
    @FXML
    void showDyski(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(true);
        zestawy.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("dyski");

    }

    //funkcja wyświetlająca strone kart graficznych w panelu wyszukiwarki
    @FXML
    void showKarty(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(true);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("karty");
    }

    //funkcja wyświetlająca strone pamięci ram w panelu wyszukiwarki
    @FXML
    void showPamiecRam(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(true);
        dyski.setVisible(false);
        zestawy.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("ram");
    }

    //funkcja wyświetlająca strone płyt głównych w panelu wyszukiwarki
    @FXML
    void showPlyty(MouseEvent event) {
        plytyGl.setVisible(true);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("plyty");
    }

    //funkcja wyświetlająca strone procesorów w panelu wyszukiwarki
    @FXML
    void showProcesory(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(true);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("procesory");
    }

    //funkcja wyświetlająca strone zestawów w panelu wyszukiwarki
    @FXML
    void showZestawy(MouseEvent event) {
        plytyGl.setVisible(false);
        procesory.setVisible(false);
        kartyGraficzne.setVisible(false);
        pamiecRam.setVisible(false);
        dyski.setVisible(false);
        zestawy.setVisible(true);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("zestawy");
    }

    //odsianie płyt głównych
    @FXML
    void odsiej_plyty(MouseEvent event) {

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_produktu,chipset,cena from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_plyty.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_plyty.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_plyty.getText().contains(",")){
                    min_cena_plyty.setText(min_cena_plyty.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_plyty.getText()+" and ";
            }
            if(max_cena_plyty.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_plyty.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_plyty.getText().contains(",")){
                    max_cena_plyty.setText(max_cena_plyty.getText().replaceAll(",","."));
                }
                if(!min_cena_plyty.getText().equals("") && Double.parseDouble(max_cena_plyty.getText())<Double.parseDouble(min_cena_plyty.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_plyty.getText();
            }

            //dodanie ograniczenia na producenta
            s1=" and producent ";
            if(checkbox_plyty_producent_1.isSelected()){
                st.add("'Asus'");
            }
            if(checkbox_plyty_producent_2.isSelected()){
                st.add("'Gigabyte'");
            }
            if(checkbox_plyty_producent_3.isSelected()){
                st.add("'MSI'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na gniazdo procesora
            s1=" and gniazdo_procesora ";
            if(checkbox_plyty_gniazdo_1.isSelected()){
                st.add("'Socket H4'");
            }
            if(checkbox_plyty_gniazdo_2.isSelected()){
                st.add("'Socket H'");
            }
            if(checkbox_plyty_gniazdo_3.isSelected()){
                st.add("'Socket R'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na chipset
            s1=" and chipset ";
            if(checkbox_plyty_chipset_1.isSelected()){
                st.add("'Typ Z'");
            }
            if(checkbox_plyty_chipset_2.isSelected()){
                st.add("'Typ X'");
            }
            if(checkbox_plyty_chipset_3.isSelected()){
                st.add("'Typ C'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na typ obsługiwanej pamięci
            s1=" and typ_obslugiwanej_pamieci ";
            if(checkbox_plyty_pamiec_1.isSelected()){
                st.add("'DDR2'");
            }
            if(checkbox_plyty_pamiec_2.isSelected()){
                st.add("'DDR3'");
            }
            if(checkbox_plyty_pamiec_3.isSelected()){
                st.add("'DDR4'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na liczbe banków pamięci
            s1=" and liczba_bankow_pamieci ";
            if(checkbox_plyty_banki_1.isSelected()){
                st.add("'6'");
            }
            if(checkbox_plyty_banki_2.isSelected()){
                st.add("'11'");
            }
            if(checkbox_plyty_banki_3.isSelected()){
                st.add("'29'");
            }
            if(checkbox_plyty_banki_4.isSelected()){
                st.add("'30'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            if(ttyb_plyty.getSelectionModel().getSelectedItem()!=null && ttyb_plyty.getSelectionModel().getSelectedItem().equals("Zakup")){
                zapytanie+=" and id_transakcji is null";
            }

            //wypełnienie tabel
            ObservableList<TablePlyty> tp_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tp_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_plyty.setItems(tp_list);
        }catch(MaxCenaException m){
            System.out.println(m);
        }catch(CenaTypeException c){
            System.out.println(c);
        }
    }

    //odsianie procesorów
    @FXML
    void odsiej_procesory(MouseEvent event) {

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_produktu,chipset,cena from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_plyty.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_plyty.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_plyty.getText().contains(",")){
                    min_cena_plyty.setText(min_cena_plyty.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_plyty.getText()+" and ";
            }
            if(max_cena_plyty.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_plyty.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_plyty.getText().contains(",")){
                    max_cena_plyty.setText(max_cena_plyty.getText().replaceAll(",","."));
                }
                if(!min_cena_plyty.getText().equals("") && Double.parseDouble(max_cena_plyty.getText())<Double.parseDouble(min_cena_plyty.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_plyty.getText();
            }

            //dodanie ograniczenia na producenta
            s1=" and producent ";
            if(checkbox_plyty_producent_1.isSelected()){
                st.add("'Asus'");
            }
            if(checkbox_plyty_producent_2.isSelected()){
                st.add("'Gigabyte'");
            }
            if(checkbox_plyty_producent_3.isSelected()){
                st.add("'MSI'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na gniazdo procesora
            s1=" and gniazdo_procesora ";
            if(checkbox_plyty_gniazdo_1.isSelected()){
                st.add("'Socket H4'");
            }
            if(checkbox_plyty_gniazdo_2.isSelected()){
                st.add("'Socket H'");
            }
            if(checkbox_plyty_gniazdo_3.isSelected()){
                st.add("'Socket R'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na chipset
            s1=" and chipset ";
            if(checkbox_plyty_chipset_1.isSelected()){
                st.add("'Typ Z'");
            }
            if(checkbox_plyty_chipset_2.isSelected()){
                st.add("'Typ X'");
            }
            if(checkbox_plyty_chipset_3.isSelected()){
                st.add("'Typ C'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na typ obsługiwanej pamięci
            s1=" and typ_obslugiwanej_pamieci ";
            if(checkbox_plyty_pamiec_1.isSelected()){
                st.add("'DDR2'");
            }
            if(checkbox_plyty_pamiec_2.isSelected()){
                st.add("'DDR3'");
            }
            if(checkbox_plyty_pamiec_3.isSelected()){
                st.add("'DDR4'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            //dodanie ograniczenia na liczbe banków pamięci
            s1=" and liczba_bankow_pamieci ";
            if(checkbox_plyty_banki_1.isSelected()){
                st.add("'6'");
            }
            if(checkbox_plyty_banki_2.isSelected()){
                st.add("'11'");
            }
            if(checkbox_plyty_banki_3.isSelected()){
                st.add("'29'");
            }
            if(checkbox_plyty_banki_4.isSelected()){
                st.add("'30'");
            }

            if(st.size()>0){
                s1+="in(";
                if(st.size()==1){
                    s1+=st.get(0)+")";
                }else{
                    for(int i = 0; i< st.size()-1; i++){
                        s1+=st.get(i)+",";
                    }
                    s1+=st.get(st.size()-1);
                    s1+=")";
                }
                zapytanie+=s1;
                st.clear();
            }

            if(ttyb_plyty.getSelectionModel().getSelectedItem()!=null && ttyb_plyty.getSelectionModel().getSelectedItem().equals("Zakup")){
                zapytanie+=" and id_transakcji is null";
            }

            //wypełnienie tabel
            ObservableList<TablePlyty> tp_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tp_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_plyty.setItems(tp_list);
        }catch(MaxCenaException m){
            System.out.println(m);
        }catch(CenaTypeException c){
            System.out.println(c);
        }

        min_cena_procesory.getText();
        max_cena_procesory.getText();
        checkbox_procesory_producent_1.isSelected();
        checkbox_procesory_producent_2.isSelected();
        checkbox_procesory_rodzina_1.isSelected();
        checkbox_procesory_rodzina_2.isSelected();
        checkbox_procesory_rodzina_3.isSelected();
        checkbox_procesory_gniazdo_1.isSelected();
        checkbox_procesory_gniazdo_2.isSelected();
        checkbox_procesory_rdzenie_1.isSelected();
        checkbox_procesory_rdzenie_2.isSelected();
        checkbox_procesory_rdzenie_3.isSelected();
        ttyb_procesory.getSelectionModel().getSelectedItem();
    }

    //odsianie kart graficznych
    @FXML
    void odsiej_karty(MouseEvent event) {
        min_cena_karty.getText();
        max_cena_karty.getText();
        checkbox_karty_producent_1.isSelected();
        checkbox_karty_producent_2.isSelected();
        checkbox_karty_producent_3.isSelected();
        checkbox_karty_producent_4.isSelected();
        checkbox_karty_uklad_1.isSelected();
        checkbox_karty_uklad_2.isSelected();
        checkbox_karty_uklad_3.isSelected();
        checkbox_karty_uklad_4.isSelected();
        checkbox_karty_zlacze_1.isSelected();
        checkbox_karty_zlacze_2.isSelected();
        checkbox_karty_zlacze_3.isSelected();
        checkbox_karty_rodzaj_1.isSelected();
        checkbox_karty_rodzaj_2.isSelected();
        checkbox_karty_rodzaj_3.isSelected();
        checkbox_karty_rodzaj_4.isSelected();
        ttyb_karty.getSelectionModel().getSelectedItem();
    }

    //odsianie pamięci RAM
    @FXML
    void odsiej_pamiec(MouseEvent event) {
        min_cena_pamiec.getText();
        max_cena_pamiec.getText();
        checkbox_pamiec_producent_1.isSelected();
        checkbox_pamiec_producent_2.isSelected();
        checkbox_pamiec_producent_3.isSelected();
        checkbox_pamiec_producent_4.isSelected();
        checkbox_pamiec_producent_5.isSelected();
        checkbox_pamiec_rodzaj_1.isSelected();
        checkbox_pamiec_rodzaj_2.isSelected();
        checkbox_pamiec_pojemnosc_1.isSelected();
        checkbox_pamiec_pojemnosc_2.isSelected();
        checkbox_pamiec_pojemnosc_3.isSelected();
        checkbox_pamiec_pojemnosc_4.isSelected();
        checkbox_pamiec_pojemnosc_5.isSelected();
        ttyb_pamiec.getSelectionModel().getSelectedItem();
    }

    //odsianie dysków
    @FXML
    void odsiej_dyski(MouseEvent event) {
        min_cena_dyski.getText();
        max_cena_dyski.getText();
        checkbox_dyski_producent_1.isSelected();
        checkbox_dyski_producent_2.isSelected();
        checkbox_dyski_producent_3.isSelected();
        checkbox_dyski_typ_1.isSelected();
        checkbox_dyski_typ_2.isSelected();
        checkbox_dyski_pojemnosc_1.isSelected();
        checkbox_dyski_pojemnosc_2.isSelected();
        checkbox_dyski_pojemnosc_3.isSelected();
        checkbox_dyski_pojemnosc_4.isSelected();
        checkbox_dyski_pojemnosc_5.isSelected();
        ttyb_dyski.getSelectionModel().getSelectedItem();
    }

    //odsianie zestawów
    @FXML
    void odsiej_zestawy(MouseEvent event) throws IOException {
        min_cena_zestawy.getText();
        max_cena_zestawy.getText();

        String[] wynik= connection.uzyskajDane("Select * from produkt where id_produktu=7");

        for(int i=0;i<wynik.length;i++){
            System.out.println(wynik[i]);
        }

    }

    //otworzenie strony konkretnego produktu
    @FXML
    void showProduktPlyty(MouseEvent event) {
        Integer index=table_plyty.getSelectionModel().getSelectedIndex();

        System.out.println(tp_nazwa.getCellData(index));
        System.out.println(tp_chipset.getCellData(index));
        System.out.println(tp_cena.getCellData(index));
    }
}