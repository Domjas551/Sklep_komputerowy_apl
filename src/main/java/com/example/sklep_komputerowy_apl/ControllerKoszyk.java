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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControllerKoszyk implements Initializable {
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();
    int kategoria=-1;

    //Wartosci przyjete roboczo
    private ArrayList<String> id_produktow_w_koszyku= new ArrayList<>(Arrays.asList("32", "102", "459"));
    private String idZalogowanegoUzytkownika="43";


    //Elementy
    @FXML
    private AnchorPane background;

    @FXML
    private Button button_anuluj;

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
            //ustawienie tabel
            //OpinieUzytkownika
            column_koszyk_nazwa_produktu.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, String>("nazwa_produktu"));
            column_koszyk_typ.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, String>("typ"));
            column_koszyk_producent.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, String>("producent"));
            column_koszyk_cena.setCellValueFactory(new PropertyValueFactory<TableProduktWKoszyku, Double>("cena"));

            //wypełnienie tabel
            //utworzenie list do wypełniania odpowiednich typów
            ObservableList<TableProduktWKoszyku> tpk_list = FXCollections.observableArrayList();

            for (int i = 0; i <= id_produktow_w_koszyku.size(); i++) {
                //Ustalenie kategorii wybranego produkt
                String wynik[] = connection.uzyskajDane("Select id_pamieci_ram, id_plyty_glownej, id_karty_graficznej, id_procesora, id_dysku from produkt where id_produktu = " + id_produktow_w_koszyku.get(i));
                for (int j = 0; j < wynik.length; j++) {
                    if (!wynik[j].equals("null")) {
                        kategoria = i;
                        break;
                    }
                }

                switch(kategoria) {
                    case 0:
                        tpk_list.add(new TableProduktWKoszyku(null, "Pamięć RAM", null, null));
                        break;
                    case 1:
                        tpk_list.add(new TableProduktWKoszyku(null, "Płyta główna", null, null));
                        break;
                    case 2:
                        tpk_list.add(new TableProduktWKoszyku(null, "Karta graficzna", null, null));
                        break;
                    case 3:
                        tpk_list.add(new TableProduktWKoszyku(null, "Procesor", null, null));
                        break;
                    case 4:
                        tpk_list.add(new TableProduktWKoszyku(null, "Dysk", null, null));
                        break;
                }

                wynik = connection.uzyskajDane("" + id_produktow_w_koszyku.get(i));
                    for (int j = 0; j < wynik.length; j += 3) {
                        tpk_list.get(i).setNazwa_produktu(wynik[j]);
                        tpk_list.get(i).setProducent(wynik[j+1]);
                        tpk_list.get(i).setCena(Double.parseDouble(wynik[j+2]));
                    }
                    table_koszyk.setItems(tpk_list);
            }
        }
    }

    @FXML
    void buy(MouseEvent event) {

    }

    @FXML
    void cancel(MouseEvent event) {

    }

    @FXML
    void goHome(MouseEvent event) {

    }

    @FXML
    void goToUzytkownik(MouseEvent event) {

    }

    @FXML
    void order(MouseEvent event) {

    }

    @FXML
    void wyloguj(MouseEvent event) {

    }

    @FXML
    void zaloguj(MouseEvent event) {

    }

}
