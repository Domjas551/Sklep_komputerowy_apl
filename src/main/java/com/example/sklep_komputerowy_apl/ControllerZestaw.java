package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerZestaw implements Initializable {

    //utworzenie połączenia do data oraz connection storage
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    private String idWybranegoZestawu=dane.getIdWybranegoZestawu();
    private String idZalogowanegoUzytkownika=dane.getIdZalogowanegoUzytkownika();

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane background;

    @FXML
    private TextField textField_liczba_egzemplarzy;

    @FXML
    private Button button_wroc;

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

    public void errorAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(m);
        alert.showAndWait();
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
            alert.showAndWait();
            return;
        }

        for(int i=0; i<dane.getIdZestawowWKoszyku().size();i=i+2)
        {
            if(dane.getIdZestawowWKoszyku().get(i).equals(idWybranegoZestawu)) {
                pom = Integer.parseInt(dane.getIdZestawowWKoszyku().get(i + 1));
                pom = pom + Integer.parseInt(textField_liczba_egzemplarzy.getText());
                dane.getIdZestawowWKoszyku().set(i + 1, String.valueOf(pom));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setContentText("Dodano do koszyka!");
                alert.showAndWait();
                return;
            }
        }

        dane.getIdZestawowWKoszyku().add(idWybranegoZestawu);
        dane.getIdZestawowWKoszyku().add(textField_liczba_egzemplarzy.getText());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setContentText("Dodano Zestaw do koszyka!");
        alert.showAndWait();
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
    void wyloguj(MouseEvent event) throws IOException {
        dane.setIdZalogowanegoUzytkownika("0");
        root = FXMLLoader.load(getClass().getResource("zestaw" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zaloguj(MouseEvent event) throws IOException {
        dane.setDestynacjaPowrotuZeStronyLogowania("zestaw");
        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
