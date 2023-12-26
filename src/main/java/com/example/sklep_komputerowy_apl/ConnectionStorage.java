package com.example.sklep_komputerowy_apl;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectionStorage {
    private static  final ConnectionStorage instance=new ConnectionStorage();

    //zmienna przechowująca połączenie do serwera zarządzającym BD
    private int port;
    private Socket socket;
    private InputStreamReader isr;
    private BufferedReader br;
    private PrintWriter pw;

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
            //System.out.println("Utracono połączenie z serwerem");
            s.printStackTrace();
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
        }catch(IOException i){
            i.printStackTrace();
        }

        return "0";
    }
}
