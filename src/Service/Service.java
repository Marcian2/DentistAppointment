package Service;
import Domain.Appointment;
import Domain.Patients;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;
import Repository.AppointmentRepo;
import Repository.PatientRepo;

public class Service {
    private final PatientRepo patientsrepository;
    private final AppointmentRepo appointmentrepository;

    public Service(PatientRepo patientsrepo, AppointmentRepo appointmentrepo){
        this.appointmentrepository=appointmentrepo;
        this.patientsrepository=patientsrepo;
    }

    //PATIENTS
    public void addPatient(String name, String age, int id, int phone) throws DuplicateItemException{
        this.patientsrepository.add(new Patients(name, age, id, phone));
    }

    public Patients getPatientById(int id) throws ItemNotFound{
        return patientsrepository.getItemById(id);
    }

    public void deletePatientById(int id) throws ItemNotFound{
        this.patientsrepository.deleteById(id);
    }

    public void updatePatientById(String newname, String age, int id, int newphone) throws ItemNotFound{
        Patients patient= patientsrepository.getItemById(id);
        patient.setName(newname);
        patient.setAge(age);
        patient.setPhone(newphone);
        patient.setId(id);
        this.patientsrepository.updateById(id, patient);
    }

    public Iterable<Patients> getAllPatients(){
        return this.patientsrepository.getAll();
    }


    //APPOINTMENTS
    public void addAppointment(int appointmentNumber, Patients patient, String dateOfAppointment) throws DuplicateItemException
    {
        this.appointmentrepository.add(new Appointment(appointmentNumber, patient, dateOfAppointment));
    }

    public Appointment getAppointmentById(int id) throws ItemNotFound{
        return appointmentrepository.getItemById(id);
    }

    public void deleteAppointmentById(int id)throws ItemNotFound{
        this.appointmentrepository.deleteById(id);
    }

    public void updateAppointmentById(int appointmentNumber, String dateOfAppointment) throws ItemNotFound{
        Appointment appointment=appointmentrepository.getItemById(appointmentNumber);
        appointment.setDateAppointment(dateOfAppointment);
    }

    public Iterable<Appointment> getAllAppointments(){
        return this.appointmentrepository.getAll();
    }
}
