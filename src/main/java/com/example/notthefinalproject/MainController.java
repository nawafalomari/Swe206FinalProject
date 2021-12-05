package com.example.notthefinalproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    ListView<Competition> compList;


    @FXML
    ListView<Partecipant> partList;

    @FXML
    Label parLabel;

    FileReader fr = new FileReader();
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeText.setOpacity(0);
        parLabel.setOpacity(0);
        partList.setOpacity(0);

        compList.getItems().addAll(fr.competitions);
        compList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Competition>() {
            @Override
            public void changed(ObservableValue<? extends Competition> observable, Competition oldValue, Competition newValue) {
                for(Partecipant p : newValue.partecipants){
                    System.out.println(p);
                }
                welcomeText.setText(newValue.single ? "The competition is single participate.": "The competition is team based.");
                welcomeText.setOpacity(1);
                parLabel.setOpacity(1);
                partList.setOpacity(1);
                partList.getItems().setAll(newValue.partecipants);

            }
        });
    }


}