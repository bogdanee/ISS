package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Bug;
import model.utils.BugStatus;
import service.Service;

import java.io.IOException;
import java.util.Objects;


public class ProgrammerController {

    private Service service;

    ObservableList<Bug> bugsList = FXCollections.observableArrayList();


    @FXML
    TableView<Bug> tableViewBugs;


    @FXML
    Button buttonLogout;


    @FXML
    public void initialize() {
        Util.configTableViewBug(tableViewBugs);
    }

    @FXML
    public void handleBugSolved() {
        if (!tableViewBugs.getSelectionModel().isEmpty()) {
            var bug = tableViewBugs.getSelectionModel().getSelectedItem();
            if (Objects.equals(bug.getStatus(), BugStatus.UNSOLVED.name())) {
                bug.setStatus(BugStatus.SOLVED.name());
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
    public void handleDelete() {
        if (!tableViewBugs.getSelectionModel().isEmpty()) {
            var bug = tableViewBugs.getSelectionModel().getSelectedItem();
            tableViewBugs.getItems().remove(bug);
            service.deleteBug(bug);

        }
        else{
            Util.showWarning("Delete bug error!", "You must select a bug from the table!");
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
