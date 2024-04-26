package Tests;
import Domain.Appointment;
import Exceptions.DuplicateItemException;
import Exceptions.ItemNotFound;
import Domain.Patients;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Repository.*;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryRepoTest {
    @Test
    @DisplayName("Testing adding items...")
    void testAddItemP() {
        MemoryRepository<Patients, Integer> memoryRepo = new MemoryRepository<>();
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        try {
            memoryRepo.add(patient);
            assertEquals(patient, memoryRepo.getItemById(1));
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());
        }
    }
    @Test
    @DisplayName("Testing deleting items...")
    void testDeleteByIdP() {
        MemoryRepository<Patients, Integer> memoryRepo = new MemoryRepository<>();
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        try {
            memoryRepo.add(patient);
            memoryRepo.deleteById(1);
            assertNull(memoryRepo.getItemById(1));
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing updating items...")
    void testUpdateByIdP() {
        MemoryRepository<Patients, Integer> memoryRepo = new MemoryRepository<>();
        Patients originalPatient = new Patients("John Doe", "25", 1, 123456789);

        try {
            memoryRepo.add(originalPatient);
            System.out.println("Before Update - All Patients: " + memoryRepo.getALlItems());
            Patients updatedPatient = new Patients("Jane Doe", "30", 1, 987654321);
            memoryRepo.updateById(1, updatedPatient);
            System.out.println("After Update - All Patients: " + memoryRepo.getALlItems());


            assertEquals(updatedPatient, memoryRepo.getItemById(1));

        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());
        }
    }
    @Test
    @DisplayName("Testing adding appointments...")
    void testAddAppointment() {
        MemoryRepository<Appointment, Integer> memoryRepo = new MemoryRepository<>();
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Appointment appointment = new Appointment(101, patient, "2023-12-10");
        try {
            memoryRepo.add(appointment);
            assertEquals(appointment, memoryRepo.getItemById(101));
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing deleting appointments...")
    void testDeleteByIdAppointment() {
        MemoryRepository<Appointment, Integer> memoryRepo = new MemoryRepository<>();
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Appointment appointment = new Appointment(101, patient, "2023-12-10");
        try {
            memoryRepo.add(appointment);
            assertTrue(memoryRepo.getAll().iterator().hasNext());
            memoryRepo.deleteById(101);
            assertFalse(memoryRepo.getAll().iterator().hasNext());
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }

    @Test
    @DisplayName("Testing updating appointments...")
    void testUpdateByIdAppointment() {
        MemoryRepository<Appointment, Integer> memoryRepo = new MemoryRepository<>();
        Patients patient = new Patients("John Doe", "25", 1, 123456789);
        Appointment originalAppointment = new Appointment(101, patient, "2023-12-10");
        try {
            memoryRepo.add(originalAppointment);
            Appointment updatedAppointment = new Appointment(101, patient, "2023-12-11");
            memoryRepo.updateById(101, updatedAppointment);
            assertEquals(updatedAppointment, memoryRepo.getItemById(101));
        } catch (DuplicateItemException | ItemNotFound e) {
            System.out.print("Should not throw exception: " + e.getMessage());        }
    }
}

