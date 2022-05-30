package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bug;

public class Util {

    public static void configTableViewBug(TableView<Bug> tableViewBugs){
        tableViewBugs.getColumns().clear();

        TableColumn<Bug, String> titleColumn = new TableColumn<>("Name");
        TableColumn<Bug, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Bug, String> statusColumn = new TableColumn<>("Status");
        TableColumn<Bug, String> fileColumn = new TableColumn<>("File address");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("fileAddress"));
        tableViewBugs.getColumns().add(titleColumn);
        tableViewBugs.getColumns().add(descriptionColumn);
        tableViewBugs.getColumns().add(statusColumn);
        tableViewBugs.getColumns().add(fileColumn);
    }

    public static void showWarning(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
