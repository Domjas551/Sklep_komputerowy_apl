module com.example.sklep_komputerowy_apl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sklep_komputerowy_apl to javafx.fxml;
    exports com.example.sklep_komputerowy_apl;
}