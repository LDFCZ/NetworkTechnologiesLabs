module ru.nsu.ccfit.lopatkin.lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp;
    requires com.google.gson;


    opens ru.nsu.ccfit.lopatkin.lab3 to javafx.fxml;
    exports ru.nsu.ccfit.lopatkin.lab3;
}