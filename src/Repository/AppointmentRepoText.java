package Repository;

import Domain.Appointment;

import java.io.*;

public class AppointmentRepoText extends FileRepository<Appointment,Integer> {

    public AppointmentRepoText(String filename) throws IOException {
        super(filename);
    }
    @Override
    protected void readfromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] stringArray = line.split(",");
                if (stringArray.length != 5) {
                    continue;
                } else {
                    Appointment app = new Appointment();
                    this.MemElements.add(app);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    protected void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Appointment d : getALlItems()) {
                writer.write(d.getAppointmentID() + "," +
                        d.getDateAppointment() + "," +
                        d.getPatient() +
                        "." + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}