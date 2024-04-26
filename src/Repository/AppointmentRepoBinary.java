package Repository;
import Domain.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AppointmentRepoBinary extends FileRepository<Appointment,Integer> {

    public AppointmentRepoBinary(String filename){
        super(filename);
    }

    @Override
    protected void readfromFile() {

    }


    protected void readFromFIle(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            this.MemElements = (ArrayList<Appointment>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(this.MemElements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


