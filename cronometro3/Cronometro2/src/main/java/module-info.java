module PSP.Cronometro2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens PSP.Cronometro2 to javafx.fxml;
    exports PSP.Cronometro2;
}
