package Tests;
import Repository.AppointmentRepo;
import Repository.PatientRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import Domain.*;
import Service.*;
import Exceptions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PatientsTest{
    @Test
    @DisplayName("Testing adding and getting all Patients and Appointments")
    void testAddAndGetAll() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);

        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Appointment appointment = new Appointment(101, patient, "2023-12-10");

        try {
            service.addPatient(patient.getName(), patient.getAge(), patient.getId(), patient.getPhone());
            service.addAppointment(appointment.getAppointmentID(), patient, appointment.getDateAppointment());

            Iterable<Patients> allPatients = service.getAllPatients();
            Iterable<Appointment> allAppointments = service.getAllAppointments();

            assertNotNull(service.getAllPatients());
            assertNotNull(service.getAllAppointments());


        } catch (DuplicateItemException e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }


    @Test
    @DisplayName("Testing deleting a Patient and its corresponding Appointment")
    void testDeletePatientAndAppointment() {
        PatientRepo patientRepo=new PatientRepo();
        AppointmentRepo appointmentRepo=new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);

        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Appointment appointment = new Appointment(101, patient, "2023-12-10");

        try {
            service.addPatient(patient.getName(), patient.getAge(), patient.getId(), patient.getPhone());
            service.addAppointment(appointment.getAppointmentID(), patient, appointment.getDateAppointment());
            service.deletePatientById(patient.getId());

            Iterable<Patients> allPatients = service.getAllPatients();
            Iterable<Appointment> allAppointments = service.getAllAppointments();

            assertFalse(containsItem(allPatients, patient));
            assertFalse(containsItem(allAppointments, appointment));

        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing updating a Patient and its corresponding Appointment")
    void testUpdatePatientAndAppointment() {
        PatientRepo patientRepo = new PatientRepo();
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        Service service = new Service(patientRepo, appointmentRepo);

        Patients originalPatient = new Patients("John Doe", "25", 1, 123456789);
        Appointment originalAppointment = new Appointment(101, originalPatient, "2023-12-10");

        try {
            service.addPatient(originalPatient.getName(), originalPatient.getAge(), originalPatient.getId(), originalPatient.getPhone());
            service.addAppointment(originalAppointment.getAppointmentID(), originalPatient, originalAppointment.getDateAppointment());

            System.out.println("Before Update - All Patients: " + service.getAllPatients());
            Patients updatedPatient = new Patients("Jane Doe", "30", 1, 987654321);
            service.updatePatientById(updatedPatient.getName(), updatedPatient.getAge(), updatedPatient.getId(), updatedPatient.getPhone());
            Appointment updatedAppointmentById= new Appointment(1,updatedPatient,"2023-12-10");
            service.updateAppointmentById(1,"2023-12-11");
            System.out.println("After Update - All Patients: " + service.getAllPatients());

            assertEquals(updatedPatient, service.getPatientById(1));
            assertEquals(updatedAppointmentById,service.getAppointmentById(1));

        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());
        }
    }
    private boolean containsItem(Iterable<?> items, Object targetItem) {
        for (Object item : items) {
            if (item.equals(targetItem)) {
                return true;
            }
        }
        return false;
    }
}
