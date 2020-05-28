package PeopleManagment;

public class Employee {
    private String firstName;
    private String surname;
    private String birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Employee(String firstName, String surname, String birthDate) {
        super();
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}
