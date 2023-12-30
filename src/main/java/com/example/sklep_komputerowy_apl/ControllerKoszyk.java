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

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;


    //Elementy
    @FXML
    private AnchorPane background;

    @FXML
    private Button button_oproznij;

    @FXML
    private Text button_home;

    @FXML
    private Button button_kup;

    @FXML
    private Button button_value_of_name;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zaloguj;

    @FXML
    private Button button_zamow;

    @FXML
    private TableColumn<TableProduktWKoszyku, Double> column_koszyk_cena;

    @FXML
    private TableColumn<TableProduktWKoszyku, String> column_koszyk_producent;

    @FXML
    private TableColumn<TableProduktWKoszyku, String> column_koszyk_typ;

    @FXML
    private TableColumn<TableProduktWKoszyku, String> column_koszyk_nazwa_produktu;

    @FXML
    private ImageView image_user;

    @FXML
    private AnchorPane produkty;

    @FXML
    private TableView<TableProduktWKoszyku> table_koszyk;

    @FXML
    private Text value_of_cena_calkowita;

    @FXML
    private AnchorPane wyloguj;

    @FXML
    private AnchorPane zaloguj;


    //-------------------------------------------------------------------
    //Funkcje

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(!id_produktow_w_koszyku.isEmpty()) {
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

            for (int i = 0; i < id_produktow_w_koszyku.size(); i=i+3) {
                switch (id_produktow_w_koszyku.get(i)) {
                    case "1":
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from pamiec_ram where id_pamieci_ram = "+id_produktow_w_koszyku.get(i+1));
                        break;
                    case "2":
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from plyta_glowna where id_plyty_glownej = "+id_produktow_w_koszyku.get(i+1));
                        break;
                    case "3":
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from karta_graficzna where id_karty_graficznej = "+id_produktow_w_koszyku.get(i+1));
                        break;
                    case "4":
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from procesor where id_procesora = "+id_produktow_w_koszyku.get(i+1));
                        break;
                    case "5":
                        wynik = connection.uzyskajDane("Select nazwa_produktu, producent, cena from dysk where id_dysku = "+id_produktow_w_koszyku.get(i+1));
                        break;
                    }
                for (int j = 0; j < Double.parseDouble(id_produktow_w_koszyku.get(i + 2)); j++)
                {
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

            for (int i = 0; i < id_zestawow_w_koszyku.size(); i=i+2)
            {
                String wynik2[] = connection.uzyskajDane("Select id_pamiec_ram, id_plyta_glowna, id_karta_graficzna, id_procesor, id_dysk from zestaw where id_zestawu = "+id_zestawow_w_koszyku.get(i));

                for(int j=0; j<Integer.parseInt(dane.getIdZestawowWKoszyku().get(i+1));j++) {
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

            table_koszyk.setItems(tpk_list);

            //value_of_cena_calkowita
            for (int j = 0; j < tpk_list.size(); j++) {
                sumCen=sumCen+tpk_list.get(j).getCena();
            }
            value_of_cena_calkowita.setText(decfor.format(sumCen)+ " zł");
        }

        String wynik[]=null;
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

    @FXML
    void order(MouseEvent event) {
        //TODO
    }

    @FXML
    void buy(MouseEvent event) {
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
