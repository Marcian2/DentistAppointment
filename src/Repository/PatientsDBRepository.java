package Repository;

import Domain.Patients;
import Exceptions.DuplicateItemException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatientsDBRepository extends DBRepository<Patients,Integer> {
    private MemoryRepository<Patients,Integer> data;

    public PatientsDBRepository(String tableName){
        super(tableName);
        getData();
    }

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
                    String name=resultSet.getString("Name");
                    String age= resultSet.getString("age");
                    int id = resultSet.getInt("id");
                    int phone=resultSet.getInt("phone");
                    Patients patient=new Patients(name,age,id,phone);
                    data.add(patient);
                }
            }
        } catch (SQLException | DuplicateItemException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Patients elem) throws DuplicateItemException
    {
        try
        {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?, ?, ?,?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setString(1, elem.getName());
                ps.setString(2, elem.getAge());
                ps.setInt(3, elem.getId());
                ps.setInt(4,elem.getPhone());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patients> getPatientsAbove18() {
        return getAllItemsStream()
                .filter(obj -> obj instanceof Patients)
                .map(obj -> (Patients) obj)
                .filter(patient -> Integer.parseInt(patient.getAge()) > 18)
                .collect(Collectors.toList());
    }

    public List<Patients> getPatientsBelow18() {
        return getAllItemsStream()
                .filter(obj -> obj instanceof Patients)
                .map(obj -> (Patients) obj)
                .filter(patient -> Integer.parseInt(patient.getAge()) < 18)
                .collect(Collectors.toList());
    }

    public List<Patients> filterPatientsAlphabetically() {
        return getAllItemsStream()
                .filter(obj -> obj instanceof Patients)
                .map(obj -> (Patients) obj)
                .sorted(Comparator.comparing(Patients::getName))
                .collect(Collectors.toList());
    }

    private Stream<Object> getAllItemsStream() {
        return null;
    }


}
