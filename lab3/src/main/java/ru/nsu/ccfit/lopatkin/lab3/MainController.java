package ru.nsu.ccfit.lopatkin.lab3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import ru.nsu.ccfit.lopatkin.lab3.services.GetLocationService;

public class MainController {

    private GetLocationService getLocationService;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox descriptionVBox;

    @FXML
    private VBox resultsVBox;

    @FXML
    private TextField userInput;

    @FXML
    private Label weatherLabel;

    @FXML
    void searchRequest(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            getLocationService.getLocation(userInput.getText());
        }
    }

    @FXML
    void initialize() {
        assert descriptionVBox != null : "fx:id=\"descriptionVBox\" was not injected: check your FXML file 'main-view.fxml'.";
        assert resultsVBox != null : "fx:id=\"resultsVBox\" was not injected: check your FXML file 'main-view.fxml'.";
        assert userInput != null : "fx:id=\"userInput\" was not injected: check your FXML file 'main-view.fxml'.";
        assert weatherLabel != null : "fx:id=\"weatherLabel\" was not injected: check your FXML file 'main-view.fxml'.";

        getLocationService = new GetLocationService(resultsVBox);

    }

}
