package Domain;

import java.util.Objects;
import java.io.Serializable;

public class Appointment implements Identifiable<Integer>, Serializable {
    private Patients Patient;
    private int appointmentID;
    private String dateAppointment;

    public Appointment(int appointmentNumber, Patients patient, String dateOfTheAppointment) {
        this.appointmentID = appointmentNumber;
        this.Patient = patient;
        this.dateAppointment = dateOfTheAppointment;
    }

    public Appointment() {

    }

    @Override
    public Integer getId() {
        return appointmentID;
    }

    @Override
    public void setId(Integer appointmentNumber) {
        this.appointmentID = appointmentNumber;
    }
    public Patients getPatient() {
        return Patient;
    }

    public void setPatient(Patients patient) {
        Patient = patient;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(String dateAppointment) {
        this.dateAppointment = dateAppointment;
    }
    public String toString() {
        return "Appointment{" +
                "id='" + appointmentID +
                ", Patient=" + Patient +
                ", Date=" + dateAppointment +
                '}';
    }
}
