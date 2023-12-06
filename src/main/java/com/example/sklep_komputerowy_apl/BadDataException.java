package sk;

class BadDataException extends Exception{

    private String message;
    private String field;

    public BadDataException(String field){
        this.field = field;
    }

    @Override
    public String toString(){
        return "Niepoprawne dane!";
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}