package Repository;
import Domain.Patients;
import java.io.*;
import java.util.ArrayList;

public class PatientRepoBinary extends FileRepository<Patients,Integer> {

    public PatientRepoBinary(String filename) {
        super(filename);
    }

    @Override
    protected void readfromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            this.MemElements = (ArrayList<Patients>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(this.MemElements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
