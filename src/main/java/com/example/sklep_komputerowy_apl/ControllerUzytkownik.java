package com.example.sklep_komputerowy_apl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerUzytkownik implements Initializable {

    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    //Wartosci przyjete roboczo
    private String idZalogowanegoUzytkownika=dane.getIdZalogowanegoUzytkownika();

    private String nazwaWybranegoProduktuDoReklamacji="";
    private String czyReklamowano="";

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Elementy
    //Elementy poza zakladkami
    @FXML
    private Button button_cart;

    @FXML
    private Text button_home;

    @FXML
    private Button button_opinie;

    @FXML
    private Button button_profil;

    @FXML
    private Button button_transakcje;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zamowienia;

    @FXML
    private ImageView image_cart;

    @FXML
    private ImageView image_user;
    @FXML
    private Button button_reklamacje;

    //Elementy w zakladce potwierdzenia usuniecia konta
    @FXML
    private AnchorPane zatwierdzenie_usuniecia;

    @FXML
    private Button button_anuluj_usuniecie;

    @FXML
    private Button button_zatwierdz_usuniecie;

    //Elementy w zakladce profil
    @FXML
    private AnchorPane profil;

    @FXML
    private Button button_go_to_zmian_hasla;

    @FXML
    private Button button_panel_admina;

    @FXML
    private Button usun_konto;

    @FXML
    private Text value_of_email;

    @FXML
    private Button button_value_of_name;

    @FXML
    private Text value_of_imie;

    @FXML
    private Text value_of_nazwisko;

    @FXML
    private TableView<TableKomunikatyUzytkownika> table_profil_komunikaty;

    @FXML
    private TableColumn<TableKomunikatyUzytkownika, String> column_profil_komunikaty_data;

    @FXML
    private TableColumn<TableKomunikatyUzytkownika, String> column_profil_komunikaty_tresc;

    //Elementy w zakladce opinie
    @FXML
    private AnchorPane opinie;

    @FXML
    private TableView<TableOpinie> table_opinie;

    @FXML
    private TableColumn<TableOpinie, String> column_opinie_nazwa_produktu;

    @FXML
    private TableColumn<TableOpinie, Double> column_opinie_ocena;

    @FXML
    private TableColumn<TableOpinie, String> column_opinie_tresc;

    //Elementy w zakladce transakcje
    @FXML
    private AnchorPane transakcje;

    @FXML
    private TableView<TableTransakcjeUzytkownika> table_transakcje;

    @FXML
    private TableColumn<TableTransakcjeUzytkownika, String> column_transakcje_id_transakcji;

    @FXML
    private TableColumn<TableTransakcjeUzytkownika, Double> column_transakcje_cena;

    @FXML
    private TableColumn<TableTransakcjeUzytkownika, String> column_transakcje_data;

    @FXML
    private TableColumn<TableTransakcjeUzytkownika, String> column_transakcje_nazwy_produktow;

    //Elementy w zakladce zamowienia
    @FXML
    private AnchorPane zamowienia;

    @FXML
    private TableView<TableZamowieniaUzytkownika> table_zamowienia;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_id_zamowienia;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_data_odbioru;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_data_zlozenia;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_nazwy_produktow;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_status;

    //Elementy w zakładce zmiany hasła
    @FXML
    private AnchorPane zmiana_hasla;

    @FXML
    private Button button_wroc_h;

    @FXML
    private Button button_zmien_haslo;

    @FXML
    private CheckBox checkbox_nowe_haslo;

    @FXML
    private CheckBox checkbox_powtorz_haslo;

    @FXML
    private CheckBox checkbox_stare_haslo;

    @FXML
    private PasswordField passwordField_nowe_haslo;

    @FXML
    private PasswordField passwordField_powtorz_haslo;

    @FXML
    private PasswordField passwordField_stare_haslo;

    @FXML
    private TextField textField_nowe_haslo;

    @FXML
    private TextField textField_powtorz_haslo;

    @FXML
    private TextField textField_stare_haslo;

    //Elementy w zakładce reklamacje
    @FXML
    private TextArea rek_powod;

    @FXML
    private AnchorPane rek_tran;

    @FXML
    private AnchorPane rek_zam;

    @FXML
    private AnchorPane reklamacje;

    @FXML
    private TableView<TableReklamacje> table_rek_tran;

    @FXML
    private TableView<TableReklamacje> table_rek_zam;

    @FXML
    private TableColumn<TableReklamacje, String> trt_nazwa;

    @FXML
    private TableColumn<TableReklamacje, String> trt_reklamowano;

    @FXML
    private TableColumn<TableReklamacje, String> trz_nazwa;

    @FXML
    private TableColumn<TableReklamacje, String> trz_reklamowano;

    @FXML
    private ChoiceBox<String> rek_choicebox_id_trans;

    @FXML
    private ChoiceBox<String> rek_choicebox_id_zam;

    @FXML
    private ChoiceBox<String> rek_typ;

    //-------------------------------------
    //Funkcje
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ustawienie tabel
        //KomunikatyUzytkownika
        column_profil_komunikaty_data.setCellValueFactory(new PropertyValueFactory<TableKomunikatyUzytkownika, String>("data"));
        column_profil_komunikaty_tresc.setCellValueFactory(new PropertyValueFactory<TableKomunikatyUzytkownika, String>("tresc"));
        //OpinieUzytkownika
        column_opinie_ocena.setCellValueFactory(new PropertyValueFactory<TableOpinie, Double>("ocena"));
        column_opinie_tresc.setCellValueFactory(new PropertyValueFactory<TableOpinie, String>("komentarz"));
        column_opinie_nazwa_produktu.setCellValueFactory(new PropertyValueFactory<TableOpinie, String>("nazwa"));
        //TransakcjeUzytkownika
        column_transakcje_id_transakcji.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, String>("id_transakcji"));
        column_transakcje_cena.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, Double>("cena"));
        column_transakcje_data.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, String>("data"));
        column_transakcje_nazwy_produktow.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, String>("nazwy"));
        //ZamowieniaUzytkownika
        column_zamowienia_id_zamowienia.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("id_zamowienia"));
        column_zamowienia_data_zlozenia.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("data_zlo"));
        column_zamowienia_data_odbioru.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("data_odb"));
        column_zamowienia_status.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("status"));
        column_zamowienia_nazwy_produktow.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("nazwy"));
        //ReklamacjeUzytkownika
        trt_nazwa.setCellValueFactory(new PropertyValueFactory<TableReklamacje,String>("nazwa"));
        trt_reklamowano.setCellValueFactory(new PropertyValueFactory<TableReklamacje,String>("rek"));
        trz_nazwa.setCellValueFactory(new PropertyValueFactory<TableReklamacje,String>("nazwa"));
        trz_reklamowano.setCellValueFactory(new PropertyValueFactory<TableReklamacje,String>("rek"));

        //wypełnienie tabel
        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableKomunikatyUzytkownika> tku_list = FXCollections.observableArrayList();
        ObservableList<TableOpinie> tou_list = FXCollections.observableArrayList();
        ObservableList<TableTransakcjeUzytkownika> ttu_list = FXCollections.observableArrayList();
        ObservableList<TableZamowieniaUzytkownika> tzu_list = FXCollections.observableArrayList();

        //TableKomunikatyUzytkownika
        String wynik[] = connection.uzyskajDane("Select data, tresc " +
                "FROM Wiadomosc " +
                "WHERE id_uzytkownika = " + idZalogowanegoUzytkownika);
        if(wynik.length!=1)
        {
            for (int i = 0; i < wynik.length; i += 2) {
                tku_list.add(new TableKomunikatyUzytkownika(wynik[i], wynik[i + 1]));
            }
            table_profil_komunikaty.setItems(tku_list);
        }

        //TableOpinieUzytkownka
        wynik = connection.uzyskajDane("Select " +
                "o.ocena, " +
                "o.komentarz, " +
                "CASE " +
                "WHEN ppr.id_pamieci_ram IS NOT NULL THEN ppr.nazwa_produktu " +
                "WHEN ppg.id_plyty_glownej IS NOT NULL THEN ppg.nazwa_produktu " +
                "WHEN pkg.id_karty_graficznej IS NOT NULL THEN pkg.nazwa_produktu " +
                "WHEN pproc.id_procesora IS NOT NULL THEN pproc.nazwa_produktu " +
                "WHEN pd.id_dysku IS NOT NULL THEN pd.nazwa_produktu " +
                "ELSE NULL " +
                "END AS nazwa_produktu " +
                "FROM Opinia o " +
                "LEFT JOIN Pamiec_RAM ppr ON o.id_pamieci_ram = ppr.id_pamieci_ram " +
                "LEFT JOIN Plyta_glowna ppg ON o.id_plyty_glownej = ppg.id_plyty_glownej " +
                "LEFT JOIN Karta_graficzna pkg ON o.id_karty_graficznej = pkg.id_karty_graficznej " +
                "LEFT JOIN Procesor pproc ON o.id_procesora = pproc.id_procesora " +
                "LEFT JOIN Dysk pd ON o.id_dysku = pd.id_dysku " +
                "WHERE o.id_uzytkownika = " + idZalogowanegoUzytkownika);
        if(wynik.length!=1) {
            for (int i = 0; i < wynik.length; i += 3) {
                tou_list.add(new TableOpinie(Double.parseDouble(wynik[i]), wynik[i + 2], wynik[i + 1]));
            }
            table_opinie.setItems(tou_list);
        }

        //TableTransakcjeUzytkownika
        wynik = connection.uzyskajDane("Select " +
                "t.id_transakcji, " +
                "t.data_t, " +
                "t.cena_calkowita, " +
                "LISTAGG( " +
                "CASE " +
                "WHEN ppr.id_pamieci_ram IS NOT NULL THEN ppr.nazwa_produktu " +
                "WHEN ppg.id_plyty_glownej IS NOT NULL THEN ppg.nazwa_produktu " +
                "WHEN pkg.id_karty_graficznej IS NOT NULL THEN pkg.nazwa_produktu " +
                "WHEN pproc.id_procesora IS NOT NULL THEN pproc.nazwa_produktu " +
                "WHEN pd.id_dysku IS NOT NULL THEN pd.nazwa_produktu " +
                "ELSE NULL " +
                "END, " +
                "', ' " +
                ") WITHIN GROUP (ORDER BY p.id_produktu) AS concatenated_product_names " +
                "FROM Transakcja t " +
                "JOIN Produkt p ON t.id_transakcji = p.id_transakcji " +
                "LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram " +
                "LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej " +
                "LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej " +
                "LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora " +
                "LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku " +
                "WHERE t.id_uzytkownika = " + idZalogowanegoUzytkownika +
                " GROUP BY t.id_transakcji, t.data_t, t.cena_calkowita");
        if(wynik.length!=1) {
            for (int i = 0; i < wynik.length; i += 4) {
                ttu_list.add(new TableTransakcjeUzytkownika(wynik[i], wynik[i + 1], "- "+wynik[i+3].replace(", ","\n- "), Double.parseDouble(wynik[i + 2])));
            }
            table_transakcje.setItems(ttu_list);
        }

        //TableZamowieniaUzytkownika
        wynik = connection.uzyskajDane("Select " +
                "z.id_zamowienia, " +
                "z.data_zlozenia, " +
                "z.data_odbioru, " +
                "z.status_odbioru, " +
                "LISTAGG( " +
                "CASE " +
                "WHEN ppr.id_pamieci_ram IS NOT NULL THEN ppr.nazwa_produktu " +
                "WHEN ppg.id_plyty_glownej IS NOT NULL THEN ppg.nazwa_produktu " +
                "WHEN pkg.id_karty_graficznej IS NOT NULL THEN pkg.nazwa_produktu " +
                "WHEN pproc.id_procesora IS NOT NULL THEN pproc.nazwa_produktu " +
                "WHEN pd.id_dysku IS NOT NULL THEN pd.nazwa_produktu " +
                "ELSE NULL " +
                "END, " +
                "', ' " +
                ") WITHIN GROUP (ORDER BY p.id_produktu) AS concatenated_product_names " +
                "FROM Zamowienie z " +
                "JOIN Produkt p ON z.id_zamowienia = p.id_zamowienia " +
                "LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram " +
                "LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej " +
                "LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej " +
                "LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora " +
                "LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku " +
                "WHERE z.id_uzytkownika = " + idZalogowanegoUzytkownika +
                " GROUP BY z.id_zamowienia, z.data_zlozenia, z.data_odbioru, z.status_odbioru");
        if(wynik.length!=1) {
            for (int i = 0; i < wynik.length; i += 5) {
                tzu_list.add(new TableZamowieniaUzytkownika(wynik[i], wynik[i+1], wynik[i + 2], "- "+wynik[i+4].replace(", ","\n- "), wynik[i + 3]));
            }
            table_zamowienia.setItems(tzu_list);
        }

        //Button_value_of_name
        wynik = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        button_value_of_name.setText(wynik[0]);

        //Button_panel_admina
        wynik = connection.uzyskajDane("Select czy_admin from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        if(wynik[0].equals("1"))
        {
            button_panel_admina.setVisible(true);
        }

        //Value_of_name
        wynik = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        value_of_imie.setText(wynik[0]);

        //Value_of_nazwisko
        wynik = connection.uzyskajDane("Select nazwisko from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        value_of_nazwisko.setText(wynik[0]);

        //Value_of_name
        wynik = connection.uzyskajDane("Select email from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        value_of_email.setText(wynik[0]);

        //inicializacja waertośći w choiceboxach
        rek_typ.getItems().setAll("Zamówienia","Transakcje");

        String s1[];

        //wyświetlane są tylko zatwierdzone transakcje/zamówienia
        s1=connection.uzyskajDane("Select id_zamowienia from zamowienie where id_uzytkownika="+idZalogowanegoUzytkownika+" " +
                "and status_odbioru='zrealizowane'");
        rek_choicebox_id_zam.getItems().setAll(s1);

        s1=connection.uzyskajDane("Select id_transakcji from transakcja where id_uzytkownika="+idZalogowanegoUzytkownika+"" +
                "and status='zatwierdzona'");
        rek_choicebox_id_trans.getItems().setAll(s1);

        //przypisanie funkcji wykonywanych przy zmianie wartości w choiceboxach
        rek_typ.setOnAction(actionEvent -> rekZmienBox());
        rek_choicebox_id_zam.setOnAction(actionEvent -> rekPokazTabele());
        rek_choicebox_id_trans.setOnAction(actionEvent -> rekPokazTabele());
    }

    //wyświetlanie alertów
    public void informationAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setContentText(m);
        alert.showAndWait();
    }

    @FXML
    void deleteAccount(MouseEvent event) throws IOException {
        connection.wprowadzDane("UPDATE Uzytkownik SET czy_aktywny = 0 WHERE id_uzytkownika = " + idZalogowanegoUzytkownika);

        root = FXMLLoader.load(getClass().getResource("wyszukiwarka" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("wyszukiwarka" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void goToCart(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("koszyk" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToAdmin(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //funkcja wyświetlająca strone opinii uzytkownika
    @FXML
    void showOpinie(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(true);
        zmiana_hasla.setVisible(false);
        reklamacje.setVisible(false);
    }

    //funkcja wyświetlająca strone profilu
    @FXML
    void showProfil(MouseEvent event) {
        profil.setVisible(true);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
        zmiana_hasla.setVisible(false);
        reklamacje.setVisible(false);
    }

    //funkcja wyświetlająca strone transakcji uzytkownika
    @FXML
    void showTransakcje(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(true);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
        zmiana_hasla.setVisible(false);
        reklamacje.setVisible(false);
    }

    //funkcja wyświetlająca strone zamowien uzytkownika
    @FXML
    void showZamowienia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(true);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
        zmiana_hasla.setVisible(false);
        reklamacje.setVisible(false);
    }

    //funkcja wyświetlająca strone zatwierdzenia usuniecia konta
    @FXML
    void showZatwierdzenieUsuniecia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(true);
        opinie.setVisible(false);
        zmiana_hasla.setVisible(false);
        reklamacje.setVisible(false);
    }

    @FXML
    void showZmianaHasla(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
        zmiana_hasla.setVisible(true);
        reklamacje.setVisible(false);

        checkbox_powtorz_haslo.setSelected(false);
        checkbox_nowe_haslo.setSelected(false);
        checkbox_stare_haslo.setSelected(false);

        passwordField_stare_haslo.setVisible(true);
        passwordField_nowe_haslo.setVisible(true);
        passwordField_powtorz_haslo.setVisible(true);

        textField_stare_haslo.setVisible(false);
        textField_nowe_haslo.setVisible(false);
        textField_powtorz_haslo.setVisible(false);

        passwordField_nowe_haslo.setText("");
        passwordField_stare_haslo.setText("");
        passwordField_powtorz_haslo.setText("");

        textField_nowe_haslo.setText("");
        textField_stare_haslo.setText("");
        textField_powtorz_haslo.setText("");
    }
    //funkcja do wyświetlenia strony reklamacji
    @FXML
    void showReklamacje(MouseEvent event){
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);
        zmiana_hasla.setVisible(false);
        reklamacje.setVisible(true);
    }

    //funkcja do zmiany widocznego choiceboxa transakcje/zamówienia
    @FXML
    void rekZmienBox(){
        String strona=rek_typ.getSelectionModel().getSelectedItem();

        if(strona.equals("Zamówienia")){
            rek_zam.setVisible(true);
            rek_tran.setVisible(false);

            //schowanie tabel
            table_rek_zam.setVisible(false);
            table_rek_tran.setVisible(false);
        }else if(strona.equals("Transakcje")){
            rek_zam.setVisible(false);
            rek_tran.setVisible(true);

            //schowanie tabel
            table_rek_zam.setVisible(false);
            table_rek_tran.setVisible(false);
        }
    }

    //funkcja do pokazania iwypełnienia odpowiedniej tabeli w zakładce reklamacje
    @FXML
    void rekPokazTabele(){

        String strona=rek_typ.getSelectionModel().getSelectedItem();
        String id="";

        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableReklamacje> tr_list= FXCollections.observableArrayList();

        if(strona.equals("Zamówienia")){

            //wyczyszczenie listy
            tr_list.clear();

            //pobranie id zamówienia
            id=rek_choicebox_id_zam.getSelectionModel().getSelectedItem();

            //pobranie danych z BD
            String wynik[]=connection.uzyskajDane("Select "+
                "(case WHEN ppr.id_pamieci_ram IS NOT NULL THEN ppr.nazwa_produktu "+
                "WHEN ppg.id_plyty_glownej IS NOT NULL THEN ppg.nazwa_produktu "+
                "WHEN pkg.id_karty_graficznej IS NOT NULL THEN pkg.nazwa_produktu "+
                "WHEN pproc.id_procesora IS NOT NULL THEN pproc.nazwa_produktu "+
                "WHEN pd.id_dysku IS NOT NULL THEN pd.nazwa_produktu "+
                "else null "+
                "end) "+
                "as nazwa_produktu," +
                "(case when id_reklamacji is null then 'Nie' else 'Tak' end) "+
                "from Produkt p "+
                "LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram "+
                "LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej "+
                "LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej "+
                "LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora "+
                "LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku " +
                "left join reklamacja r on p.id_produktu=r.id_produktu "+
                "where p.id_zamowienia="+id);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników

                //wyświetlenie alertu informacyjnego
                informationAlert("Brak danych do wyświetlenia");

                tr_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=2){
                    tr_list.add(new TableReklamacje(wynik[i],wynik[i+1]));
                }
            }

            //wypełnienie tabeli danymi
            table_rek_zam.setItems(tr_list);

            //wyświetlenie odpowiedniej tabeli
            table_rek_zam.setVisible(true);
            table_rek_tran.setVisible(false);

        }else if(strona.equals("Transakcje")){

            //wyczyszczenie listy
            tr_list.clear();

            //pobranie id transakcji
            id=rek_choicebox_id_trans.getSelectionModel().getSelectedItem();

            //pobranie danych z BD
            String wynik[]=connection.uzyskajDane("Select "+
                    "(case WHEN ppr.id_pamieci_ram IS NOT NULL THEN ppr.nazwa_produktu "+
                    "WHEN ppg.id_plyty_glownej IS NOT NULL THEN ppg.nazwa_produktu "+
                    "WHEN pkg.id_karty_graficznej IS NOT NULL THEN pkg.nazwa_produktu "+
                    "WHEN pproc.id_procesora IS NOT NULL THEN pproc.nazwa_produktu "+
                    "WHEN pd.id_dysku IS NOT NULL THEN pd.nazwa_produktu "+
                    "else null "+
                    "end) "+
                    "as nazwa_produktu, "+
                    "(case when id_reklamacji is null then 'Nie' else 'Tak' end) "+
                    "from Produkt p "+
                    "LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram "+
                    "LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej "+
                    "LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej "+
                    "LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora "+
                    "LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku "+
                    "left join reklamacja r on p.id_produktu=r.id_produktu "+
                    "where p.id_transakcji="+id);

            if(wynik.length<1){
                //gdy zapytanie nie zwróciło żądnych wyników

                //wyświetlenie alertu informacyjnego
                informationAlert("Brak danych do wyświetlenia");

                tr_list.clear();
            }else{
                for(int i=0;i<wynik.length;i+=2){
                    tr_list.add(new TableReklamacje(wynik[i],wynik[i+1]));
                }
            }

            //wypełnienie tabeli danymi
            table_rek_tran.setItems(tr_list);

            //wyświetlenie odpowiedniej tabeli
            table_rek_zam.setVisible(false);
            table_rek_tran.setVisible(true);
        }
    }

    //funkcja do wybierania produktu do reklamacji
    @FXML
    void wybierzProduktRekl(){

        String strona=rek_typ.getSelectionModel().getSelectedItem();

        if(strona.equals("Zamówienia")){

            Integer index=table_rek_zam.getSelectionModel().getSelectedIndex();

            nazwaWybranegoProduktuDoReklamacji=trz_nazwa.getCellData(index);
            czyReklamowano=trz_reklamowano.getCellData(index);


        }else if(strona.equals("Transakcje")){

            Integer index=table_rek_tran.getSelectionModel().getSelectedIndex();

            nazwaWybranegoProduktuDoReklamacji=trt_nazwa.getCellData(index);
            czyReklamowano=trt_reklamowano.getCellData(index);

        }
    }

    //funkcja do składania reklamacji
    @FXML
    void reklamuj(){
        String strona=rek_typ.getSelectionModel().getSelectedItem();
        String id="";
        String id_produktu="";

        if(czyReklamowano.equals("Nie")) {
            if (strona.equals("Zamówienia")) {

                //pobranie id zamówienia
                id = rek_choicebox_id_zam.getSelectionModel().getSelectedItem();

                //pobranie danych z BD
                String wynik[] = connection.uzyskajDane("Select case when " +
                        "(case WHEN ppr.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN  ppr.id_pamieci_ram " +
                        "WHEN ppg.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN ppg.id_plyty_glownej " +
                        "WHEN pkg.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN pkg.id_karty_graficznej " +
                        "WHEN pproc.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN  pproc.id_procesora " +
                        "WHEN pd.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN pd.id_dysku " +
                        "end) is not null then p.id_produktu end " +
                        "as id_produktu " +
                        "from Produkt p " +
                        "LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram " +
                        "LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej " +
                        "LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej " +
                        "LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora " +
                        "LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku " +
                        "where p.id_zamowienia=" + id);

                for (int i = 0; i < wynik.length; i++) {
                    if (!wynik[i].equals("null")) {
                        id_produktu = wynik[i];
                    }
                }


            } else if (strona.equals("Transakcje")) {

                //pobranie id transakcji
                id = rek_choicebox_id_trans.getSelectionModel().getSelectedItem();

                //pobranie danych z BD
                String wynik[] = connection.uzyskajDane("Select case when " +
                        "(case WHEN ppr.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN  ppr.id_pamieci_ram " +
                        "WHEN ppg.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN ppg.id_plyty_glownej " +
                        "WHEN pkg.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN pkg.id_karty_graficznej " +
                        "WHEN pproc.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN  pproc.id_procesora " +
                        "WHEN pd.nazwa_produktu='" + nazwaWybranegoProduktuDoReklamacji + "' THEN pd.id_dysku " +
                        "end) is not null then p.id_produktu end " +
                        "as id_produktu " +
                        "from Produkt p " +
                        "LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram " +
                        "LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej " +
                        "LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej " +
                        "LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora " +
                        "LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku " +
                        "where p.id_transakcji=" + id);

                for (int i = 0; i < wynik.length; i++) {
                    if (!wynik[i].equals("null")) {
                        id_produktu = wynik[i];
                    }
                }
            }
            try {//pobranie powodu reklamacji
                String powod = rek_powod.getText();

                if (powod.equals("")) {
                    throw new EmptyValueException();
                }

                //pobranie i zedytowanie aktualnej daty
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter formatowanie = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                String formattedDate = date.format(formatowanie);

                //zgłoszenie reklamacji i zapisanie jej do BD
                connection.wprowadzDane("Insert into reklamacja values(" +
                        "(select case when max(id_reklamacji)>0 then max(id_reklamacji)+1 else 1 end from reklamacja)," +
                        id_produktu + ",'" +
                        powod + "','" +
                        formattedDate + "'" +
                        ")");

                rekPokazTabele();

            } catch (EmptyValueException e) {
                informationAlert("Powód nie może być pusty");
            }
        }else{
            informationAlert("Dany produkt był już reklamowany");
        }
    }

    @FXML
    void wyloguj(MouseEvent event) throws IOException {
        dane.getInstance().setIdZalogowanegoUzytkownika("0");

        root = FXMLLoader.load(getClass().getResource("wyszukiwarka" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void zmienHaslo(MouseEvent event) {
        String wynik[] = connection.uzyskajDane("Select haslo from uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);

        if(checkbox_stare_haslo.isSelected())
        {
            passwordField_stare_haslo.setText(textField_stare_haslo.getText());
        }
        else {
            textField_stare_haslo.setText(passwordField_stare_haslo.getText());
        }
        System.out.println("w: "+wynik[0]);
        System.out.println("tf: "+textField_stare_haslo.getText());
        if(textField_stare_haslo.getText().equals(wynik[0]))
        {
            if(checkbox_nowe_haslo.isSelected())
            {
                passwordField_nowe_haslo.setText(textField_nowe_haslo.getText());
            }
            else {
                textField_nowe_haslo.setText(passwordField_nowe_haslo.getText());
            }

            if(checkbox_powtorz_haslo.isSelected())
            {
                passwordField_powtorz_haslo.setText(textField_powtorz_haslo.getText());
            }
            else {
                textField_powtorz_haslo.setText(passwordField_powtorz_haslo.getText());
            }

            if(textField_nowe_haslo.getText().equals(textField_powtorz_haslo.getText())) {
                try {
                    Pattern pat_ha = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(){};:<>~?_=+-]).{6,20}$");
                    Matcher matcher = pat_ha.matcher(textField_nowe_haslo.getText());

                    if (textField_nowe_haslo.getText().length() < 6 || textField_nowe_haslo.getText().length() > 20 || !matcher.find()) {
                        throw new BadPasswordException();
                    }
                    else
                    {
                        connection.wprowadzDane("UPDATE Uzytkownik SET haslo = '" + textField_nowe_haslo.getText()+"' WHERE id_uzytkownika = " + idZalogowanegoUzytkownika);
                    }
                } catch (BadPasswordException ex) {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setContentText("Nowe hasło nie spełnia wymagań!");
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setContentText("Nowe hasło i hasło powtórzone są różne!");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setContentText("Wprowadzono niepoprawne stare hasło!");
            alert.showAndWait();
        }
    }

    @FXML
    void pokazNoweHaslo(MouseEvent event) {
        if(!checkbox_nowe_haslo.isSelected())
        {
            passwordField_nowe_haslo.setText(textField_nowe_haslo.getText());
            passwordField_nowe_haslo.setVisible(true);
            textField_nowe_haslo.setVisible(false);
            return;
        }
        textField_nowe_haslo.setText(passwordField_nowe_haslo.getText());
        passwordField_nowe_haslo.setVisible(false);
        textField_nowe_haslo.setVisible(true);
    }

    @FXML
    void pokazPowtorzoneHaslo(MouseEvent event) {
        if(!checkbox_powtorz_haslo.isSelected())
        {
            passwordField_powtorz_haslo.setText(textField_powtorz_haslo.getText());
            passwordField_powtorz_haslo.setVisible(true);
            textField_powtorz_haslo.setVisible(false);
            return;
        }
        textField_powtorz_haslo.setText(passwordField_powtorz_haslo.getText());
        passwordField_powtorz_haslo.setVisible(false);
        textField_powtorz_haslo.setVisible(true);
    }

    @FXML
    void pokazStareHaslo(MouseEvent event) {
        if(!checkbox_stare_haslo.isSelected())
        {
            passwordField_stare_haslo.setText(textField_stare_haslo.getText());
            passwordField_stare_haslo.setVisible(true);
            textField_stare_haslo.setVisible(false);
            return;
        }
        textField_stare_haslo.setText(passwordField_stare_haslo.getText());
        passwordField_stare_haslo.setVisible(false);
        textField_stare_haslo.setVisible(true);
    }

}
