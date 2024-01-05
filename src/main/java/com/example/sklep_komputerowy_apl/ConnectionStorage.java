package com.example.sklep_komputerowy_apl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectionStorage {

    //podłączenie do klas z danymi
    DataStorage dane=DataStorage.getInstance();
    private static  final ConnectionStorage instance=new ConnectionStorage();

    //zmienna przechowująca połączenie do serwera zarządzającym BD
    private int port;
    private Socket socket;
    private InputStreamReader isr;
    private BufferedReader br;
    private PrintWriter pw;

    //Elementy do zmian scen
    private Stage stage;
    private Scene scene;
    private Parent root;


    private ConnectionStorage(){
        try{
            port=6700;
            socket=new Socket("127.0.0.1",port);
            isr=new InputStreamReader(socket.getInputStream());
            br=new BufferedReader(isr);
            pw= new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static ConnectionStorage getInstance(){
        return instance;
    }

    //funkcja do uzyskiwania danych od BD
    public String[] uzyskajDane(String zapytanie){

        String[] wynik={};

        try{
            //wysłanie zapytania do serwera
            pw.println(zapytanie);
            //odczytanie danych od serwera
            wynik= br.readLine().split(";");

            return wynik;
        }catch(SocketException s){

            try {
                if(dane.getCzyLogowanie()==0){
                    errorAlert("Utracono połączenie z serwerem");
                    goLogowanie();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        return wynik;

    }

    //funkcja do wprowadzenia/zmiany danych w BD
    public String wprowadzDane(String zapytanie){

        pw.println(zapytanie);
        try{
            String s=br.readLine();
            if(s.equals("Wprowadzenie/modyfikacja danych nieudana")){

                //utworzenie alertu typu error do wyświetlenia
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(s);
                alert.showAndWait();
                return "0";
            }else{

                //utworzenie alertu typu information do wyświetlenia
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setContentText(s);
                alert.showAndWait();
                return "1";
            }
        }catch(SocketException s){
            errorAlert("Utracono połączenie z serwerem");
            try {
                if(dane.getCzyLogowanie()==0){
                    goLogowanie();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }catch(IOException i){
            i.printStackTrace();
        }

        return "0";
    }

    //funkcja do wprowadzenia/zmiany danych w BD bez alertów
    public String wprowadzDaneBezAlert(String zapytanie){

        pw.println(zapytanie);
        try{
            String s=br.readLine();
            if(s.equals("Wprowadzenie/modyfikacja danych nieudana")){

                return "0";
            }else{

                return "1";
            }
        }catch(SocketException s){
            errorAlert("Utracono połączenie z serwerem");
            try {
                if(dane.getCzyLogowanie()==0){
                    goLogowanie();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }catch(IOException i){
            i.printStackTrace();
        }

        return "0";
    }

    //wyświetlanie alertów
    public void errorAlert(String m){
        //utworzenie alertu typu information do wyświetlenia
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(m);
        alert.showAndWait();
    }

    //funkcja do prześcia na strone logowania
    public void goLogowanie() throws IOException{
        System.out.println("lo");
        root = FXMLLoader.load(getClass().getResource("logowanie" + ".fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InputStreamReader getIsr() {
        return isr;
    }

    public void setIsr(InputStreamReader isr) {
        this.isr = isr;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
