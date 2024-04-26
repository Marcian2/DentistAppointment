package GUI;

import Domain.Patients;
import Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentGUIController implements  javafx.fxml.Initializable {
    @FXML
    private TableView<Patients> patientTableView;

    @FXML
    private TableColumn<Patients, String> nameColumn;

    private final Service service;

    public AppointmentGUIController(Service service) {
        this.service = service;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller (if needed)
        initializeTableView();
    }

    private void initializeTableView() {
        // Set up the TableView columns and data
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


    }

}