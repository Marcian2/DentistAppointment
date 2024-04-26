package Main;

import GUI.AppointmentGUIController;
import Repository.AppointmentRepo;
import Repository.PatientRepo;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file and set the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("your_fxml_file.fxml"));
            Service service = new Service(new PatientRepo(), new AppointmentRepo()); // Create your service instance
            loader.setController(new AppointmentGUIController(service));

            // Load the FXML file
            Parent root = loader.load();

            // Set up the primary stage
            primaryStage.setTitle("Your JavaFX Application");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}