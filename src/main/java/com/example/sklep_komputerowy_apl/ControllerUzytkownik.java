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
    private String idZalogowoanegoUuzytkownika;

    //Elementy
    //Elementy poza zakładkami
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
    private TableView<TableOpinieUzytkownika> table_opinie;

    @FXML
    private TableColumn<TableOpinieUzytkownika, String> column_opinie_data;

    @FXML
    private TableColumn<TableOpinieUzytkownika, String> column_opinie_nazwa_produktu;

    @FXML
    private TableColumn<TableOpinieUzytkownika, Double> column_opinie_ocena;

    @FXML
    private TableColumn<TableOpinieUzytkownika, String> column_opinie_tresc;

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
        column_opinie_data.setCellValueFactory(new PropertyValueFactory<TableOpinieUzytkownika, String>("data"));
        column_opinie_ocena.setCellValueFactory(new PropertyValueFactory<TableOpinieUzytkownika, Double>("ocena"));
        column_opinie_tresc.setCellValueFactory(new PropertyValueFactory<TableOpinieUzytkownika, String>("tresc"));
        column_opinie_nazwa_produktu.setCellValueFactory(new PropertyValueFactory<TableOpinieUzytkownika, String>("nazwa"));
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
        ObservableList<TableOpinieUzytkownika> tou_list = FXCollections.observableArrayList();
        ObservableList<TableTransakcjeUzytkownika> ttu_list = FXCollections.observableArrayList();
        ObservableList<TableZamowieniaUzytkownika> tzu_list = FXCollections.observableArrayList();

        //TableKomunikatyUzytkownika
        String wynik[] = connection.uzyskajDane("SELECT data, tresc " +
                "FROM Wiadomosc " +
                "WHERE id_uzytkownika = " + idZalogowoanegoUuzytkownika + ";");
        for (int i = 0; i < wynik.length; i += 2) {
            tku_list.add(new TableKomunikatyUzytkownika(wynik[i], wynik[i + 1]));
        }
        table_profil_komunikaty.setItems(tku_list);


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
        dane.zapiszAktualnaStrone("zatwierdzenie usuniecia");
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
