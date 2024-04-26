package Ui;
import Service.Service;
import Domain.*;
import Exceptions.*;
import java.util.Scanner;

public class Ui {
    private final Service service;

    public Ui(Service service){
        this.service=service;
    }
    public void AllPatients()
    {
        Iterable<Patients> list=this.service.getAllPatients();
        System.out.println("List of Patients:");
        for(Patients patient: list)
        {
            System.out.println(patient);
        }
    }

    public void AllAppointments()
    {
        Iterable<Appointment> appointments=this.service.getAllAppointments();
        System.out.println("List of Appointments:");
        for(Appointment appointment: appointments)
        {
            System.out.println(appointment);
        }
    }

    //String name, String age, int id, int phone
    public void addPatient()  {
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name= scanner.nextLine();
        System.out.print("Enter Age: ");
        String age= scanner.nextLine();

        int id;
        while(true){
            System.out.print("Enter Id: ");
            if(scanner.hasNextInt()){
                id=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid id value");
                scanner.nextLine();
            }
        }

        int phone;
        while(true){
            System.out.print("Enter phone number : ");
            if(scanner.hasNextInt()) {
                phone = scanner.nextInt();
                if (String.valueOf(phone).length() == 9) {
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Enter a valid phone number of 9 digits");
                    scanner.nextLine();
                }
            }
        }
        try{
            service.addPatient(name,age,id,phone);
            System.out.println("Patient added");
        }catch(DuplicateItemException error){
            System.out.print("A patient with the id "+ id + " exists ");
        }

    }

    //int appointmentNumber, Patients patient, LocalDate dateOfTheAppointment
    public void addAppointment() throws ItemNotFound{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Create a new appointment");
        int appnr;
        while(true){
            System.out.println("Enter appointment number: ");
            if(scanner.hasNextInt()){
                appnr=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid appointment number value");
                scanner.nextLine();
            }
        }

        Patients patient = service.getPatientById(appnr);

        System.out.print("Enter a date(YYYY-MM-DD): ");
        String date=scanner.nextLine();
        try{
            service.addAppointment(appnr,patient,date);
            System.out.println("An appointment with the number "+appnr+" has been added.");
        }catch(DuplicateItemException error)
        {
            System.out.println("An appointment with the number "+appnr+" exists.");
        }

    }
    public void getPatientById()throws NullPointerException{
        Scanner scanner=new Scanner(System.in);
        int id;
        while(true){
            System.out.print("Enter id: ");
            if(scanner.hasNextInt()){
                id=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid id value");
                scanner.nextLine();
            }
        }
        try{
            Patients patient=service.getPatientById(id);
            System.out.println(patient.toString());
        }catch(ItemNotFound err){
            System.out.println("No patient found with id: "+ id +"\n");
        }
    }
    public void getAppointmentById() {
        Scanner scanner=new Scanner(System.in);
        int appnr;
        while(true){
            System.out.print("Enter appointment number: ");
            if(scanner.hasNextInt()){
                appnr=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid appointment number value");
                scanner.nextLine();
            }
        }
        try{
            Appointment appointment=service.getAppointmentById(appnr);
            System.out.print(appointment.toString());
        }catch(ItemNotFound err){
            System.out.println("No appointment found with appointment number: "+ appnr +"\n");
        }
    }

    public void deletePatientById() {
        Scanner scanner=new Scanner(System.in);
        int id;
        while(true){
            System.out.print("Enter id: ");
            if(scanner.hasNextInt()){
                id=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid id value");
                scanner.nextLine();
            }
        }
        try{
            service.deletePatientById(id);
            System.out.println("Patient with id: "+ id +" was deleted.");
        }catch(ItemNotFound err){
            System.out.println("No patient found with id: "+ id);
        }
    }

    public void deleteAppointmentById() {
        Scanner scanner=new Scanner(System.in);
        int appnr;
        while(true){
            System.out.print("Enter appointment number: ");
            if(scanner.hasNextInt()){
                appnr=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid appointment number value");
                scanner.nextLine();
            }
        }
        try{
            service.deleteAppointmentById(appnr);
            System.out.println("Appointment with number: "+ appnr +" was deleted.");
        }catch(ItemNotFound err){
            System.out.println("No appointment found with number: "+ appnr +"\n");
        }
    }

    public void updatePatientById() throws ItemNotFound{
        AllPatients();
        Scanner scanner=new Scanner(System.in);
        int id;
        while(true){
            System.out.print("Enter Id: ");
            if(scanner.hasNextInt()){
                id=scanner.nextInt();
                if(service.getPatientById(id) != null){
                    scanner.nextLine();
                    break;
                }else{
                    System.out.println("Enter a valid id value from the patients list");
                    scanner.nextLine();
                }
            }
        }
        System.out.print("Enter Name: ");
        String name= scanner.nextLine();
        System.out.print("Enter Age: ");
        String age= scanner.nextLine();
        int phone;
        while(true){
            System.out.print("Enter phone number: ");
            if(scanner.hasNextInt()) {
                phone = scanner.nextInt();
                if (String.valueOf(phone).length() == 10) {
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Enter a valid phone number of 10 digits");
                    scanner.nextLine();
                }
            }
        }
        try{
            service.updatePatientById(name,age,id,phone);
            System.out.println("Patient with id "+ id+ "has been updated");
        }catch(ItemNotFound err){
            System.out.println("Patient with id "+ id + " has not been found");
        }
    }

    public void updateAppointmentById(){
        Scanner scanner = new Scanner(System.in);
        int appnr;
        while(true){
            System.out.print("Enter appointment number: ");
            if(scanner.hasNextInt()){
                appnr=scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Enter a valid appointment number value");
                scanner.nextLine();
            }
        }
        System.out.print("Enter a date(YYYY.MM.DD): ");
        String date=scanner.nextLine();
        try{
            service.updateAppointmentById(appnr,date);
            System.out.println("Appointment with number: "+ appnr +" was updated.");
        }catch(ItemNotFound error){
            System.out.println("Appointment with number: "+ appnr +" was not found.");
        }

    }
    public void run() throws  ItemNotFound {
        printMenu();
        while (true){
            System.out.print("Command : \n");
            System.out.print("Input number ");
            Scanner scanner=new Scanner(System.in);
            int command= scanner.nextInt();
            switch(command){
                case 0:
                    return;
                case 1:
                    AllPatients();
                    break;
                case 11:
                    AllAppointments();
                    break;
                case 2:
                    addPatient();
                    break;
                case 12:
                    addAppointment();
                    break;
                case 3:
                    getPatientById();
                    break;
                case 13:
                    getAppointmentById();
                    break;
                case 4:
                    deletePatientById();
                    break;
                case 14:
                    deleteAppointmentById();
                    break;
                case 5:
                    updatePatientById();
                    break;
                case 15:
                    updateAppointmentById();
                    break;
            }

        }
    }

    private void printMenu() {
        System.out.println("0- exit");
        System.out.println("1- Get the patients list");
        System.out.println("11-Get the Appointments list");
        System.out.println("2- Add a patient");
        System.out.println("12-Add an appointment ");
        System.out.println("3- Get a patient");
        System.out.println("13-Get an appointment");
        System.out.println("4- Delete a patient ");
        System.out.println("14-Delete an appointment ");
        System.out.println("5- Update a patient ");
        System.out.println("15-Update an appointment ");
    }
}
