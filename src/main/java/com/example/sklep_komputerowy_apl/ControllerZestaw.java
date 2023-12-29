package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerZestaw implements Initializable {

    //utworzenie połączenia do data oraz connection storage
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    @FXML
    private AnchorPane background;

    @FXML
    private Button button_cart;

    @FXML
    private Button button_dodaj_do_koszyka;

    @FXML
    private Text button_home;

    @FXML
    private Button button_value_of_name;

    @FXML
    private Button button_wyloguj;

    @FXML
    private Button button_zaloguj;

    @FXML
    private ImageView image_cart;

    @FXML
    private ImageView image_user;

    @FXML
    private AnchorPane plyta_glowna;

    @FXML
    private Text value_of_cena;

    @FXML
    private Text value_of_dysk;

    @FXML
    private Text value_of_karta;

    @FXML
    private Text value_of_nazwa_zestawu;

    @FXML
    private Text value_of_pamiec;

    @FXML
    private Text value_of_plyta;

    @FXML
    private Text value_of_procesor;

    @FXML
    private AnchorPane wyloguj;

    @FXML
    private AnchorPane zaloguj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        //pobranie danych z BD
        String wynik[]=connection.uzyskajDane("Select nazwa_zestawu," +
        "cena, "+
        "(Select nazwa_produktu from plyta_glowna where id_plyty_glownej=z.id_plyta_glowna) as plyta, "+
        "(Select nazwa_produktu from procesor where id_procesora=z.id_procesor) as procesor, "+
        "(Select nazwa_produktu from karta_graficzna where id_karty_graficznej=z.id_karta_graficzna) as karta, "+
        "(Select nazwa_produktu from pamiec_ram where id_pamieci_ram=z.id_pamiec_ram) as pamiec, "+
        "(Select nazwa_produktu from dysk where id_dysku=z.id_dysk) as dysk "+
        "from zestaw z where id_zestawu="+dane.getIdWybranegoZestawu());

        if(wynik.length<1){
            errorAlert("Wystąpił błąd przy pobieraniu danych z serwera");
        }else{
            //wprowadzenie danych z BD w odpowiednie miejsca
            value_of_nazwa_zestawu.setText(wynik[0]);
            value_of_cena.setText(wynik[1]);
            value_of_plyta.setText(wynik[2]);
            value_of_procesor.setText(wynik[3]);
            value_of_karta.setText(wynik[4]);
            value_of_pamiec.setText(wynik[5]);
            value_of_dysk.setText(wynik[6]);
        }
    }

    public void errorAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(m);
        alert.showAndWait();
    }

    @FXML
    void addToCart(MouseEvent event) {

    }

    @FXML
    void goHome(MouseEvent event) {

    }

    @FXML
    void goToCart(MouseEvent event) {

    }

    @FXML
    void goToUzytkownik(MouseEvent event) {

    }

    @FXML
    void wyloguj(MouseEvent event) {

    }

    @FXML
    void zaloguj(MouseEvent event) {

    }

}
