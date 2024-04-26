package Domain;
import java.util.Objects;
import java.io.Serializable;
public class Patients implements Identifiable<Integer>,Serializable{
    private String name,age;
    private int id,phone;

    public Patients(String name, String age, int id, int phone) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.phone=phone;
    }

    public Patients() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id) {
        this.id= id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name +
                ", age=" + age +
                ", id=" + id +
                ", phone="+phone+
                '}';
    }

    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
            return true;
        if (objectToCompare.getClass() != Patients.class)
            return false;
        Patients patientToCompare = (Patients) objectToCompare;
        return Objects.equals(patientToCompare.id, this.id);
    }


}
