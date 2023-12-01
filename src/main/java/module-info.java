module com.example.sklep_komputerowy_apl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sklep_komputerowy_apl to javafx.fxml;
    exports com.example.sklep_komputerowy_apl;
}