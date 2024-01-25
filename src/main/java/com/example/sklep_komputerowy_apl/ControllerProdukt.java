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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tables.TableOpinie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerProdukt implements Initializable {
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();
    int kategoria=-1;
    String idtypu="";


    private String idWybranegoProduktu=dane.getIdWybranegoProduktu();
    private String idZalogowanegoUzytkownika=dane.getIdZalogowanegoUzytkownika();

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Elementy
    //Elementy poza zakladkami
    @FXML
    private AnchorPane background;

    @FXML
    private Button button_cart;

    @FXML
    private Button button_wroc;

    @FXML
    private Button button_dodaj_do_koszyka;

    @FXML
    private TextField textField_liczba_egzemplarzy;

    @FXML
    private Text button_home;

    @FXML
    private Button button_opinie;

    @FXML
    private Button button_specyfikacja;

    @FXML
    private ImageView image_cart;

    //Elementy logowania/wylogowania
    @FXML
    private Button button_value_of_name;

    @FXML
    private AnchorPane wyloguj;

    @FXML
    private ImageView image_user;

    @FXML
    private AnchorPane zaloguj;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zaloguj;

    //Elementy w zakladce opinie
    @FXML
    private AnchorPane opinie;

    @FXML
    private Button button_dodaj_opinie;

    @FXML
    private TableView<TableOpinie> table_produkt_opinia;

    @FXML
    private TableColumn<TableOpinie, String> column_produkt_opinia_nazwa_produktu;

    @FXML
    private TableColumn<TableOpinie, Double> column_produkt_opinia_ocena;

    @FXML
    private TableColumn<TableOpinie, String> column_produkt_opinia_tresc;

    //Elementy w zakladce pamiec_ram
    @FXML
    private AnchorPane pamiec_ram;

    @FXML
    private Text value_of_cena_pr;

    @FXML
    private Text value_of_napiecie_pr;

    @FXML
    private Text value_of_nazwa_produktu_pr;

    @FXML
    private Text value_of_opis_pr;

    @FXML
    private Text value_of_pojemnosc_pr;

    @FXML
    private Text value_of_producent_pr;

    @FXML
    private Text value_of_rodzaj_pamieci_pr;

    @FXML
    private Text value_of_taktowanie_pr;

    //Elementy w zakladce plyta_glowna
    @FXML
    private AnchorPane plyta_glowna;

    @FXML
    private Text value_of_cena_pg;

    @FXML
    private Text value_of_chipset_pg;

    @FXML
    private Text value_of_gniazdo_procesora_pg;

    @FXML
    private Text value_of_liczba_bankow_pamieci_pg;

    @FXML
    private Text value_of_max_wielkosc_pamieci_pg;

    @FXML
    private Text value_of_nazwa_produktu_pg;

    @FXML
    private Text value_of_opis_pg;

    @FXML
    private Text value_of_producent_pg;

    @FXML
    private Text value_of_szerokosc_pg;

    @FXML
    private Text value_of_typ_obslugiwanej_pamieci_pg;

    @FXML
    private Text value_of_wysokosc_pg;

    //Elementy w zakladce karta_graficzna
    @FXML
    private AnchorPane karta_graficzna;

    @FXML
    private Text value_of_cena_kg;

    @FXML
    private Text value_of_glebokosc_kg;

    @FXML
    private Text value_of_nazwa_produktu_kg;

    @FXML
    private Text value_of_opis_kg;

    @FXML
    private Text value_of_pobor_mocy_kg;

    @FXML
    private Text value_of_producent_kg;

    @FXML
    private Text value_of_rodzaj_pamieci_kg;

    @FXML
    private Text value_of_rodzaj_zlacza_kg;

    @FXML
    private Text value_of_szerokosc_kg;

    @FXML
    private Text value_of_taktowanie_kg;

    @FXML
    private Text value_of_typ_obslugiwanej_pamiec_kg;

    @FXML
    private Text value_of_uklad_graficzny_kg;

    @FXML
    private Text value_of_wysokosc_kg;

    //Elementy w zakladce procesor
    @FXML
    private AnchorPane procesor;

    @FXML
    private Text value_of_cena_proc;

    @FXML
    private Text value_of_gniazdo_procesora_proc;

    @FXML
    private Text value_of_liczba_rdzeni_proc;

    @FXML
    private Text value_of_liczba_watkow_proc;

    @FXML
    private Text value_of_nazwa_produktu_proc;

    @FXML
    private Text value_of_opis_proc;

    @FXML
    private Text value_of_pobor_mocy_proc;

    @FXML
    private Text value_of_producent_proc;

    @FXML
    private Text value_of_rodzina_proc;

    @FXML
    private Text value_of_taktowanie_proc;

    //Elementy w zakladce dysk
    @FXML
    private AnchorPane dysk;

    @FXML
    private Text value_of_cena_dy;

    @FXML
    private Text value_of_glebokosc_dy;

    @FXML
    private Text value_of_nazwa_produktu_dy;

    @FXML
    private Text value_of_opis_dy;

    @FXML
    private Text value_of_pojemnosc_dy;

    @FXML
    private Text value_of_producent_dy;

    @FXML
    private Text value_of_szerokosc_dy;

    @FXML
    private Text value_of_typ_dysku_dy;

    @FXML
    private Text value_of_wysokosc_dy;

    //Elementy w zakladce dodawania opinii
    @FXML
    private AnchorPane dodawanie_opinii;

    @FXML
    private Button button_anuluj_dodawanie_opinii;

    @FXML
    private Button button_zatwierdz;

    @FXML
    private TextField text_field_komentarz;

    @FXML
    private TextField text_field_ocena;


    @FXML
    private Text value_of_name_dodawanie_opinii;


    //-------------------------------------
    //Funkcje
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //ustawienie tabel
        //OpinieUzytkownika
        column_produkt_opinia_ocena.setCellValueFactory(new PropertyValueFactory<TableOpinie, Double>("ocena"));
        column_produkt_opinia_tresc.setCellValueFactory(new PropertyValueFactory<TableOpinie, String>("komentarz"));
        column_produkt_opinia_nazwa_produktu.setCellValueFactory(new PropertyValueFactory<TableOpinie, String>("nazwa"));

        //wypełnienie tabel
        //utworzenie list do wypełniania odpowiednich typów
        ObservableList<TableOpinie> top_list = FXCollections.observableArrayList();

        //Ustalenie kategorii wybranego produkt
        String wynik[] = connection.uzyskajDane("Select id_pamieci_ram, id_plyty_glownej, id_karty_graficznej, id_procesora, id_dysku from produkt where id_produktu = " + idWybranegoProduktu);
        for (int i = 0; i < wynik.length; i ++) {
            if(!wynik[i].equals("null"))
            {
                idtypu=wynik[i];
                kategoria=i;
                break;
            }
        }

        //TableOpinie
        wynik = connection.uzyskajDane("Select" +
                " o.ocena," +
                " o.komentarz," +
                " CASE" +
                " WHEN ppr.id_pamieci_ram IS NOT NULL THEN ppr.nazwa_produktu" +
                " WHEN ppg.id_plyty_glownej IS NOT NULL THEN ppg.nazwa_produktu" +
                " WHEN pkg.id_karty_graficznej IS NOT NULL THEN pkg.nazwa_produktu" +
                " WHEN pproc.id_procesora IS NOT NULL THEN pproc.nazwa_produktu" +
                " WHEN pd.id_dysku IS NOT NULL THEN pd.nazwa_produktu" +
                " ELSE NULL" +
                " END AS nazwa_produktu" +
                " FROM produkt p" +
                " LEFT JOIN Pamiec_RAM ppr ON p.id_pamieci_ram = ppr.id_pamieci_ram" +
                " LEFT JOIN Plyta_glowna ppg ON p.id_plyty_glownej = ppg.id_plyty_glownej" +
                " LEFT JOIN Karta_graficzna pkg ON p.id_karty_graficznej = pkg.id_karty_graficznej" +
                " LEFT JOIN Procesor pproc ON p.id_procesora = pproc.id_procesora" +
                " LEFT JOIN Dysk pd ON p.id_dysku = pd.id_dysku" +
                " JOIN opinia o on o.id_pamieci_ram=ppr.id_pamieci_ram or o.id_plyty_glownej=ppg.id_plyty_glownej or o.id_karty_graficznej=pkg.id_karty_graficznej or o.id_procesora=pproc.id_procesora or o.id_dysku=pd.id_dysku" +
                " WHERE p.id_produktu = " + idWybranegoProduktu);
        if(wynik.length!=1) {
            for (int i = 0; i < wynik.length; i += 3) {
                top_list.add(new TableOpinie(Double.parseDouble(wynik[i]), wynik[i + 2], wynik[i + 1]));
            }
            table_produkt_opinia.setItems(top_list);
        }

        switch(kategoria) {
            case 0:
                //Specyfikacja pamiec_ram
                wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena, rodzaj_pamieci, pojemnosc, taktowanie, napiecie, opis" +
                        " from produkt join pamiec_ram on produkt.id_pamieci_ram=pamiec_ram.id_pamieci_ram" +
                        " where id_produktu = " + idWybranegoProduktu);
                value_of_nazwa_produktu_pr.setText(wynik[0]);
                value_of_producent_pr.setText(wynik[1]);
                value_of_cena_pr.setText(wynik[2] + " zł");
                value_of_rodzaj_pamieci_pr.setText(wynik[3]);
                value_of_pojemnosc_pr.setText(wynik[4]+ " Gb");
                value_of_taktowanie_pr.setText(wynik[5]+ " Hz");
                value_of_napiecie_pr.setText(wynik[6]+ " V");
                value_of_opis_pr.setText(wynik[7]);
                value_of_name_dodawanie_opinii.setText(wynik[0]);
                break;
            case 1:
                //Specyfikacja pamiec_ram
                wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena, gniazdo_procesora, chipset, typ_obslugiwanej_pamieci, liczba_bankow_pamieci, max_wielkosc_pamieci, szerokosc, wysokosc, opis" +
                        " from produkt join plyta_glowna on produkt.id_plyty_glownej=plyta_glowna.id_plyty_glownej" +
                        " where id_produktu = " + idWybranegoProduktu);
                value_of_nazwa_produktu_pg.setText(wynik[0]);
                value_of_producent_pg.setText(wynik[1]);
                value_of_cena_pg.setText(wynik[2] + " zł");
                value_of_gniazdo_procesora_pg.setText(wynik[3]);
                value_of_chipset_pg.setText(wynik[4]);
                value_of_typ_obslugiwanej_pamieci_pg.setText(wynik[5]);
                value_of_liczba_bankow_pamieci_pg.setText(wynik[6]);
                value_of_max_wielkosc_pamieci_pg.setText(wynik[7]+" Gb");
                value_of_szerokosc_pg.setText(wynik[8]+ " mm");
                value_of_wysokosc_pg.setText(wynik[9]+ " mm");
                value_of_opis_pg.setText(wynik[10]);
                value_of_name_dodawanie_opinii.setText(wynik[0]);
                break;
            case 2:
                //Specyfikacja karta_graficzna
                wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena, uklad_graficzny, rodzaj_zlacza, pamiec, rodzaj_pamieci, taktowanie_pamieci, szerokosc, wysokosc, glebokosc, pobor_mocy, opis" +
                        " from produkt join karta_graficzna on produkt.id_karty_graficznej=karta_graficzna.id_karty_graficznej" +
                        " where id_produktu = " + idWybranegoProduktu);
                value_of_nazwa_produktu_kg.setText(wynik[0]);
                value_of_producent_kg.setText(wynik[1]);
                value_of_cena_kg.setText(wynik[2] + " zł");
                value_of_uklad_graficzny_kg.setText(wynik[3]);
                value_of_rodzaj_zlacza_kg.setText(wynik[4]);
                value_of_typ_obslugiwanej_pamiec_kg.setText(wynik[5]);
                value_of_rodzaj_pamieci_kg.setText(wynik[6]);
                value_of_taktowanie_kg.setText(wynik[7]+" Hz");
                value_of_szerokosc_kg.setText(wynik[8]+ " mm");
                value_of_wysokosc_kg.setText(wynik[9]+ " mm");
                value_of_glebokosc_kg.setText(wynik[10]+ " mm");
                value_of_pobor_mocy_kg.setText(wynik[11]+ " W");
                value_of_opis_kg.setText(wynik[12]);
                value_of_name_dodawanie_opinii.setText(wynik[0]);
                break;
            case 3:
                //Specyfikacja_procesor
                wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena, rodzina, gniazdo_procesora, taktowanie, liczba_rdzeni, liczba_watkow, pobor_mocy, opis" +
                        " from produkt join procesor on produkt.id_procesora=procesor.id_procesora" +
                        " where id_produktu = " + idWybranegoProduktu);
                value_of_nazwa_produktu_proc.setText(wynik[0]);
                value_of_producent_proc.setText(wynik[1]);
                value_of_cena_proc.setText(wynik[2] + " zł");
                value_of_rodzina_proc.setText(wynik[3]);
                value_of_gniazdo_procesora_proc.setText(wynik[4]);
                value_of_taktowanie_proc.setText(wynik[5]+ " Hz");
                value_of_liczba_rdzeni_proc.setText(wynik[6]);
                value_of_liczba_watkow_proc.setText(wynik[7]);
                value_of_pobor_mocy_proc.setText(wynik[8]+ " W");
                value_of_opis_proc.setText(wynik[9]);
                value_of_name_dodawanie_opinii.setText(wynik[0]);
                break;
            case 4:
                //Specyfikacja_procesor
                wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena, pojemnosc, typ_dysku, szerokosc,wysokosc, glebokosc, opis" +
                        " from produkt join dysk on produkt.id_dysku=dysk.id_dysku" +
                        " where id_produktu = " + idWybranegoProduktu);
                value_of_nazwa_produktu_dy.setText(wynik[0]);
                value_of_producent_dy.setText(wynik[1]);
                value_of_cena_dy.setText(wynik[2] + " zł");
                value_of_pojemnosc_dy.setText(wynik[3]+" Gb");
                value_of_typ_dysku_dy.setText(wynik[4]);
                value_of_szerokosc_dy.setText(wynik[5]+" mm");
                value_of_wysokosc_dy.setText(wynik[6]+" mm");
                value_of_glebokosc_dy.setText(wynik[7]+" mm");
                value_of_opis_dy.setText(wynik[8]);
                value_of_name_dodawanie_opinii.setText(wynik[0]);
                break;
        }

        if(!idZalogowanegoUzytkownika.equals("0")) {
            zaloguj.setVisible(false);
            wyloguj.setVisible(true);
            wynik = connection.uzyskajDane("Select imie from uzytkownik where id_uzytkownika = " + idZalogowanegoUzytkownika);
            button_value_of_name.setText(wynik[0]);
        }
        else
        {
            wyloguj.setVisible(false);
            zaloguj.setVisible(true);
        }

        showSpecyfikacja();
    }

    @FXML
    void addToCart(MouseEvent event) {
        int pom;

        try {
            Double.parseDouble(textField_liczba_egzemplarzy.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setContentText("Wprowadzona wartość nie jest liczbą");
            alert.getDialogPane().setMinHeight(200);
            alert.showAndWait();
            return;
        }

        if(!(Integer.parseInt(textField_liczba_egzemplarzy.getText())==0)) {
            for (int i = 0; i < dane.getIdProduktowWKoszyku().size(); i = i + 3) {
                if (dane.getIdProduktowWKoszyku().get(i).equals(String.valueOf(kategoria + 1)) && dane.getIdProduktowWKoszyku().get(i + 1).equals(idtypu)) {
                    pom = Integer.parseInt(dane.getIdProduktowWKoszyku().get(i + 2));
                    pom = pom + Integer.parseInt(textField_liczba_egzemplarzy.getText());
                    dane.getIdProduktowWKoszyku().set(i + 2, String.valueOf(pom));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sukces");
                    alert.setContentText("Dodano do koszyka!");
                    alert.getDialogPane().setMinHeight(200);
                    alert.showAndWait();
                    return;
                }
            }

            dane.getIdProduktowWKoszyku().add(String.valueOf(kategoria + 1));
            dane.getIdProduktowWKoszyku().add(idtypu);
            dane.getIdProduktowWKoszyku().add(textField_liczba_egzemplarzy.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces");
            alert.setContentText("Dodano do koszyka!");
            alert.getDialogPane().setMinHeight(200);
            alert.showAndWait();
        }
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
    void goToUzytkownik(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("uzytkownik" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showOpinie(MouseEvent event) {
        pamiec_ram.setVisible(false);
        plyta_glowna.setVisible(false);
        karta_graficzna.setVisible(false);
        procesor.setVisible(false);
        dysk.setVisible(false);
        opinie.setVisible(true);
        dodawanie_opinii.setVisible(false);
    }

    void showSpecyfikacja() {
        switch(kategoria) {
            case 0:
                pamiec_ram.setVisible(true);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(false);
                procesor.setVisible(false);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 1:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(true);
                karta_graficzna.setVisible(false);
                procesor.setVisible(false);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 2:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(true);
                procesor.setVisible(false);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 3:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(false);
                procesor.setVisible(true);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 4:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(false);
                procesor.setVisible(false);
                dysk.setVisible(true);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
        }
    }


    @FXML
    void showDodajOpinie(MouseEvent event) {
        text_field_komentarz.setText("");
        text_field_ocena.setText("0.0");

        pamiec_ram.setVisible(false);
        plyta_glowna.setVisible(false);
        karta_graficzna.setVisible(false);
        procesor.setVisible(false);
        dysk.setVisible(false);
        opinie.setVisible(false);
        dodawanie_opinii.setVisible(true);
    }

    @FXML
    void showSpecyfikacja(MouseEvent event) {

        switch(kategoria) {
            case 0:
                pamiec_ram.setVisible(true);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(false);
                procesor.setVisible(false);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 1:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(true);
                karta_graficzna.setVisible(false);
                procesor.setVisible(false);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 2:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(true);
                procesor.setVisible(false);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 3:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(false);
                procesor.setVisible(true);
                dysk.setVisible(false);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
            case 4:
                pamiec_ram.setVisible(false);
                plyta_glowna.setVisible(false);
                karta_graficzna.setVisible(false);
                procesor.setVisible(false);
                dysk.setVisible(true);
                opinie.setVisible(false);
                dodawanie_opinii.setVisible(false);
                break;
        }
    }

    @FXML
    void wyloguj(MouseEvent event) throws IOException {
        dane.setIdZalogowanegoUzytkownika("0");
        root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zaloguj(MouseEvent event) throws IOException {
        dane.setDestynacjaPowrotuZeStronyLogowania("produkt");
        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zatwierdzOpinie(MouseEvent event) throws IOException {
        try {
            Double.parseDouble(text_field_ocena.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setContentText("Wprowadzona wartość nie jest liczbą");
            alert.getDialogPane().setMinHeight(200);
            alert.showAndWait();
            return;
        }

        if (Double.parseDouble(text_field_ocena.getText()) >= 0 && Double.parseDouble(text_field_ocena.getText()) <= 5) {
            if (text_field_komentarz.getLength() != 0) {
                if (text_field_komentarz.getLength() <= 300) {
                    String wynik[];
                    wynik=connection.uzyskajDane("Select count(*) from opinia");

                    switch(kategoria) {
                        case 0:
                            connection.wprowadzDane("Insert INTO Opinia (id_opinii, id_uzytkownika, id_pamieci_ram, ocena, komentarz) VALUES ("+(Integer.parseInt(wynik[0])+1)+", "+idZalogowanegoUzytkownika+", "+idtypu+", "+text_field_ocena.getText()+", '"+text_field_komentarz.getText()+"')");
                            break;
                        case 1:
                            connection.wprowadzDane("Insert INTO Opinia (id_opinii, id_uzytkownika, id_plyty_glownej, ocena, komentarz) VALUES ("+(Integer.parseInt(wynik[0])+1)+", "+idZalogowanegoUzytkownika+", "+idtypu+", "+text_field_ocena.getText()+", '"+text_field_komentarz.getText()+"')");
                            break;
                        case 2:
                            connection.wprowadzDane("Insert INTO Opinia (id_opinii, id_uzytkownika, id_karty_graficznej, ocena, komentarz) VALUES ("+(Integer.parseInt(wynik[0])+1)+", "+idZalogowanegoUzytkownika+", "+idtypu+", "+text_field_ocena.getText()+", '"+text_field_komentarz.getText()+"')");
                            break;
                        case 3:
                            connection.wprowadzDane("Insert INTO Opinia (id_opinii, id_uzytkownika, id_procesora, ocena, komentarz) VALUES ("+(Integer.parseInt(wynik[0])+1)+", "+idZalogowanegoUzytkownika+", "+idtypu+", "+text_field_ocena.getText()+", '"+text_field_komentarz.getText()+"')");
                            break;
                        case 4:
                            connection.wprowadzDane("Insert INTO Opinia (id_opinii, id_uzytkownika, id_dysku, ocena, komentarz) VALUES ("+(Integer.parseInt(wynik[0])+1)+", "+idZalogowanegoUzytkownika+", "+idtypu+", "+text_field_ocena.getText()+", '"+text_field_komentarz.getText()+"')");
                            break;
                    }

                    root = FXMLLoader.load(getClass().getResource("produkt" + ".fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setContentText("Komentarz przekroczył limit długości wynoszący 300 znaków!");
                    alert.getDialogPane().setMinHeight(200);
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setContentText("Proszę wprowadzić komentarz!");
                alert.getDialogPane().setMinHeight(200);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setContentText("Wprowadzona liczba nie mieści się w zakresie <0,5>");
            alert.getDialogPane().setMinHeight(200);
            alert.showAndWait();
        }
    }

}