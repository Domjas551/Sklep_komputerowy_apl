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

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControllerKoszyk implements Initializable {
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    private ArrayList<String> id_produktow_w_koszyku= dane.getIdProduktowWKoszyku();
    private ArrayList<String> id_zestawow_w_koszyku= dane.getIdZestawowWKoszyku();
    private String idZalogowanegoUzytkownika=dane.getIdZalogowanegoUzytkownika();
    private boolean czyZamowienie;
    private double cenaFinalna;
    private String idGoscia;

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;


    //Elementy
    @FXML
    private AnchorPane background;

    @FXML
    private Button button_gosc_dalej;

    @FXML
    private Text button_home;

    @FXML
    private Button button_kup;

    @FXML
    private Button button_oproznij;

    @FXML
    private Button button_rabat_finalizuj;

    @FXML
    private Button button_value_of_name;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zaloguj;

    @FXML
    private Button button_zamow;

    @FXML
    private ChoiceBox<?> choiceBox_typ_rabatu;

    @FXML
    private TableColumn<TableProduktWKoszyku, Double> column_koszyk_cena;

    @FXML
    private TableColumn<TableProduktWKoszyku, String> column_koszyk_producent;

    @FXML
    private TableColumn<TableProduktWKoszyku, String> column_koszyk_typ;

    @FXML
    private TableColumn<TableProduktWKoszyku, String> column_koszyk_nazwa_produktu;

    @FXML
    private TableView<TableProduktWKoszyku> table_koszyk;

    @FXML
    private AnchorPane gosc;

    @FXML
    private ImageView image_user;

    @FXML
    private AnchorPane produkty;

    @FXML
    private AnchorPane rabat;

    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_imie;

    @FXML
    private TextField textField_nazwisko;

    @FXML
    private Text value_of_cena_bez_rabatu;

    @FXML
    private Text value_of_cena_calkowita;

    @FXML
    private Text value_of_cena_z_rabatem;

    @FXML
    private AnchorPane wyloguj;

    @FXML
    private AnchorPane zaloguj;


    //-------------------------------------------------------------------
    //Funkcje

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(!(id_produktow_w_koszyku.isEmpty()&&id_zestawow_w_koszyku.isEmpty())) {
            String wynik[]=null;
            Double sumCen=0.0;
            //ustawienie tabel
            //OpinieUzytkownika
            column_koszyk_nazwa_produktu.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, String>("nazwa_produktu"));
            column_koszyk_typ.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, String>("typ"));
            column_koszyk_producent.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, String>("producent"));
            column_koszyk_cena.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, Double>("cena"));

            //wypełnienie tabel
            //utworzenie list do wypełniania odpowiednich typów
            ObservableList<TableProduktWKoszyku> tpk_list = FXCollections.observableArrayList();
            if(!id_produktow_w_koszyku.isEmpty()) {
                for (int i = 0; i < id_produktow_w_koszyku.size(); i = i + 3) {
                    switch (id_produktow_w_koszyku.get(i)) {
                        case "1":
                            wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from pamiec_ram where id_pamieci_ram = " + id_produktow_w_koszyku.get(i + 1));
                            break;
                        case "2":
                            wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from plyta_glowna where id_plyty_glownej = " + id_produktow_w_koszyku.get(i + 1));
                            break;
                        case "3":
                            wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from karta_graficzna where id_karty_graficznej = " + id_produktow_w_koszyku.get(i + 1));
                            break;
                        case "4":
                            wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from procesor where id_procesora = " + id_produktow_w_koszyku.get(i + 1));
                            break;
                        case "5":
                            wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from dysk where id_dysku = " + id_produktow_w_koszyku.get(i + 1));
                            break;
                    }
                    for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++) {
                        switch (id_produktow_w_koszyku.get(i)) {
                            case "1":
                                tpk_list.add(new TableProduktWKoszyku(wynik[0], "Pamięć RAM", wynik[1], Double.parseDouble(wynik[2])));
                                break;
                            case "2":
                                tpk_list.add(new TableProduktWKoszyku(wynik[0], "Płyta główna", wynik[1], Double.parseDouble(wynik[2])));
                                break;
                            case "3":
                                tpk_list.add(new TableProduktWKoszyku(wynik[0], "Karta graficzna", wynik[1], Double.parseDouble(wynik[2])));
                                break;
                            case "4":
                                tpk_list.add(new TableProduktWKoszyku(wynik[0], "Procesor", wynik[1], Double.parseDouble(wynik[2])));
                                break;
                            case "5":
                                tpk_list.add(new TableProduktWKoszyku(wynik[0], "Dysk", wynik[1], Double.parseDouble(wynik[2])));
                                break;
                        }
                    }
                }
            }

            if(!id_zestawow_w_koszyku.isEmpty()) {
                for (int i = 0; i < id_zestawow_w_koszyku.size(); i = i + 2) {
                    String wynik2[] = connection.uzyskajDane("Select id_pamiec_ram, id_plyta_glowna, id_karta_graficzna, id_procesor, id_dysk from zestaw where id_zestawu = " + id_zestawow_w_koszyku.get(i));

                    for (int j = 0; j < Integer.parseInt(dane.getIdZestawowWKoszyku().get(i + 1)); j++) {
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from pamiec_ram where id_pamieci_ram = " + id_produktow_w_koszyku.get(Integer.parseInt(wynik2[0])));
                        tpk_list.add(new TableProduktWKoszyku(wynik[0], "Pamięć RAM", wynik[1], Double.parseDouble(wynik[2])));
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from plyta_glowna where id_plyty_glownej = " + id_produktow_w_koszyku.get(Integer.parseInt(wynik2[1])));
                        tpk_list.add(new TableProduktWKoszyku(wynik[0], "Płyta główna", wynik[1], Double.parseDouble(wynik[2])));
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from karta_graficzna where id_karty_graficznej = " + id_produktow_w_koszyku.get(Integer.parseInt(wynik2[2])));
                        tpk_list.add(new TableProduktWKoszyku(wynik[0], "Karta graficzna", wynik[1], Double.parseDouble(wynik[2])));
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from procesor where id_procesora = " + id_produktow_w_koszyku.get(Integer.parseInt(wynik2[3])));
                        tpk_list.add(new TableProduktWKoszyku(wynik[0], "Procesor", wynik[1], Double.parseDouble(wynik[2])));
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from dysk where id_dysku = " + id_produktow_w_koszyku.get(Integer.parseInt(wynik2[4])));
                        tpk_list.add(new TableProduktWKoszyku(wynik[0], "Dysk", wynik[1], Double.parseDouble(wynik[2])));
                    }
                }
            }

            table_koszyk.setItems(tpk_list);

            //value_of_cena_calkowita
            for (int j = 0; j < tpk_list.size(); j++) {
                sumCen=sumCen+tpk_list.get(j).getCena();
            }
            value_of_cena_calkowita.setText(decfor.format(sumCen)+ " zł");
        }

        String wynik[];
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
    }

    boolean sprawdzDostepnosc()
    {
        String wynik[];

        //Sprawdzenie dostepnosci produktow w koszyku
        if(!id_produktow_w_koszyku.isEmpty()) {
            for (int i = 0; i < id_produktow_w_koszyku.size(); i=i+3) {
                switch (id_produktow_w_koszyku.get(i))
                {
                    case "1":
                        wynik = connection.uzyskajDane("Select count(*) from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_pamieci_ram = "+id_produktow_w_koszyku.get(i+1));
                        if(Integer.parseInt(wynik[0])<Integer.parseInt(id_produktow_w_koszyku.get(i+2)))
                        {
                            return false;
                        }
                        break;
                    case "2":
                        wynik = connection.uzyskajDane("Select count(*) from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_plyty_glownej = "+id_produktow_w_koszyku.get(i+1));
                        if(Integer.parseInt(wynik[0])<Integer.parseInt(id_produktow_w_koszyku.get(i+2)))
                        {
                            return false;
                        }
                        break;
                    case "3":
                        wynik = connection.uzyskajDane("Select count(*) from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_karty_graficznej = "+id_produktow_w_koszyku.get(i+1));
                        if(Integer.parseInt(wynik[0])<Integer.parseInt(id_produktow_w_koszyku.get(i+2)))
                        {
                            return false;
                        }
                        break;
                    case "4":
                        wynik = connection.uzyskajDane("Select count(*) from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_procesora = "+id_produktow_w_koszyku.get(i+1));
                        if(Integer.parseInt(wynik[0])<Integer.parseInt(id_produktow_w_koszyku.get(i+2)))
                        {
                            return false;
                        }
                        break;
                    case "5":
                        wynik = connection.uzyskajDane("Select count(*) from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_dysku = "+id_produktow_w_koszyku.get(i+1));
                        if(Integer.parseInt(wynik[0])<Integer.parseInt(id_produktow_w_koszyku.get(i+2)))
                        {
                            return false;
                        }
                        break;
                }
            }
        }

        if(!id_zestawow_w_koszyku.isEmpty()) {
            for (int i = 0; i < id_zestawow_w_koszyku.size(); i = i + 2) {
                wynik = connection.uzyskajDane("Select count(*) from produkt where id_transakcji is null and id_zamowienia is null and id_pamieci_ram is not null and id_typu_zestawu = " + id_produktow_w_koszyku.get(i));
                if(Integer.parseInt(wynik[0])<Integer.parseInt(id_zestawow_w_koszyku.get(i+1)))
                {
                    return false;
                }
            }
        }

        return true;
    }

    void finalizujTransakcje()
    {
        String id_transakcji[];
        String id_zestawu[];
        String id_uzytkownika;
        String wynik[];

        if(idZalogowanegoUzytkownika.equals(0))
        {
            id_uzytkownika=idGoscia;
        }
        else
        {
            id_uzytkownika=idZalogowanegoUzytkownika;
        }
        //pobranie i zedytowanie aktualnej daty
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatowanie = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = date.format(formatowanie);

        //Dodanie zamowienia do bazy danych
        connection.wprowadzDaneBezAlert("INSERT INTO transakcja (id_transakcji, id_uzytkownika, data_t, cena_calkowita, status) " +
                "VALUES ((SELECT MAX(id_transakcji) + 1 FROM transakcja), " +
                id_uzytkownika+", '" +
                formattedDate+"', " +
                cenaFinalna+", " +
                "'oczekująca')");

        id_transakcji=connection.uzyskajDane("Select max(id_transakcji) from transakcja");


        //Dodanie zakupionych produktow do bazy danych
        if(!id_produktow_w_koszyku.isEmpty()) {
            for (int i = 0; i < id_produktow_w_koszyku.size(); i = i + 3) {
                switch (id_produktow_w_koszyku.get(i)) {
                    case "1":
                        wynik=connection.uzyskajDane("Select id_produktu from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_pamieci_ram = " +
                                id_produktow_w_koszyku.get(i+1)+" fetch first " +
                                id_produktow_w_koszyku.get(i+2)+" rows only");
                        for(int j=0; j< wynik.length; j++)
                        {
                            connection.wprowadzDaneBezAlert("UPDATE produkt SET id_transakcji = " +
                                    id_transakcji+" WHERE id_produktu = "+wynik[j]);
                        }
                        break;
                    case "2":
                        wynik=connection.uzyskajDane("Select id_produktu from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_plyty_glownej = " +
                                id_produktow_w_koszyku.get(i+1)+" fetch first " +
                                id_produktow_w_koszyku.get(i+2)+" rows only");
                        for(int j=0; j< wynik.length; j++)
                        {
                            connection.wprowadzDaneBezAlert("UPDATE produkt SET id_transakcji = " +
                                    id_transakcji+" WHERE id_produktu = "+wynik[j]);
                        }
                        break;
                    case "3":
                        wynik=connection.uzyskajDane("Select id_produktu from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_karty_graficznej = " +
                                id_produktow_w_koszyku.get(i+1)+" fetch first " +
                                id_produktow_w_koszyku.get(i+2)+" rows only");
                        for(int j=0; j< wynik.length; j++)
                        {
                            connection.wprowadzDaneBezAlert("UPDATE produkt SET id_transakcji = " +
                                    id_transakcji+" WHERE id_produktu = "+wynik[j]);
                        }
                        break;
                    case "4":
                        wynik=connection.uzyskajDane("Select id_produktu from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_procesora = " +
                                id_produktow_w_koszyku.get(i+1)+" fetch first " +
                                id_produktow_w_koszyku.get(i+2)+" rows only");
                        for(int j=0; j< wynik.length; j++)
                        {
                            connection.wprowadzDaneBezAlert("UPDATE produkt SET id_transakcji = " +
                                    id_transakcji+" WHERE id_produktu = "+wynik[j]);
                        }
                        break;
                    case "5":
                        wynik=connection.uzyskajDane("Select id_produktu from produkt where id_transakcji is null and id_zamowienia is null and id_zestawu is null and id_dysku = " +
                                id_produktow_w_koszyku.get(i+1)+" fetch first " +
                                id_produktow_w_koszyku.get(i+2)+" rows only");
                        for(int j=0; j< wynik.length; j++)
                        {
                            connection.wprowadzDaneBezAlert("UPDATE produkt SET id_transakcji = " +
                                    id_transakcji+" WHERE id_produktu = "+wynik[j]);
                        }
                        break;
                }
            }
        }

        //Dodanie zakupionych produktow wchodzących w sklad zestawow do bazy danych
        if(!id_zestawow_w_koszyku.isEmpty()) {
            for (int i = 0; i < id_zestawow_w_koszyku.size(); i = i + 2) {

                for (int j = 0; j < Integer.parseInt(dane.getIdZestawowWKoszyku().get(i + 1)); j++) {
                    id_zestawu = connection.uzyskajDane("SELECT id_zestawu FROM produkt " +
                            "WHERE id_typu_zestawu = " + dane.getIdZestawowWKoszyku().get(i)+
                            "AND id_transakcji IS NULL " +
                            "AND id_zamowienia IS NULL " +
                            "FETCH FIRST 1 ROW ONLY");

                    connection.wprowadzDaneBezAlert("UPDATE produkt " +
                            "SET id_transakcji = " +id_transakcji+
                            " WHERE id_zestawu = " +id_zestawu+
                            " AND id_pamieci_ram IS NOT NULL;");
                    connection.wprowadzDaneBezAlert("UPDATE produkt " +
                            "SET id_transakcji = " +id_transakcji+
                            " WHERE id_zestawu = " +id_zestawu+
                            " AND id_plyty_glownej IS NOT NULL;");
                    connection.wprowadzDaneBezAlert("UPDATE produkt " +
                            "SET id_transakcji = " +id_transakcji+
                            " WHERE id_zestawu = " +id_zestawu+
                            " AND id_karty_graficznej IS NOT NULL;");
                    connection.wprowadzDaneBezAlert("UPDATE produkt " +
                            "SET id_transakcji = " +id_transakcji+
                            " WHERE id_zestawu = " +id_zestawu+
                            " AND id_procesora IS NOT NULL;");
                    connection.wprowadzDaneBezAlert("UPDATE produkt " +
                            "SET id_transakcji = " +id_transakcji+
                            " WHERE id_zestawu = " +id_zestawu+
                            " AND id_dysku IS NOT NULL;");
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transakcja została zrealizowana!");
        alert.setContentText("Dziękujemy za zakupy. ");
        alert.showAndWait();
        dane.getIdProduktowWKoszyku().clear();
        dane.getIdZestawowWKoszyku().clear();
    }

    void finalizujZamowienie() throws IOException {
        String id_zamowienia[];
        String id_zestawu[];
        //pobranie i zedytowanie aktualnej daty
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatowanie = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDate = date.format(formatowanie);

        //Dodanie zamowienia do bazy danych
        connection.wprowadzDaneBezAlert("INSERT INTO zamowienie (id_zamowienia, id_uzytkownika, data_zlozenia, status_odbioru, cena_calkowita)" +
                "VALUES ((SELECT MAX(id_zamowienia) + 1 FROM zamowienie)," +
                idZalogowanegoUzytkownika+", '" +
                formattedDate+"', " +
                "'oczekujace', " +
                cenaFinalna+")");
        id_zamowienia=connection.uzyskajDane("Select max(id_zamowienia) from zamowienie");


        //Dodanie zamowionych produktow do bazy danych
        if(!id_produktow_w_koszyku.isEmpty()) {
            for (int i = 0; i < id_produktow_w_koszyku.size(); i = i + 3) {
                switch (id_produktow_w_koszyku.get(i)) {
                    case "1":
                        for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++) {
                            connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_pamieci_ram) " +
                                    "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                                    id_zamowienia[0] + ", " +
                                    id_produktow_w_koszyku.get(i + 1) + ")");
                        }
                        break;
                    case "2":
                        for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++) {
                            connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_plyty_glownej) " +
                                    "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                                    id_zamowienia[0] + ", " +
                                    id_produktow_w_koszyku.get(i + 1) + ")");
                        }
                        break;
                    case "3":
                        for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++) {
                            connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_karty_graficznej) " +
                                    "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                                    id_zamowienia[0] + ", " +
                                    id_produktow_w_koszyku.get(i + 1) + ")");
                        }
                        break;
                    case "4":
                        for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++) {
                            connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_procesora) " +
                                    "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                                    id_zamowienia[0] + ", " +
                                    id_produktow_w_koszyku.get(i + 1) + ")");
                        }
                        break;
                    case "5":
                        for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++) {
                            connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_dysku) " +
                                    "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                                    id_zamowienia[0] + ", " +
                                    id_produktow_w_koszyku.get(i + 1) + ")");
                        }
                        break;
                }
            }
        }

        //Dodanie zamowionych produktow wchodzących w sklad zestawow do bazy danych
        if(!id_zestawow_w_koszyku.isEmpty()) {
            for (int i = 0; i < id_zestawow_w_koszyku.size(); i = i + 2) {
                String wynik2[] = connection.uzyskajDane("Select id_pamiec_ram, id_plyta_glowna, id_karta_graficzna, id_procesor, id_dysk from zestaw where id_zestawu = " + id_zestawow_w_koszyku.get(i));

                for (int j = 0; j < Integer.parseInt(dane.getIdZestawowWKoszyku().get(i + 1)); j++) {
                    id_zestawu = connection.uzyskajDane("Select max(id_zestawu)+1 from produkt");

                    connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_pamieci_ram, id_zestawu, id_typu_zestawu) " +
                            "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                            id_zamowienia + ", " +
                            wynik2[0] + ", " +
                            id_zestawu + ", " +
                            dane.getIdProduktowWKoszyku().get(i) + ")");
                    connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_plyty_glownej, id_zestawu, id_typu_zestawu) " +
                            "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                            id_zamowienia + ", " +
                            wynik2[1] + ", " +
                            id_zestawu + ", " +
                            dane.getIdProduktowWKoszyku().get(i) + ")");
                    connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_karty_graficznej, id_zestawu, id_typu_zestawu) " +
                            "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                            id_zamowienia + ", " +
                            wynik2[2] + ", " +
                            id_zestawu + ", " +
                            dane.getIdProduktowWKoszyku().get(i) + ")");
                    connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_procesora, id_zestawu, id_typu_zestawu) " +
                            "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                            id_zamowienia + ", " +
                            wynik2[3] + ", " +
                            id_zestawu + ", " +
                            dane.getIdProduktowWKoszyku().get(i) + ")");
                    connection.wprowadzDaneBezAlert("INSERT INTO produkt (id_produktu, id_zamowienia, id_dysku, id_zestawu, id_typu_zestawu) " +
                            "VALUES ((SELECT MAX(id_produktu) + 1 FROM produkt), " +
                            id_zamowienia + ", " +
                            wynik2[4] + ", " +
                            id_zestawu + ", " +
                            dane.getIdProduktowWKoszyku().get(i) + ")");
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zamówienie zostało złożone!");
        alert.setContentText("Dziękujemy za zakupy. ");
        alert.showAndWait();
        dane.getIdProduktowWKoszyku().clear();
        dane.getIdZestawowWKoszyku().clear();
    }

    @FXML
    void order(MouseEvent event) {
        if(idZalogowanegoUzytkownika.equals("0"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Uwaga!");
            alert.setContentText("Funkcja zamawiania jest dostępna tylko i wyłącznie dla zalogowanych klientów.\nProsimy się zalogować. ");
            alert.showAndWait();
            return;
        }
        else
        {
            button_oproznij.setVisible(false);
            button_zamow.setVisible(false);
            button_kup.setVisible(false);
            produkty.setVisible(false);
            rabat.setVisible(true);
        }
    }

    @FXML
    void buy(MouseEvent event) {
        //TODO
    }

    @FXML
    void goscDalej(MouseEvent event) {
        //TODO
    }

    @FXML
    void rabatDalej(MouseEvent event) {
        //TODO
    }

    @FXML
    void clear(MouseEvent event) throws IOException {
        dane.getIdProduktowWKoszyku().clear();
        dane.getIdZestawowWKoszyku().clear();
        root = FXMLLoader.load(getClass().getResource("koszyk" + ".fxml"));
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
    void goToUzytkownik(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("uzytkownik" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void wyloguj(MouseEvent event) throws IOException {
        dane.setIdZalogowanegoUzytkownika("0");
        root = FXMLLoader.load(getClass().getResource("koszyk" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zaloguj(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
