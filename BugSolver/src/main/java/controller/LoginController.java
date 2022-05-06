package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Programmer;
import model.Tester;
import service.Service;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    private Service service;

    @FXML
    TextField textFieldUsername;

    @FXML
    TextField textFieldPassword;

    @FXML
    Button buttonConnect;

    @FXML
    void initialize()
    {
    }

    @FXML
    public void handleConnect(ActionEvent actionEvent) throws Exception {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        Tester tester = service.findTesterByUsername(username);
        Programmer programmer = service.findProgrammerByUsername(username);
        String error = "";
        if (tester != null)
        {
            if (!Objects.equals(tester.getPassword(), password))
                error = "Invalid password!";
            else {
                FXMLLoader testerView = new FXMLLoader(getClass().getClassLoader().getResource("fxml/testerView.fxml"));
                Parent root = testerView.load();

                Stage stage = new Stage();
                stage.setTitle("Application");
                stage.setScene(new Scene(root));
                stage.show();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
        }
        else if (programmer != null)
        {
            if (!Objects.equals(programmer.getPassword(), password))
                error = "Invalid password!";
            else {
                FXMLLoader programmerView = new FXMLLoader(getClass().getClassLoader().getResource("fxml/programmerView.fxml"));
                Parent root = programmerView.load();

                Stage stage = new Stage();
                stage.setTitle("Application");
                stage.setScene(new Scene(root));
                stage.show();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
        }
        else{
            error = "Invalid username!";
        }
        if (error.length()>0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Authentication failure");
            alert.setContentText(error);
            alert.showAndWait();
        }
    }

    public void setService(Service service) {
        this.service = service;
    }
}
