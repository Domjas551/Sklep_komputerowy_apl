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
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerAdminUzytkownik implements Initializable {
    ConnectionStorage connection=ConnectionStorage.getInstance();
    DataStorage dane=DataStorage.getInstance();

    //Wartosci przyjete roboczo
    private String idWybranegoUzytkownika=dane.getIdWybranegoUzytkownika();

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Elementy
    //Elementy poza zakladkami
    @FXML
    private Button button_anuluj_usuniecie;

    @FXML
    private Button button_back_to_admin;

    @FXML
    private Button button_show_zmiana_hasla;

    @FXML
    private Text button_home;

    @FXML
    private Button button_panel_admina;

    @FXML
    private Button button_wroc_do_menu;

    @FXML
    private Button button_zatwierdz_usuniecie;

    @FXML
    private Button button_zmien_haslo;

    @FXML
    private CheckBox checkbox_nowe_haslo;

    @FXML
    private CheckBox checkbox_powtorz_haslo;

    @FXML
    private CheckBox checkbox_stare_haslo;

    @FXML
    private AnchorPane menu;

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

    @FXML
    private Button usun_konto;

    @FXML
    private Text value_of_email;

    @FXML
    private Text value_of_imie;

    @FXML
    private Text value_of_nazwisko;

    @FXML
    private AnchorPane zatwierdzenie_usuniecia;

    @FXML
    private AnchorPane zmiana_hasla;


    //-------------------------------------
    //Funkcje
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Value_of_name
        String wynik[] = connection.uzyskajDane("Select imie from Uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);
        value_of_imie.setText(wynik[0]);

        //Value_of_nazwisko
        wynik = connection.uzyskajDane("Select nazwisko from Uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);
        value_of_nazwisko.setText(wynik[0]);

        //Value_of_name
        wynik = connection.uzyskajDane("Select email from Uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);
        value_of_email.setText(wynik[0]);
    }

    @FXML
    void deleteAccount(MouseEvent event) throws IOException {
        connection.wprowadzDane("UPDATE Uzytkownik SET czy_aktywny = 0 WHERE id_uzytkownika = " + idWybranegoUzytkownika);

        root = FXMLLoader.load(getClass().getResource("admin" + ".fxml"));
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
    void goToAdmin(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin" + ".fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //funkcja wyświetlająca strone menu
    @FXML
    void showMenu(MouseEvent event) {
        menu.setVisible(true);
        zatwierdzenie_usuniecia.setVisible(false);
        zmiana_hasla.setVisible(false);
    }

    //funkcja wyświetlająca strone zatwierdzenia usuniecia konta
    @FXML
    void showZatwierdzenieUsuniecia(MouseEvent event) {
        menu.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(true);
        zmiana_hasla.setVisible(false);
    }

    @FXML
    void showZmianaHasla(MouseEvent event) {
        menu.setVisible(false);
        zatwierdzenie_usuniecia.setVisible(false);
        zmiana_hasla.setVisible(true);

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

    @FXML
    void zmienHaslo(MouseEvent event) {
        String wynik[] = connection.uzyskajDane("Select haslo from uzytkownik where id_uzytkownika = " + idWybranegoUzytkownika);

        if(checkbox_stare_haslo.isSelected())
        {
            passwordField_stare_haslo.setText(textField_stare_haslo.getText());
        }
        else {
            textField_stare_haslo.setText(passwordField_stare_haslo.getText());
        }
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
                        connection.wprowadzDane("UPDATE Uzytkownik SET haslo = '" + textField_nowe_haslo.getText()+"' WHERE id_uzytkownika = " + idWybranegoUzytkownika);
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
