package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Bug;
import model.utils.BugStatus;
import service.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TesterController {

    private Service service;

    ObservableList<Bug> bugsList = FXCollections.observableArrayList();


    @FXML
    TableView<Bug> tableViewBugs;


    @FXML
    Button buttonLogout;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldDescription;

    @FXML
    TextField textFieldFileAddress;


    @FXML
    public void initialize() {
        textFieldFileAddress.setEditable(false);
        Util.configTableViewBug(tableViewBugs);
    }

    @FXML
    public void handleBugUnsolved() {
        if (!tableViewBugs.getSelectionModel().isEmpty()) {
            var bug = tableViewBugs.getSelectionModel().getSelectedItem();
            if (Objects.equals(bug.getStatus(), BugStatus.SOLVED.name())) {
                bug.setStatus(BugStatus.UNSOLVED.name());
                var index = tableViewBugs.getSelectionModel().getSelectedIndex();
                tableViewBugs.getItems().set(index, bug);
                service.updateBug(bug);
            }
        }
        else{
            Util.showWarning("Update bug error!", "You must select a bug from the table!");
        }

    }



    @FXML
    public void handleAdd() {
        try{
            String name = textFieldName.getText();
            String description = textFieldDescription.getText();
            String status = BugStatus.UNSOLVED.name();
            String fileAddress = textFieldFileAddress.getText();
            Bug bug = new Bug(name, description, status, fileAddress);
            service.addBug(bug);
            tableViewBugs.getItems().add(bug);
        } catch (Exception exception){
            Util.showWarning("Add error!", exception.getMessage());
        }
    }


    @FXML
    public void handleFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(buttonLogout.getScene().getWindow());

        if (file != null){
            textFieldFileAddress.setText(file.toURI().toString());
        }
    }


    @FXML
    public void handleLogout() throws IOException {
        buttonLogout.getScene().getWindow().hide();

        FXMLLoader loginLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
        Parent root= loginLoader.load();

        LoginController loginController = loginLoader.getController();
        loginController.setService(service);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void showBugs(){
        bugsList.setAll(service.getAllBugs());
        tableViewBugs.getItems().setAll(bugsList);
    }



    public void setService(Service service) {
        this.service = service;
    }

}
