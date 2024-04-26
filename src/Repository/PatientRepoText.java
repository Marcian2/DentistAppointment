package Repository;
import Domain.Patients;
import java.io.*;

public class PatientRepoText extends FileRepository<Patients,Integer>{

    protected void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Patients d: getAll() ) {
                writer.write(d.getName() + "," +
                        d.getAge() + "," +
                        d.getId() +
                        d.getPhone()+
                        "." + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void readfromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine())!= null) {
                String[] stringArray = line.split(",");
                if (stringArray.length != 5) {
                    continue;
                } else {
                    Patients patient=new Patients();
                    this.MemElements.add(patient);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public PatientRepoText(String filename) throws IOException {
        super(filename);
    }

}
