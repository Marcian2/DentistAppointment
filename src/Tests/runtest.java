package Tests;
import org.junit.jupiter.api.Test;

public class runtest {
    @Test
    public void testRun(){
        PatientsTest patientsTest=new PatientsTest();
        ServiceTest serviceTest=new ServiceTest();
        MemoryRepoTest memoryRepoTest=new MemoryRepoTest();

        patientsTest.testAddAndGetAll();
        patientsTest.testDeletePatientAndAppointment();
        patientsTest.testUpdatePatientAndAppointment();

        serviceTest.testUpdateAppointment();
        serviceTest.testDeletePatient();
        serviceTest.testDeleteAppointment();
        serviceTest.testAddPatient();
        serviceTest.testAddAppointment();

        memoryRepoTest.testDeleteByIdAppointment();
        memoryRepoTest.testDeleteByIdP();
        memoryRepoTest.testAddItemP();
        memoryRepoTest.testAddAppointment();

    }
}
