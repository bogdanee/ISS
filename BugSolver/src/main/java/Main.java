import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.orm.BugHbmRepository;
import repository.orm.ProgrammerHbmRepository;
import repository.orm.TesterHbmRepository;
import service.Service;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Service service = new Service(new BugHbmRepository(), new TesterHbmRepository(), new ProgrammerHbmRepository());

        FXMLLoader loginLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
        Parent root= loginLoader.load();

        LoginController loginController = loginLoader.getController();
        loginController.setService(service);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
