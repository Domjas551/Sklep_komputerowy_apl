package exceptions;

public class ConnectionException extends Exception {

    public ConnectionException(){
    }

    public String toString(){
        return "!!BRAK POŁĄCZENIA Z SERWEREM!!";
    }

}
