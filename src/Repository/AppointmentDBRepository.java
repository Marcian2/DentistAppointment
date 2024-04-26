package Repository;

import Domain.Appointment;
import Domain.Patients;
import Exceptions.DuplicateItemException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AppointmentDBRepository extends DBRepository<Appointment,Integer> {

    private MemoryRepository<Appointment,Integer> data;

    public AppointmentDBRepository(String tableName) {
        super(tableName);
        getData();
    }

    @Override
    public void getData() {
        try
        {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString))
            {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next())
                {
                    int appointmentID = resultSet.getInt("id");

                    Patients patient = new Patients("John Doe", "25", 1, 123456789);
                   // String Patientt=resultSet.getString(patient.toString());
                    String dateAppointment = resultSet.getString("date");
                    Appointment app = new Appointment(appointmentID,patient,dateAppointment);
                    data.add(app);
                }
            }
        } catch (SQLException | DuplicateItemException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void add(Appointment elem) throws DuplicateItemException
    {
        try
        {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setInt(1, elem.getAppointmentID());
                ps.setString(2, String.valueOf(elem.getPatient()));
                ps.setString(3, elem.getDateAppointment());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appointment> getAppointmentsAbove18() {
        return getAllItemsStream()
                .filter(obj -> obj instanceof Appointment)
                .map(obj -> (Appointment) obj)
                .filter(appointment -> Integer.parseInt(appointment.getPatient().getAge()) > 18)
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsBelow18(){
        return getAllItemsStream()
                .filter(obj->obj instanceof Appointment)
                .map(obj->(Appointment) obj)
                .filter(appointment -> Integer.parseInt(appointment.getPatient().getAge())<18)
                .collect(Collectors.toList());
    }
    private Stream<Object> getAllItemsStream() {
        return null;
    }

}
