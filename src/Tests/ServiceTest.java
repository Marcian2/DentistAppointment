package Tests;
import Domain.Patients;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;
import Repository.AppointmentRepo;
import Repository.PatientRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Service.Service;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    @DisplayName("Testing adding a patient...")
    void testAddPatient() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Service service = new Service(patientRepo, appointmentRepo);
        try {
            service.addPatient("John Doe", "25", 1, 123456789);
            //assertEquals(patient, service.getPatientById(1));
        } catch (DuplicateItemException e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing deleting a patient...")
    void testDeletePatient() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);
        try {
            service.addPatient("John Doe", "25", 1, 123456789);
            service.deletePatientById(1);
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing updating a patient...")
    void testUpdatePatient() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);
        try {
            service.addPatient("John Doe", "25", 1, 123456789);

            service.updatePatientById("Jane Doe", "30", 1, 987654321);


            System.out.println("After Update - All Patients: " + service.getAllPatients());

        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }
    @Test
    @DisplayName("Testing adding an appointment...")
    void testAddAppointment() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);
        Patients patient = new Patients("John Doe", "25", 1, 123456789);

        try {
            service.addAppointment(101, patient, "2023-12-10");
            assertEquals("2023-12-10", service.getAppointmentById(101).getDateAppointment());
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing deleting an appointment...")
    void testDeleteAppointment() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);
        Patients patient = new Patients("John Doe", "25", 1, 123456789);

        try {
            service.addAppointment(101, patient, "2023-12-10");
            assertTrue(service.getAllAppointments().iterator().hasNext()); // Ensure there is an appointment before deletion
            service.deleteAppointmentById(101);
            assertFalse(service.getAllAppointments().iterator().hasNext()); // Ensure there are no appointments after deletion
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing updating an appointment...")
    void testUpdateAppointment() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);
        Patients patient = new Patients("John Doe", "25", 1, 123456789);

        try {
            service.addAppointment(101, patient, "2023-12-10");
            service.updateAppointmentById(101, "2023-12-11");
            assertEquals("2023-12-11", service.getAppointmentById(101).getDateAppointment());
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }
}

