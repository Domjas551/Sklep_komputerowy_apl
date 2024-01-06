package com.example.sklep_komputerowy_apl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerWyszukiwarka  implements Initializable{

    //podłączenie do klas z danymi oraz komunikacją z BD
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;

    //komponenty
    @FXML
    private TextField wyszukajText;
    private Button wyszukajButton;
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
    @FXML
    private Button button_wyczysc_plyty;

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
    @FXML
    private Button button_wyczysc_procesory;

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
    @FXML
    private Button button_wyczysc_karty;

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
    @FXML
    private Button button_wyczysc_pamiec;

    //tabela w zakładce karty ram
    @FXML
    private TableView<TableRam> table_ram;
    @FXML
    private TableColumn<TableRam, Double> tr_cena;
    @FXML
    private TableColumn<TableRam, String> tr_pamiec;
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
    @FXML
    private Button button_wyczysc_dyski;

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
    @FXML
    private AnchorPane anchor_zaloguj;
    @FXML
    private AnchorPane anchor_wyloguj;
    @FXML
    private Button button_value_of_name;

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
        tr_pamiec.setCellValueFactory(new PropertyValueFactory<TableRam,String>("rodzaj"));
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
        if(dane.uzyskajPoprzednieZapytanie("plyty").equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,chipset,cena from produkt " +
                    "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej");

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników

                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tp_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }


            table_plyty.setItems(tp_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie("plyty"));
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników

                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tpr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_plyty.setItems(tp_list);
        }

        //procesory
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie("procesory").equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,rodzina,cena from produkt " +
                    "join procesor on produkt.id_procesora=procesor.id_procesora");

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tpr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tpr_list.add(new TableProcesory(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_procesory.setItems(tpr_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie("procesory"));
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tpr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tpr_list.add(new TableProcesory(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_procesory.setItems(tpr_list);
        }

        //karty graficzne
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie("karty").equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,uklad_graficzny,cena from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej");

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tk_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tk_list.add(new TableKarty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_karty.setItems(tk_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie("karty"));
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tk_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tk_list.add(new TableKarty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_karty.setItems(tk_list);
        }
        //pamięć ram
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie("ram").equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,rodzaj_pamieci,cena from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram");

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tr_list.add(new TableRam(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_ram.setItems(tr_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie("ram"));
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tr_list.add(new TableRam(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_ram.setItems(tr_list);
        }

        //dyski
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie("dyski").equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_produktu,pojemnosc,cena from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku");

            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    td_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    td_list.add(new TableDyski(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_dyski.setItems(td_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie("dyski"));
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    td_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    td_list.add(new TableDyski(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_dyski.setItems(td_list);
        }
        //zestawy
        //domyślnymi danymi
        if(dane.uzyskajPoprzednieZapytanie("zestawy").equals("")){
            String wynik[]= connection.uzyskajDane("Select distinct nazwa_zestawu,cena from zestaw");

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=2){
                    tz_list.add(new TableZestawy(wynik[i],Double.parseDouble(wynik[i+1])));
                }
            }

            table_zestawy.setItems(tz_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.uzyskajPoprzednieZapytanie("zestawy"));
            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=2){
                    tz_list.add(new TableZestawy(wynik[i],Double.parseDouble(wynik[i+1])));
                }
            }

            table_zestawy.setItems(tz_list);
        }

        //wyświetlenie aktualnej strony wyszukiwarki
        switch(dane.getWyszukiwarka_activePage()){
            case "plyty":
                plytyGl.setVisible(true);
                procesory.setVisible(false);
                kartyGraficzne.setVisible(false);
                pamiecRam.setVisible(false);
                dyski.setVisible(false);
                zestawy.setVisible(false);
                break;
            case "procesory":
                plytyGl.setVisible(false);
                procesory.setVisible(true);
                kartyGraficzne.setVisible(false);
                pamiecRam.setVisible(false);
                dyski.setVisible(false);
                zestawy.setVisible(false);
                break;
            case "karty":
                plytyGl.setVisible(false);
                procesory.setVisible(false);
                kartyGraficzne.setVisible(true);
                pamiecRam.setVisible(false);
                dyski.setVisible(false);
                zestawy.setVisible(false);
                break;
            case "ram":
                plytyGl.setVisible(false);
                procesory.setVisible(false);
                kartyGraficzne.setVisible(false);
                pamiecRam.setVisible(true);
                dyski.setVisible(false);
                zestawy.setVisible(false);
                break;
            case "dyski":
                plytyGl.setVisible(false);
                procesory.setVisible(false);
                kartyGraficzne.setVisible(false);
                pamiecRam.setVisible(false);
                dyski.setVisible(true);
                zestawy.setVisible(false);
                break;
            case "zestawy":
                plytyGl.setVisible(false);
                procesory.setVisible(false);
                kartyGraficzne.setVisible(false);
                pamiecRam.setVisible(false);
                dyski.setVisible(false);
                zestawy.setVisible(true);
                break;
        }

        //wyświetlenie odpowiedniej przystawki zaloguj/wyloguj
        if(dane.getIdZalogowanegoUzytkownika().equals("0")){
            anchor_wyloguj.setVisible(false);
            anchor_zaloguj.setVisible(true);
        }else{
            anchor_wyloguj.setVisible(true);
            anchor_zaloguj.setVisible(false);
        }

        //todo rozmiar button_value_of_name

        //wyświetlenie nazwy użytkownika
        String wynik = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + dane.getIdZalogowanegoUzytkownika())[0];
        button_value_of_name.setText(wynik);
    }

    //wyświetlanie alertów
    public void informationAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(m);
        alert.showAndWait();
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

    //odsianie po nazwie produktu na aktualnie otwartej stronie
    @FXML
    void odsiej_po_nazwie(MouseEvent event){
        String nazwa=wyszukajText.getText();

        switch(dane.getWyszukiwarka_activePage()) {
            case "plyty":
                if (!nazwa.equals("")) {
                    String zapytanie = "Select distinct nazwa_produktu,chipset,cena from produkt " +
                            "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej where " +
                            "nazwa_produktu like('%" + nazwa + "%')";

                    //wypełnienie tabel
                    ObservableList<TablePlyty> tp_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tp_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tp_list.add(new TablePlyty(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_plyty.setItems(tp_list);
                } else {
                    String zapytanie = "Select distinct nazwa_produktu,chipset,cena from produkt " +
                            "join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej";

                    //wypełnienie tabel
                    ObservableList<TablePlyty> tp_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie("");

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tp_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tp_list.add(new TablePlyty(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_plyty.setItems(tp_list);
                }
                break;
            case "procesory":
                if (!nazwa.equals("")) {
                    String zapytanie = "Select distinct nazwa_produktu,rodzina,cena from produkt " +
                            "join procesor on produkt.id_procesora=procesor.id_procesora where " +
                            "nazwa_produktu like('%" + nazwa + "%')";

                    //wypełnienie tabel
                    ObservableList<TableProcesory> tpr_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tpr_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tpr_list.add(new TableProcesory(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_procesory.setItems(tpr_list);
                } else {
                    String zapytanie = "Select distinct nazwa_produktu,rodzina,cena from produkt " +
                            "join procesor on produkt.id_procesora=procesor.id_procesora";

                    //wypełnienie tabel
                    ObservableList<TableProcesory> tpr_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie("");

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tpr_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tpr_list.add(new TableProcesory(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_procesory.setItems(tpr_list);
                }
                break;
            case "karty":
                if (!nazwa.equals("")) {
                    String zapytanie = "Select distinct nazwa_produktu,uklad_graficzny,cena from produkt " +
                            "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej where " +
                            "nazwa_produktu like('%" + nazwa + "%')";

                    //wypełnienie tabel
                    ObservableList<TableKarty> tk_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tk_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tk_list.add(new TableKarty(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_karty.setItems(tk_list);
                } else {
                    String zapytanie = "Select distinct nazwa_produktu,uklad_graficzny,cena from produkt " +
                            "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej";

                    //wypełnienie tabel
                    ObservableList<TableKarty> tk_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie("");

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tk_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tk_list.add(new TableKarty(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_karty.setItems(tk_list);
                }
                break;
            case "ram":
                if (!nazwa.equals("")) {
                    String zapytanie = "Select distinct nazwa_produktu,rodzaj_pamieci,cena from produkt " +
                            "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram where " +
                            "nazwa_produktu like('%" + nazwa + "%')";

                    //wypełnienie tabel
                    ObservableList<TableRam> tr_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tr_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tr_list.add(new TableRam(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_ram.setItems(tr_list);
                } else {
                    String zapytanie = "Select distinct nazwa_produktu,rodzaj_pamieci,cena from produkt " +
                            "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram";

                    //wypełnienie tabel
                    ObservableList<TableRam> tr_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie("");

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tr_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            tr_list.add(new TableRam(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_ram.setItems(tr_list);
                }
                break;
            case "dyski":
                if (!nazwa.equals("")) {
                    String zapytanie = "Select distinct nazwa_produktu,pojemnosc,cena from produkt " +
                            "join dysk on produkt.id_dysku=dysk.id_dysku where " +
                            "nazwa_produktu like('%" + nazwa + "%')";

                    //wypełnienie tabel
                    ObservableList<TableDyski> td_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        td_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            td_list.add(new TableDyski(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_dyski.setItems(td_list);
                } else {
                    String zapytanie = "Select distinct nazwa_produktu,pojemnosc,cena from produkt " +
                            "join dysk on produkt.id_dysku=dysk.id_dysku";

                    //wypełnienie tabel
                    ObservableList<TableDyski> td_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie("");

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        td_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 3) {
                            td_list.add(new TableDyski(wynik[i], wynik[i + 1], Double.parseDouble(wynik[i + 2])));
                        }
                    }

                    table_dyski.setItems(td_list);
                }
                break;
            case "zestawy":
                if (!nazwa.equals("")) {
                    String zapytanie = "Select distinct nazwa_zestawu,cena from zestaw where " +
                            "nazwa_zestawu like('%" + nazwa + "%')";

                    //wypełnienie tabel
                    ObservableList<TableZestawy> tz_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tz_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 2) {
                            tz_list.add(new TableZestawy(wynik[i], Double.parseDouble(wynik[i + 1])));
                        }
                    }

                    table_zestawy.setItems(tz_list);
                } else {
                    String zapytanie = "Select distinct nazwa_zestawu,cena from zestaw";

                    //wypełnienie tabel
                    ObservableList<TableZestawy> tz_list = FXCollections.observableArrayList();
                    String wynik[] = connection.uzyskajDane(zapytanie);

                    //zapisanie ostatio wykonanego zapytania
                    dane.zapiszZapytanie(zapytanie);

                    if (wynik.length <= 1) {
                        //gdy zapytanie nie zwróciło żądnych wyników

                        //wyświetlenie alertu informacyjnego
                        informationAlert("Brak danych do wyświetlenia");

                        tz_list.clear();
                    } else {
                        for (int i = 0; i < wynik.length; i += 2) {
                            tz_list.add(new TableZestawy(wynik[i], Double.parseDouble(wynik[i + 1])));
                        }
                    }

                    table_zestawy.setItems(tz_list);
                }
                break;

        }

    }

    //czyści kryteria wyszukiwania
    @FXML
    void wyczysc_kryteria(MouseEvent event){

        switch(dane.getWyszukiwarka_activePage()){
            //wyczyszczenie kryteriów płyt
            case "plyty":
                min_cena_plyty.setText("");
                max_cena_plyty.setText("");
                checkbox_plyty_producent_1.setSelected(false);
                checkbox_plyty_producent_2.setSelected(false);
                checkbox_plyty_producent_3.setSelected(false);
                checkbox_plyty_gniazdo_1.setSelected(false);
                checkbox_plyty_gniazdo_2.setSelected(false);
                checkbox_plyty_gniazdo_3.setSelected(false);
                checkbox_plyty_chipset_1.setSelected(false);
                checkbox_plyty_chipset_2.setSelected(false);
                checkbox_plyty_chipset_3.setSelected(false);
                checkbox_plyty_pamiec_1.setSelected(false);
                checkbox_plyty_pamiec_2.setSelected(false);
                checkbox_plyty_pamiec_3.setSelected(false);
                checkbox_plyty_banki_1.setSelected(false);
                checkbox_plyty_banki_2.setSelected(false);
                checkbox_plyty_banki_3.setSelected(false);
                checkbox_plyty_banki_4.setSelected(false);
                break;
            case "procesory":
                min_cena_procesory.setText("");
                max_cena_procesory.setText("");
                checkbox_procesory_producent_1.setSelected(false);
                checkbox_procesory_producent_2.setSelected(false);
                checkbox_procesory_rodzina_1.setSelected(false);
                checkbox_procesory_rodzina_2.setSelected(false);
                checkbox_procesory_rodzina_3.setSelected(false);
                checkbox_procesory_gniazdo_1.setSelected(false);
                checkbox_procesory_gniazdo_2.setSelected(false);
                checkbox_procesory_rdzenie_1.setSelected(false);
                checkbox_procesory_rdzenie_2.setSelected(false);
                checkbox_procesory_rdzenie_3.setSelected(false);
            break;
            case "karty":
                min_cena_karty.setText("");
                max_cena_karty.setText("");
                checkbox_karty_producent_1.setSelected(false);
                checkbox_karty_producent_2.setSelected(false);
                checkbox_karty_producent_3.setSelected(false);
                checkbox_karty_producent_4.setSelected(false);
                checkbox_karty_uklad_1.setSelected(false);
                checkbox_karty_uklad_2.setSelected(false);
                checkbox_karty_uklad_3.setSelected(false);
                checkbox_karty_uklad_4.setSelected(false);
                checkbox_karty_zlacze_1.setSelected(false);
                checkbox_karty_zlacze_2.setSelected(false);
                checkbox_karty_zlacze_3.setSelected(false);
                checkbox_karty_rodzaj_1.setSelected(false);
                checkbox_karty_rodzaj_2.setSelected(false);
                checkbox_karty_rodzaj_3.setSelected(false);
                checkbox_karty_rodzaj_4.setSelected(false);
                break;
            case "ram":
                min_cena_pamiec.setText("");
                max_cena_pamiec.setText("");
                checkbox_pamiec_producent_1.setSelected(false);
                checkbox_pamiec_producent_2.setSelected(false);
                checkbox_pamiec_producent_3.setSelected(false);
                checkbox_pamiec_producent_4.setSelected(false);
                checkbox_pamiec_producent_5.setSelected(false);
                checkbox_pamiec_rodzaj_1.setSelected(false);
                checkbox_pamiec_rodzaj_2.setSelected(false);
                checkbox_pamiec_pojemnosc_1.setSelected(false);
                checkbox_pamiec_pojemnosc_2.setSelected(false);
                checkbox_pamiec_pojemnosc_3.setSelected(false);
                checkbox_pamiec_pojemnosc_4.setSelected(false);
                checkbox_pamiec_pojemnosc_5.setSelected(false);
                break;
            case "dyski":
                min_cena_dyski.setText("");
                max_cena_dyski.setText("");
                checkbox_dyski_producent_1.setSelected(false);
                checkbox_dyski_producent_2.setSelected(false);
                checkbox_dyski_producent_3.setSelected(false);
                checkbox_dyski_typ_1.setSelected(false);
                checkbox_dyski_typ_2.setSelected(false);
                checkbox_dyski_pojemnosc_1.setSelected(false);
                checkbox_dyski_pojemnosc_2.setSelected(false);
                checkbox_dyski_pojemnosc_3.setSelected(false);
                checkbox_dyski_pojemnosc_4.setSelected(false);
                checkbox_dyski_pojemnosc_5.setSelected(false);

        }
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
            Pattern pat_naz = Pattern.compile("^[0-9]*[.]?[0-9]?[0-9]?+$");
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

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tp_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tp_list.add(new TablePlyty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_plyty.setItems(tp_list);
        }catch(MaxCenaException m){
            m.alert();
        }catch(CenaTypeException c){
            c.alert();
        }
    }

    //odsianie procesorów
    @FXML
    void odsiej_procesory(MouseEvent event) {

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_produktu,rodzina,cena from produkt " +
            "join procesor on produkt.id_procesora=procesor.id_procesora where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_procesory.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_procesory.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_procesory.getText().contains(",")){
                    min_cena_procesory.setText(min_cena_procesory.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_procesory.getText()+" and ";
            }
            if(max_cena_procesory.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_procesory.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_procesory.getText().contains(",")){
                    max_cena_procesory.setText(max_cena_procesory.getText().replaceAll(",","."));
                }
                if(!min_cena_procesory.getText().equals("") && Double.parseDouble(max_cena_procesory.getText())<Double.parseDouble(min_cena_procesory.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_procesory.getText();
            }

            //dodanie ograniczenia na producenta
            s1=" and producent ";
            if(checkbox_procesory_producent_1.isSelected()){
                st.add("'Intel'");
            }
            if(checkbox_procesory_producent_2.isSelected()){
                st.add("'AMD'");
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

            //dodanie ograniczenia na rodzine
            s1=" and rodzina ";
            if(checkbox_procesory_rodzina_1.isSelected()){
                st.add("'Core i3'");
            }
            if(checkbox_procesory_rodzina_2.isSelected()){
                st.add("'Core i5'");
            }
            if(checkbox_procesory_rodzina_3.isSelected()){
                st.add("'Celeron'");
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

            //dodanie ograniczenia gniazdo procesora
            s1=" and gniazdo_procesora ";
            if(checkbox_procesory_gniazdo_1.isSelected()){
                st.add("'Socket R'");
            }
            if(checkbox_procesory_gniazdo_2.isSelected()){
                st.add("'Socket H'");
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

            //dodanie ograniczenia na liczbe rdzeni
            s1=" and liczba_rdzeni ";
            if(checkbox_procesory_rdzenie_1.isSelected()){
                st.add("'6'");
            }
            if(checkbox_procesory_rdzenie_2.isSelected()){
                st.add("'10'");
            }
            if(checkbox_procesory_rdzenie_3.isSelected()){
                st.add("'14'");
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


            if(ttyb_procesory.getSelectionModel().getSelectedItem()!=null && ttyb_procesory.getSelectionModel().getSelectedItem().equals("Zakup")){
                zapytanie+=" and id_transakcji is null";
            }

            //wypełnienie tabel
            ObservableList<TableProcesory> tpr_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tpr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tpr_list.add(new TableProcesory(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_procesory.setItems(tpr_list);
        }catch(MaxCenaException m){
            m.alert();
        }catch(CenaTypeException c){
            c.alert();
        }

    }

    //odsianie kart graficznych
    @FXML
    void odsiej_karty(MouseEvent event) {

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_produktu,uklad_graficzny,cena from produkt " +
                    "join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_karty.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_karty.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_karty.getText().contains(",")){
                    min_cena_karty.setText(min_cena_karty.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_karty.getText()+" and ";
            }
            if(max_cena_karty.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_karty.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_karty.getText().contains(",")){
                    max_cena_karty.setText(max_cena_karty.getText().replaceAll(",","."));
                }
                if(!min_cena_karty.getText().equals("") && Double.parseDouble(max_cena_karty.getText())<Double.parseDouble(min_cena_karty.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_karty.getText();
            }

            //dodanie ograniczenia na producenta
            s1=" and producent ";
            if(checkbox_karty_producent_1.isSelected()){
                st.add("'MSI'");
            }
            if(checkbox_karty_producent_2.isSelected()){
                st.add("'Sapphire'");
            }
            if(checkbox_karty_producent_3.isSelected()){
                st.add("'Asus'");
            }
            if(checkbox_karty_producent_4.isSelected()){
                st.add("'PNY'");
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

            //dodanie ograniczenia na układ graficzny
            s1=" and uklad_graficzny ";
            if(checkbox_karty_uklad_1.isSelected()){
                st.add("'GeForce GTX 1650'");
            }
            if(checkbox_karty_uklad_2.isSelected()){
                st.add("'Dual Radeon RX 6650'");
            }
            if(checkbox_karty_uklad_3.isSelected()){
                st.add("'GeForce RTX 4070'");
            }
            if(checkbox_karty_uklad_4.isSelected()){
                st.add("'GeForce RTX 3070'");
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

            //dodanie ograniczenia na rodzaj złącza
            s1=" and rodzaj_zlacza ";
            if(checkbox_karty_zlacze_1.isSelected()){
                st.add("'PCI Express 2.0 x8'");
            }
            if(checkbox_karty_zlacze_2.isSelected()){
                st.add("'PCI Express 4.0 x8'");
            }
            if(checkbox_karty_zlacze_3.isSelected()){
                st.add("'PCI Express 4.0 x16'");
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

            //dodanie ograniczenia na rodzaj pamięci
            s1=" and rodzaj_pamieci ";
            if(checkbox_karty_rodzaj_1.isSelected()){
                st.add("'GDDR3'");
            }
            if(checkbox_karty_rodzaj_2.isSelected()){
                st.add("'GDDR4'");
            }
            if(checkbox_karty_rodzaj_3.isSelected()){
                st.add("'GDDR6'");
            }
            if(checkbox_karty_rodzaj_4.isSelected()){
                st.add("'GDDR7'");
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


            if(ttyb_karty.getSelectionModel().getSelectedItem()!=null && ttyb_karty.getSelectionModel().getSelectedItem().equals("Zakup")){
                zapytanie+=" and id_transakcji is null";
            }

            //wypełnienie tabel
            ObservableList<TableKarty> tk_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tk_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tk_list.add(new TableKarty(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_karty.setItems(tk_list);
        }catch(MaxCenaException m){
            m.alert();
        }catch(CenaTypeException c){
            c.alert();
        }

    }

    //odsianie pamięci RAM
    @FXML
    void odsiej_pamiec(MouseEvent event) {

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_produktu,rodzaj_pamieci,cena from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_pamiec.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_pamiec.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_pamiec.getText().contains(",")){
                    min_cena_pamiec.setText(min_cena_pamiec.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_pamiec.getText()+" and ";
            }
            if(max_cena_pamiec.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_pamiec.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_pamiec.getText().contains(",")){
                    max_cena_pamiec.setText(max_cena_pamiec.getText().replaceAll(",","."));
                }
                if(!min_cena_pamiec.getText().equals("") && Double.parseDouble(max_cena_pamiec.getText())<Double.parseDouble(min_cena_pamiec.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_pamiec.getText();
            }

            //dodanie ograniczenia na producenta
            s1=" and producent ";
            if(checkbox_pamiec_producent_1.isSelected()){
                st.add("'Lexar'");
            }
            if(checkbox_pamiec_producent_2.isSelected()){
                st.add("'Patriot'");
            }
            if(checkbox_pamiec_producent_3.isSelected()){
                st.add("'Corsair'");
            }
            if(checkbox_pamiec_producent_4.isSelected()){
                st.add("'TeamGroup'");
            }
            if(checkbox_pamiec_producent_5.isSelected()){
                st.add("'Kingston'");
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

            //dodanie ograniczenia na rodzaj pamięci
            s1=" and rodzaj_pamieci ";
            if(checkbox_pamiec_rodzaj_1.isSelected()){
                st.add("'DDR2'");
            }
            if(checkbox_pamiec_rodzaj_2.isSelected()){
                st.add("'DDR3'");
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

            //dodanie ograniczenia na rodzaj złącza
            s1=" and pojemnosc ";
            if(checkbox_pamiec_pojemnosc_1.isSelected()){
                st.add("'4'");
            }
            if(checkbox_pamiec_pojemnosc_2.isSelected()){
                st.add("'8'");
            }
            if(checkbox_pamiec_pojemnosc_3.isSelected()){
                st.add("'16'");
            }
            if(checkbox_pamiec_pojemnosc_4.isSelected()){
                st.add("'32'");
            }
            if(checkbox_pamiec_pojemnosc_5.isSelected()){
                st.add("'64'");
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

            if(ttyb_pamiec.getSelectionModel().getSelectedItem()!=null && ttyb_pamiec.getSelectionModel().getSelectedItem().equals("Zakup")){
                zapytanie+=" and id_transakcji is null";
            }

            //wypełnienie tabel
            ObservableList<TableRam> tr_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tr_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tr_list.add(new TableRam(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_ram.setItems(tr_list);
        }catch(MaxCenaException m){
            m.alert();
        }catch(CenaTypeException c){
            c.alert();
        }
    }

    //odsianie dysków
    @FXML
    void odsiej_dyski(MouseEvent event) {

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_produktu,pojemnosc,cena from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_dyski.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_dyski.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_dyski.getText().contains(",")){
                    min_cena_dyski.setText(min_cena_dyski.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_dyski.getText()+" and ";
            }
            if(max_cena_dyski.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_dyski.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_dyski.getText().contains(",")){
                    max_cena_dyski.setText(max_cena_dyski.getText().replaceAll(",","."));
                }
                if(!min_cena_dyski.getText().equals("") && Double.parseDouble(max_cena_dyski.getText())<Double.parseDouble(min_cena_dyski.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_dyski.getText();
            }

            //dodanie ograniczenia na producenta
            s1=" and producent ";
            if(checkbox_dyski_producent_1.isSelected()){
                st.add("'Seagate'");
            }
            if(checkbox_dyski_producent_2.isSelected()){
                st.add("'WD'");
            }
            if(checkbox_dyski_producent_3.isSelected()){
                st.add("'Toshiba'");
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

            //dodanie ograniczenia typ dysku
            s1=" and typ_dysku ";
            if(checkbox_dyski_typ_1.isSelected()){
                st.add("'HDD'");
            }
            if(checkbox_dyski_typ_2.isSelected()){
                st.add("'SSD'");
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

            //dodanie ograniczenia na pojemność
            s1=" and pojemnosc ";
            if(checkbox_dyski_pojemnosc_1.isSelected()){
                st.add("'250'");
            }
            if(checkbox_dyski_pojemnosc_2.isSelected()){
                st.add("'500'");
            }
            if(checkbox_dyski_pojemnosc_3.isSelected()){
                st.add("'1000'");
            }
            if(checkbox_dyski_pojemnosc_4.isSelected()){
                st.add("'1500'");
            }
            if(checkbox_dyski_pojemnosc_5.isSelected()){
                st.add("'2000'");
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

            if(ttyb_dyski.getSelectionModel().getSelectedItem()!=null && ttyb_dyski.getSelectionModel().getSelectedItem().equals("Zakup")){
                zapytanie+=" and id_transakcji is null";
            }

            //wypełnienie tabel
            ObservableList<TableDyski> td_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    td_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=3){
                    td_list.add(new TableDyski(wynik[i],wynik[i+1],Double.parseDouble(wynik[i+2])));
                }
            }

            table_dyski.setItems(td_list);
        }catch(MaxCenaException m){
            m.alert();
        }catch(CenaTypeException c){
            c.alert();
        }

    }

    //odsianie zestawów
    @FXML
    void odsiej_zestawy(MouseEvent event){

        try{
            //tworzenie zapytania do BD
            String zapytanie="Select distinct nazwa_zestawu,cena from zestaw where ";
            //zmienne pomocnicze
            ArrayList<String> st =new ArrayList<>();
            String s1;

            //dodanie ograniczenia na cene

            //utworzenie ograniczenia
            Pattern pat_naz = Pattern.compile("^[0-9]*.[0-9]*+$");
            Matcher matcher;


            zapytanie+="cena between ";
            if(min_cena_zestawy.getText().equals("")){
                zapytanie+="0 and ";
            }else{

                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(min_cena_zestawy.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(min_cena_zestawy.getText().contains(",")){
                    min_cena_zestawy.setText(min_cena_zestawy.getText().replaceAll(",","."));
                }
                zapytanie+=min_cena_zestawy.getText()+" and ";
            }
            if(max_cena_zestawy.getText().equals("")){
                zapytanie+="99999999";
            }else{
                //sprawdzenie poprawności wprowadzonych danych
                matcher = pat_naz.matcher(max_cena_zestawy.getText());
                if (matcher.find()) {
                    //pass
                } else {
                    throw new CenaTypeException();
                }

                //podmiana , na .
                if(max_cena_zestawy.getText().contains(",")){
                    max_cena_zestawy.setText(max_cena_zestawy.getText().replaceAll(",","."));
                }
                if(!min_cena_zestawy.getText().equals("") && Double.parseDouble(max_cena_zestawy.getText())<Double.parseDouble(min_cena_zestawy.getText())){
                    throw new MaxCenaException();
                }
                zapytanie+=max_cena_zestawy.getText();
            }

            //wypełnienie tabel
            ObservableList<TableZestawy> tz_list= FXCollections.observableArrayList();
            String wynik[]= connection.uzyskajDane(zapytanie);

            //zapisanie ostatio wykonanego zapytania
            dane.zapiszZapytanie(zapytanie);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników
                if(dane.getCzyOffline()==0){
                    //wyświetlenie alertu informacyjnego
                    informationAlert("Brak danych do wyświetlenia");

                    tz_list.clear();
                }

            }else{
                for(int i=0;i<wynik.length;i+=2){
                    tz_list.add(new TableZestawy(wynik[i],Double.parseDouble(wynik[i+1])));
                }
            }

            table_zestawy.setItems(tz_list);
        }catch(MaxCenaException m){
            m.alert();
        }catch(CenaTypeException c){
            c.alert();
        }

    }

    //otworzenie strony konkretnego produktu
    @FXML
    void showProduktPlyty(MouseEvent event) throws IOException{
        Integer index=table_plyty.getSelectionModel().getSelectedIndex();

        if(tp_nazwa.getCellData(index)!=null) {
            //pozyskanie id produktu na bazie jego nazwy, instancji do zakupienia
            String wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                    "id_plyty_glownej=(select id_plyty_glownej from plyta_glowna where nazwa_produktu='" + tp_nazwa.getCellData(index) + "') " +
                    "and id_transakcji is null and id_zamowienia is null fetch first 1 row only")[0];

            //pozyskanie id produktu na bazie jego nazwy, instancji do zmówienia
            if (wynik.equals("")) {
                wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                        "id_plyty_glownej=(select id_plyty_glownej from plyta_glowna where nazwa_produktu='" + tp_nazwa.getCellData(index) + "') " +
                        "fetch first 1 row only")[0];
            }
            dane.setIdWybranegoProduktu(wynik);

            //przejście na strone produktu
            root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void showProduktProcesory(MouseEvent event) throws IOException{
        Integer index=table_procesory.getSelectionModel().getSelectedIndex();

        if(tpr_nazwa.getCellData(index)!=null) {
            //pozyskanie id produktu na bazie jego nazwy, instancji do zakupienia
            String wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                    "id_procesora=(select id_procesora from procesor where nazwa_produktu='" + tpr_nazwa.getCellData(index) + "') " +
                    "and id_transakcji is null and id_zamowienia is null fetch first 1 row only")[0];

            //pozyskanie id produktu na bazie jego nazwy, instancji do zmówienia
            if (wynik.equals("")) {
                wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                        "id_procesora=(select id_procesora from procesor where nazwa_produktu='" + tpr_nazwa.getCellData(index) + "') " +
                        "fetch first 1 row only")[0];
            }
            dane.setIdWybranegoProduktu(wynik);

            //przejście na strone produktu
            root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void showProduktKarty(MouseEvent event) throws IOException{
        Integer index=table_karty.getSelectionModel().getSelectedIndex();

        if(tk_nazwa.getCellData(index)!=null) {
            //pozyskanie id produktu na bazie jego nazwy, instancji do zakupienia
            String wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                    "id_karty_graficznej=(select id_karty_graficznej from karta_graficzna where nazwa_produktu='" + tk_nazwa.getCellData(index) + "') " +
                    "and id_transakcji is null and id_zamowienia is null fetch first 1 row only")[0];

            //pozyskanie id produktu na bazie jego nazwy, instancji do zmówienia
            if (wynik.equals("")) {
                wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                        "id_karty_graficznej=(select id_karty_graficznej from karta_graficzna where nazwa_produktu='" + tk_nazwa.getCellData(index) + "') " +
                        "fetch first 1 row only")[0];
            }
            dane.setIdWybranegoProduktu(wynik);

            //przejście na strone produktu
            root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void showProduktPamiec(MouseEvent event) throws IOException{
        Integer index=table_ram.getSelectionModel().getSelectedIndex();

        if(tr_nazwa.getCellData(index)!=null) {
            //pozyskanie id produktu na bazie jego nazwy, instancji do zakupienia
            String wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                    "id_pamieci_ram=(select id_pamieci_ram from pamiec_ram where nazwa_produktu='" + tr_nazwa.getCellData(index) + "') " +
                    "and id_transakcji is null and id_zamowienia is null fetch first 1 row only")[0];

            //pozyskanie id produktu na bazie jego nazwy, instancji do zmówienia
            if (wynik.equals("")) {
                wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                        "id_pamieci_ram=(select id_pamieci_ram from pamiec_ram where nazwa_produktu='" + tr_nazwa.getCellData(index) + "') " +
                        "fetch first 1 row only")[0];
            }
            dane.setIdWybranegoProduktu(wynik);

            //przejście na strone produktu
            root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void showProduktDyski(MouseEvent event) throws IOException{
        Integer index=table_dyski.getSelectionModel().getSelectedIndex();

        if(td_nazwa.getCellData(index)!=null) {
            //pozyskanie id produktu na bazie jego nazwy, instancji do zakupienia
            String wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                    "id_dysku=(select id_dysku from dysk where nazwa_produktu='" + td_nazwa.getCellData(index) + "') " +
                    "and id_transakcji is null and id_zamowienia is null fetch first 1 row only")[0];

            //pozyskanie id produktu na bazie jego nazwy, instancji do zmówienia
            if (wynik.equals("")) {
                wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                        "id_dysku=(select id_dysku from dysk where nazwa_produktu='" + td_nazwa.getCellData(index) + "') " +
                        "fetch first 1 row only")[0];
            }
            dane.setIdWybranegoProduktu(wynik);

            //przejście na strone produktu
            root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void showProduktZestawy(MouseEvent event) throws IOException{
        Integer index=table_zestawy.getSelectionModel().getSelectedIndex();

        if(tz_nazwa.getCellData(index)!=null) {
            //pozyskanie id produktu na bazie jego nazwy, instancji do zakupienia
            String wynik = connection.uzyskajDane("Select id_zestawu from zestaw " +
                    "where nazwa_zestawu='" + tz_nazwa.getCellData(index) + "'")[0];

            //pozyskanie id produktu na bazie jego nazwy, instancji do zmówienia
            if (wynik.equals("")) {
                wynik = connection.uzyskajDane("Select id_produktu from produkt where " +
                        "id_zestawu=(select id_zestawu from zestaw where nazwa_zestawu='" + tz_nazwa.getCellData(index) + "') " +
                        "fetch first 1 row only")[0];
            }
            dane.setIdWybranegoZestawu(wynik);

            //przejście na strone produktu
            root = FXMLLoader.load(getClass().getResource("zestaw" + ".fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    //funkcje do wylogowania/zalogowania
    @FXML
    void wyloguj(){

        dane.setIdZalogowanegoUzytkownika("0");

        anchor_wyloguj.setVisible(false);
        anchor_zaloguj.setVisible(true);
    }

    @FXML
    void zaloguj(MouseEvent event) throws IOException {
        dane.setDestynacjaPowrotuZeStronyLogowania("wyszukiwarka");
        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //funkcja do przejścia na profil użytkownika
    @FXML
    void goProfil(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("uzytkownik" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //funkcja do przejścia do koszyka
    @FXML
    void goKoszyk(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("koszyk" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}