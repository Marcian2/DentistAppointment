import Domain.Appointment;
import Domain.Patients;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;
import Repository.*;
import Service.Service;
import Ui.Ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws NullPointerException,DuplicateItemException,ItemNotFound {
        GenericRepository< Appointment,Integer> repo=new AppointmentRepo();
        try (FileReader fr = new FileReader("Doctor.properties"))
        {
            Properties props = new Properties();
            props.load(fr);

            String repoType = props.getProperty("repository_type");
            String sourceName = props.getProperty("Appointment_repository");
            switch (repoType)
            {
                case "inmemory":
                    repo = new MemoryRepository<>();
                    break;
                case "textfile":
                    repo = new AppointmentRepoText(sourceName);
                    break;
                case "binaryfile":
                    repo = new AppointmentRepoBinary(sourceName);
                    break;
                case "database":
                    repo = new AppointmentDBRepository(sourceName);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Appointment app: repo.getAll())
            System.out.println(app);
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Appointment b = new Appointment(100,patient,"11-12-2023");
        try {
            repo.add(b);
        } catch (DuplicateItemException e) {
            throw new RuntimeException(e);
        }

        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);
        Ui ui = new Ui(service);
        ui.run();
}
}
