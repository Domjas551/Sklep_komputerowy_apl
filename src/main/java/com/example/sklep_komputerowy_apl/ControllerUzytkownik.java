package com.example.sklep_komputerowy_apl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerUzytkownik implements Initializable {
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    //Wartosci przyjete roboczo
    private String idZalogowanegoUzytkownika="43";

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
    private Button button_zmienHaslo;

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
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_data_odbioru;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_data_zlozenia;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_nazwy_produktow;

    @FXML
    private TableColumn<TableZamowieniaUzytkownika, String> column_zamowienia_status;


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
        column_transakcje_cena.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, Double>("cena"));
        column_transakcje_data.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, String>("data"));
        column_transakcje_nazwy_produktow.setCellValueFactory(new PropertyValueFactory<TableTransakcjeUzytkownika, String>("nazwy"));
        //ZamowieniaUzytkownika
        column_zamowienia_data_zlozenia.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("data_zlo"));
        column_zamowienia_data_odbioru.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("data_odb"));
        column_zamowienia_status.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("status"));
        column_zamowienia_nazwy_produktow.setCellValueFactory(new PropertyValueFactory<TableZamowieniaUzytkownika, String>("nazwy"));

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
                " GROUP BY t.data_t, t.cena_calkowita");
        if(wynik.length!=1) {
            for (int i = 0; i < wynik.length; i += 3) {
                ttu_list.add(new TableTransakcjeUzytkownika(wynik[i], wynik[i + 2], Double.parseDouble(wynik[i + 1])));
            }
            table_transakcje.setItems(ttu_list);
        }

        //TableZamowieniaUzytkownika
        wynik = connection.uzyskajDane("Select " +
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
                " GROUP BY z.data_zlozenia, z.data_odbioru, z.status_odbioru");
        if(wynik.length!=1) {
            for (int i = 0; i < wynik.length; i += 4) {
                tzu_list.add(new TableZamowieniaUzytkownika(wynik[i], wynik[i + 1], wynik[i + 3], wynik[i + 2]));
            }
            table_zamowienia.setItems(tzu_list);
        }

        //Button_value_of_name
        wynik = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        button_value_of_name.setText(wynik[0]);

        //Value_of_name
        wynik = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        value_of_imie.setText(wynik[0]);

        //Value_of_nazwisko
        wynik = connection.uzyskajDane("Select nazwisko from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        value_of_nazwisko.setText(wynik[0]);

        //Value_of_name
        wynik = connection.uzyskajDane("Select email from Uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
        value_of_email.setText(wynik[0]);
    }



    @FXML
    void deleteAccount(MouseEvent event) {
        //TODO
    }

    @FXML
    void goHome(MouseEvent event) {
        //TODO
    }


    @FXML
    void goToCart(MouseEvent event) {
        //TODO
    }

    //funkcja wyświetlająca strone opinii uzytkownika
    @FXML
    void showOpinie(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(true);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("opinie");
    }

    //funkcja wyświetlająca strone profilu
    @FXML
    void showProfil(MouseEvent event) {
        profil.setVisible(true);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("Profil");
    }

    //funkcja wyświetlająca strone transakcji uzytkownika
    @FXML
    void showTransakcje(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(true);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("transakcje");
    }

    //funkcja wyświetlająca strone zamowien uzytkownika
    @FXML
    void showZamowienia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(true);
        zatwierdzenie_usuniecia.setVisible(false);
        opinie.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("zamowienia");
    }

    //funkcja wyświetlająca strone zatwierdzenia usuniecia konta
    @FXML
    void showZatwierdzenieUsuniecia(MouseEvent event) {
        profil.setVisible(false);
        transakcje.setVisible(false);
        zamowienia.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(true);
        opinie.setVisible(false);

        //uaktualnienie informacji o aktywnej stronie
        dane.zapiszAktualnaStrone("zatwierdzenie_usuniecia");
    }

    @FXML
    void wyloguj(MouseEvent event) {
        //TODO
    }

    @FXML
    void zmienHaslo(MouseEvent event) {
        //TODO
    }

}
