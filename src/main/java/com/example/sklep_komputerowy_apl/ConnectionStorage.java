package com.example.sklep_komputerowy_apl;

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
            //utworzenie socketu
            //Socket socket=new Socket("127.0.0.1",port);
            //reader do odczytania danych od serwera
            //InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            //BufferedReader br = new BufferedReader(isr);
            //writer do wysyłania danych do serwera
            //PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            //wysłanie zapytania do serwera
            pw.println(zapytanie);
            //odczytanie danych od serwera
            wynik= br.readLine().split(";");

            //zamknięcie writera i readera
            //pw.close();
            //br.close();
            //socket.close();

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
    public void wprowadzDane(String zapytanie){

        pw.println(zapytanie);

    }
}
