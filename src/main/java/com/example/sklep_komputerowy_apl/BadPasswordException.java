package sk;

class BadPasswordException extends Exception{

    private String message;

    public BadPasswordException(){}

    @Override
    public String toString(){
        return "Hasło musi posiadać od 6 do 20 znaków, w tym co najmniej jedną małą i duża literę, cyfrę, oraz znak specjalny.";
    }
}