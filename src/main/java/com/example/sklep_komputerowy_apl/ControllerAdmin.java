package com.example.sklep_komputerowy_apl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerAdmin implements Initializable {

    //podłączenie do klas z danymi oraz komunikacją z BD
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

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
    private TableView<?> table_tra_zam;
    @FXML
    private TableView<?> table_uzytkownicy;
    @FXML
    private TextField texarea_trazam_email;
    @FXML
    private TextField textfield_email;
    @FXML
    private ChoiceBox<String> trazam_typ;
    @FXML
    private TableColumn<?, ?> ttz_cena;
    @FXML
    private TableColumn<?, ?> ttz_email;
    @FXML
    private TableColumn<?, ?> ttz_ilosc;
    @FXML
    private TableColumn<?, ?> tu_email;
    @FXML
    private TableColumn<?, ?> tu_r10;
    @FXML
    private TableColumn<?, ?> tu_r15;
    @FXML
    private TableColumn<?, ?> tu_r25;
    @FXML
    private TableColumn<?, ?> tu_r5;
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

    //dane
    String nazwy_plyty[];
    String nazwy_procesory[];
    String nazwy_karty[];
    String nazwy_pamiec[];
    String nazwy_dyski[];
    String nazwy_zestawy[];

    //wprowadzenie wartości do tabel
    public void odswiezTableUzupelnij(){
        // tabela uzupełniania komponentów

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableUzupelnianie> tuz_list= FXCollections.observableArrayList();

        //wypełnienie danymi z BD
        if(dane.getOstatnieZapytanieUzupelnijTable().equals("")){
            String wynik[]= connection.uzyskajDane("Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
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
                    "select pamiec_ram.nazwa_produktu, 'Pamięć RAM' as typ, count(*) as ilosc from produkt " +
                    "join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu " +
                    "union " +
                    "select dysk.nazwa_produktu, 'Dysk' as typ, count(*) as ilosc from produkt " +
                    "join dysk on produkt.id_dysku=dysk.id_dysku " +
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu) order by ilosc asc");

            for(int i=0;i<wynik.length;i+=3){
                tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                dane.getNazwyProduktowUzupelnij().add(wynik[i]);
                dane.getTypProduktowUzupelnij().add(wynik[i+1]);
                dane.getIloscProduktowUzupelnij().add(Integer.parseInt(wynik[i+2]));
            }

            table_uzupelnianie.setItems(tuz_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieUzupelnijTable());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tuz_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                }
            }

            table_uzupelnianie.setItems(tuz_list);
        }
    }

    //inicializacja strony admina
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ustawienie wartości w choiceboxach
        magazyn_akcja.getItems().addAll("Uzupełnij","Dodaj");
        f_typ_produktu.getItems().addAll("Płyta główna","Procesor","Karta graficzna","Pamięć RAM","Dysk","Zestaw");

        //ustawienie domyślnej wartości
        magazyn_akcja.setValue("Uzupełnij");
        f_typ_produktu.setValue("Płyta główna");
        //ustawienie wykonywanych funkcji przy zmianie wartośći w choiceboxach
        magazyn_akcja.setOnAction(actionEvent -> formZmienStrone());
        f_typ_produktu.setOnAction(actionEvent -> formZmienStroneForm());

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

        //wstawienie wartości do tabel
        odswiezTableUzupelnij();

    };

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

        //aktualizowanie informacji w tabeli
        // tabela uzupełniania komponentów

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableUzupelnianie> tuz_list= FXCollections.observableArrayList();

        //wypełnienie danymi z BD
        if(dane.getOstatnieZapytanieUzupelnijTable().equals("")){
            System.out.println("to");
            String wynik[]= connection.uzyskajDane("Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
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
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu) order by ilosc asc");

            for(int i=0;i<wynik.length;i+=3){
                tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
            }

            table_uzupelnianie.setItems(tuz_list);
        }else{
            //danymi z uprzednio wykonanego zapytania
            String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieUzupelnijTable());
            if(wynik.length<=1){
                //gdy zapytanie nie zwróciło żądnych wyników
                tuz_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                }
            }

            table_uzupelnianie.setItems(tuz_list);
        }

        String wybor=magazyn_akcja.getSelectionModel().getSelectedItem();

        if(wybor.equals("Uzupełnij")){

            //zaktualizowanie informacji w tabeli
            // tabela uzupełniania komponentów

            //utworzenie list do wypełniania odpowiednich typów
            tuz_list= FXCollections.observableArrayList();

            //wypełnienie danymi z BD
            if(dane.getOstatnieZapytanieUzupelnijTable().equals("")){
                System.out.println("to");
                String wynik[]= connection.uzyskajDane("Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
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
                        "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu) order by ilosc asc");

                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                }

                table_uzupelnianie.setItems(tuz_list);
            }else{
                //danymi z uprzednio wykonanego zapytania
                String wynik[]= connection.uzyskajDane(dane.getOstatnieZapytanieUzupelnijTable());
                if(wynik.length<=1){
                    //gdy zapytanie nie zwróciło żądnych wyników
                    tuz_list.clear();
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
            scrollpane_table.setVisible(false);
            scrollpane_form_plyty.setVisible(true);
            scrollpane_form_karty.setVisible(false);
            scrollpane_form_procesory.setVisible(false);
            scrollpane_form_pamiec.setVisible(false);
            scrollpane_form_dyski.setVisible(false);
            scrollpane_form_zestawy.setVisible(false);
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
        }
    }

    //funkcja dod odawania nowego produktu do magazynu
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
                System.out.println(wynik);

                if(wynik.equals("Wprowadzenie/modyfikacja danych zakończona pomyślnie")){
                    nazwy_plyty=connection.uzyskajDane("Select nazwa_produktu from PLYTA_GLOWNA");
                }

                //aktualizacja wartośći w checkboxach
                fz_plyta.getItems().addAll(nazwy_plyty);

            }catch(EmptyValueException e){
                System.out.println(e);
            }catch( CenaTypeException c){
                System.out.println(c);
            }catch(TypeException t){
                System.out.println(t);
            }catch(NameTakenException n){
                System.out.println(n);
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
                System.out.println(zapytanie);
                String wynik=connection.wprowadzDane(zapytanie);

                if(wynik.equals("Wprowadzenie/modyfikacja danych zakończona pomyślnie")){
                    nazwy_procesory=connection.uzyskajDane("Select nazwa_produktu from PROCESOR");
                }

                //aktualizacja wartości w checkboxach
                fz_procesor.getItems().addAll(nazwy_procesory);

            }catch(EmptyValueException e){
                System.out.println(e);
            }catch( CenaTypeException c){
                System.out.println(c);
            }catch(TypeException t){
                System.out.println(t);
            }catch(NameTakenException n){
                System.out.println(n);
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

                if(wynik.equals("Wprowadzenie/modyfikacja danych zakończona pomyślnie")){
                    nazwy_karty=connection.uzyskajDane("Select nazwa_produktu from KARTA_GRAFICZNA");
                }

                //aktualizacja wartości w checkboxach
                fz_karta.getItems().addAll(nazwy_karty);


            }catch(EmptyValueException e){
                System.out.println(e);
            }catch( CenaTypeException c){
                System.out.println(c);
            }catch(TypeException t){
                System.out.println(t);
            }catch(NameTakenException n){
                System.out.println(n);
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
                        +fr_pamiec.getValue()+"','"+fr_pojemnosc.getValue()+"','"+fr_taktowanie.getText()+"','"+fr_napiecie.getText()+"','"+fr_nazwa.getText()+"','"+fr_producent.getValue()+"','"+
                        fr_opis.getText()+"',"+fr_cena.getText()+")";

                //próba wprowadzenia danych do DB z oczekiwaniem na odpowiedź ze statusem zapytania błąd/sukces
                String wynik=connection.wprowadzDane(zapytanie);
                System.out.println(wynik);

                if(wynik.equals("Wprowadzenie/modyfikacja danych zakończona pomyślnie")){
                    nazwy_pamiec=connection.uzyskajDane("Select nazwa_produktu from PAMIEC_RAM");
                }

                //aktualizacja wartości w checkboxach
                fz_pamiec.getItems().addAll(nazwy_pamiec);

            }catch(EmptyValueException e){
                System.out.println(e);
            }catch( CenaTypeException c){
                System.out.println(c);
            }catch(TypeException t){
                System.out.println(t);
            }catch(NameTakenException n){
                System.out.println(n);
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
                System.out.println(wynik);


                if(wynik.equals("Wprowadzenie/modyfikacja danych zakończona pomyślnie")){
                    nazwy_dyski=connection.uzyskajDane("Select nazwa_produktu from DYSK");
                }

                //aktualizacja wartości w checkboxach
                fz_dysk.getItems().addAll(nazwy_dyski);

            }catch(EmptyValueException e){
                System.out.println(e);
            }catch( CenaTypeException c){
                System.out.println(c);
            }catch(TypeException t){
                System.out.println(t);
            }catch(NameTakenException n){
                System.out.println(n);
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
                String wynik=connection.wprowadzDane(zapytanie);
                System.out.println(wynik);

                if(wynik.equals("Wprowadzenie/modyfikacja danych zakończona pomyślnie")){
                    nazwy_zestawy=connection.uzyskajDane("Select nazwa_zestawu from ZESTAW");
                }

            }catch(EmptyValueException e){
                System.out.println(e);
            }catch( CenaTypeException c){
                System.out.println(c);
            }catch(NameTakenException n){
                System.out.println(n);
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

            String wynik[]= connection.uzyskajDane("Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
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
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu) where nazwa_produktu like('%"+nazwa+"%') order by ilosc asc");

                for(int i=0;i<wynik.length;i+=3){
                    tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
                }

                table_uzupelnianie.setItems(tuz_list);
        }else{
            //wyszukanie wszystkich

            //utworzenie list do wypełniania odpowiednich typów
            ObservableList<TableUzupelnianie> tuz_list= FXCollections.observableArrayList();

            //wypełnienie danymi z BD

            String wynik[]= connection.uzyskajDane("Select nazwa_produktu, typ, ilosc from (Select plyta_glowna.nazwa_produktu, 'Płyta główna' as typ, count(*) as ilosc from produkt " +
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
                    "where id_transakcji is null and id_zamowienia is null group by nazwa_produktu) order by ilosc asc");

            for(int i=0;i<wynik.length;i+=3){
                tuz_list.add(new TableUzupelnianie(wynik[i],wynik[i+1],Integer.parseInt(wynik[i+2])));
            }

            table_uzupelnianie.setItems(tuz_list);
        }
    }

    //funkcja do wybierania produktu z listy uzupełnień

    @FXML
    void wybierzProdukt(MouseEvent event){
        Integer index=table_uzupelnianie.getSelectionModel().getSelectedIndex();

        dane.wybierzProdukt(tuz_nazwa.getCellData(index),tuz_typ.getCellData(index));
    }

    //funkcja do uzupełniania wybranego produktu o podaną ilość
    @FXML
    void uzupelnijWybranyProdukt(){

        if(!uzupelnij_ilosc.getText().equals("")){
            int ilosc=Integer.parseInt(uzupelnij_ilosc.getText());

            if(dane.getTypWybranyProdukt().equals("Płyta główna")){

                for(int i=0;i<ilosc;i++){

                    String wynik=connection.wprowadzDane("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null, null, " +
                            "(select id_plyty_glownej from plyta_glowna where nazwa_produktu='"+dane.getNazwaWybranyProdukt()+"'), " +
                            "null,null,null,null)");
                    System.out.println(wynik);
                }
            }else if(dane.getTypWybranyProdukt().equals("Procesor")){
                for(int i=0;i<ilosc;i++){

                    String wynik=connection.wprowadzDane("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null, null, " +
                            "null, " +
                            "null," +
                            "(select id_procesora from procesor where nazwa_produktu='"+dane.getNazwaWybranyProdukt()+"')," +
                            "null,null)");
                    System.out.println(wynik);
                }
            }else if(dane.getTypWybranyProdukt().equals("Pamięć RAM")){
                for(int i=0;i<ilosc;i++){

                    String wynik=connection.wprowadzDane("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null," +
                            " (select id_pamieci_ram from pamiec_ram where nazwa_produktu='"+dane.getNazwaWybranyProdukt()+"'), " +
                            "null, " +
                            "null," +
                            "null," +
                            "null,null)");
                    System.out.println(wynik);
                }
            }else if(dane.getTypWybranyProdukt().equals("Karta graficzna")){
                for(int i=0;i<ilosc;i++){

                    String wynik=connection.wprowadzDane("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null," +
                            " null, " +
                            "null, " +
                            "(select id_karty_graficznej from karta_graficzna where nazwa_produktu='"+dane.getNazwaWybranyProdukt()+"')," +
                            "null," +
                            "null,null)");
                    System.out.println(wynik);
                }
            }else if(dane.getTypWybranyProdukt().equals("Dysk")){
                for(int i=0;i<ilosc;i++){

                    String wynik=connection.wprowadzDane("Insert into produkt " +
                            "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                            " null, null," +
                            " null, " +
                            "null, " +
                            "null," +
                            "null," +
                            "(select id_dysku from dysk where nazwa_produktu='"+dane.getNazwaWybranyProdukt()+"'),null)");
                    System.out.println(wynik);
                }
            }

            //zaktualizowanie informacji w tabeli
            odswiezTableUzupelnij();


        }
    }

    //funkcja do uzupełniania stanu wszystkich produktów których ilość jest mniejsza od 10
    @FXML
    void uzupelnijWszystkieProdukty(){

        System.out.println(dane.getNazwyProduktowUzupelnij().size());

        for(int j=0;j<dane.getNazwyProduktowUzupelnij().size();j++) {

            if(dane.getIloscProduktowUzupelnij().get(j)<10) {
                {
                    if (dane.getTypProduktowUzupelnij().get(j).equals("Płyta główna")) {

                        for (int i = 0; i < (10-dane.getIloscProduktowUzupelnij().get(j)); i++) {

                            String wynik = connection.wprowadzDane("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null, null, " +
                                    "(select id_plyty_glownej from plyta_glowna where nazwa_produktu='" + dane.getNazwyProduktowUzupelnij().get(j) + "'), " +
                                    "null,null,null,null)");
                            System.out.println(wynik);

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("Wprowadzenie/modyfikacja danych nieudana")){
                                break;
                            }
                        }
                    } else if (dane.getTypProduktowUzupelnij().get(j).equals("Procesor")) {
                        for (int i = 0; i < (10-dane.getIloscProduktowUzupelnij().get(j)); i++) {

                            String wynik = connection.wprowadzDane("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null, null, " +
                                    "null, " +
                                    "null," +
                                    "(select id_procesora from procesor where nazwa_produktu='" + dane.getNazwyProduktowUzupelnij().get(j) + "')," +
                                    "null,null)");
                            System.out.println(wynik);

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("Wprowadzenie/modyfikacja danych nieudana")){
                                break;
                            }
                        }
                    } else if (dane.getTypProduktowUzupelnij().get(j).equals("Pamięć RAM")) {
                        for (int i = 0; i < (10-dane.getIloscProduktowUzupelnij().get(j)); i++) {

                            String wynik = connection.wprowadzDane("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null," +
                                    " (select id_pamieci_ram from pamiec_ram where nazwa_produktu='" + dane.getNazwyProduktowUzupelnij().get(j) + "'), " +
                                    "null, " +
                                    "null," +
                                    "null," +
                                    "null,null)");
                            System.out.println(wynik);

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("Wprowadzenie/modyfikacja danych nieudana")){
                                break;
                            }
                        }
                    } else if (dane.getTypProduktowUzupelnij().get(j).equals("Karta graficzna")) {
                        for (int i = 0; i < (10-dane.getIloscProduktowUzupelnij().get(j)); i++) {

                            String wynik = connection.wprowadzDane("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null," +
                                    " null, " +
                                    "null, " +
                                    "(select id_karty_graficznej from karta_graficzna where nazwa_produktu='" + dane.getNazwyProduktowUzupelnij().get(j) + "')," +
                                    "null," +
                                    "null,null)");
                            System.out.println(wynik);

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("Wprowadzenie/modyfikacja danych nieudana")){
                                break;
                            }
                        }
                    } else if (dane.getTypProduktowUzupelnij().get(j).equals("Dysk")) {
                        for (int i = 0; i < (10-dane.getIloscProduktowUzupelnij().get(j)); i++) {

                            String wynik = connection.wprowadzDane("Insert into produkt " +
                                    "values((select case when max(id_produktu)>0 then max(id_produktu)+1 else 1 end from produkt)," +
                                    " null, null," +
                                    " null, " +
                                    "null, " +
                                    "null," +
                                    "null," +
                                    "(select id_dysku from dysk where nazwa_produktu='" + dane.getNazwyProduktowUzupelnij().get(j) + "'),null)");
                            System.out.println(wynik);

                            //przerwanie pętli w razie wystąpienia błędu
                            if(wynik.equals("Wprowadzenie/modyfikacja danych nieudana")){
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


}

