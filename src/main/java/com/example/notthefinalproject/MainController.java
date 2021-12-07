package com.example.notthefinalproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {



    Competition selectedComp = null;

    @FXML
    Button addComp;

    @FXML
    ListView<Competition> compList;


    @FXML
    ListView<Partecipant> partList;

    @FXML
    Label parLabel;

    @FXML
    Button editComp;


    @FXML
    Button editPar;

    @FXML
    Button addPar;
    @FXML
    ListView<Student> memberList;

    @FXML
    Button memberAdd;

    @FXML
    Button memberEdit;

    @FXML
    Label mamberLabel;
    @FXML
    protected void editPar() throws IOException {

        if(compList.getFocusModel().getFocusedItem().single){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-par-single.fxml"));
            EditParSingleController controller = new EditParSingleController(partList.getFocusModel().getFocusedItem());
            fxmlLoader.setController(controller);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("CompTracker");
            stage.setScene(scene);
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-par-team.fxml"));
            EditTeamController controller = new EditTeamController(partList.getFocusModel().getFocusedItem());
            fxmlLoader.setController(controller);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("CompTracker");
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    protected void addPar(){

    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    @FXML
    protected void editMemeber() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-member.fxml"));
        EditMemberController controller = new EditMemberController(partList.getFocusModel().getFocusedItem(), memberList.getFocusModel().getFocusedItem());
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void addComp() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-comp.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    protected void editComp() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-comp.fxml"));
        EditCompController controller = new EditCompController(compList.getFocusModel().getFocusedItem());
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CompTracker");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                refreshList();
            }
        });


    }






    public void  refreshList(){
        this.compList.getItems().setAll(FileReader.competitions);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberAdd.setOpacity(0);
        memberList.setOpacity(0);
        memberEdit.setOpacity(0);
        mamberLabel.setOpacity(0);

        addPar.setOpacity(0);
        editPar.setOpacity(0);
        welcomeText.setOpacity(0);
        parLabel.setOpacity(0);
        partList.setOpacity(0);
        partList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Partecipant>() {
            @Override
            public void changed(ObservableValue<? extends Partecipant> observable, Partecipant oldValue, Partecipant newValue) {
                if(!(compList.getFocusModel().getFocusedItem().single)){
                    System.out.println(compList.getFocusModel().getFocusedItem().compName);
                    memberList.getItems().setAll(((TeamPartecipant) newValue).teamMumbers);
                    memberList.setOpacity(1);
                    memberEdit.setOpacity(1);
                    memberAdd.setOpacity(1);
                    mamberLabel.setOpacity(1);
                }

            }
        });
        compList.getItems().addAll(FileReader.competitions);
        compList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Competition>() {
            @Override
            public void changed(ObservableValue<? extends Competition> observable, Competition oldValue, Competition newValue) {
                selectedComp = newValue;
                for(Partecipant p : newValue.partecipants){

                    System.out.println(p);
                }

                memberList.setOpacity(0);
                memberAdd.setOpacity(0);
                memberEdit.setOpacity(0);
                mamberLabel.setOpacity(0);
                welcomeText.setText(newValue.single ? "The competition is single participate.": "The competition is team based.");
                welcomeText.setOpacity(1);
                parLabel.setOpacity(1);
                partList.setOpacity(1);
                addPar.setOpacity(1);
                editPar.setOpacity(1);
                partList.getItems().setAll(newValue.partecipants);

            }
        });
    }


}