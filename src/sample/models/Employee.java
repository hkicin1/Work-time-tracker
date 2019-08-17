package sample.models;

public class Employee extends Person {
    private int employeeId;
    private Person person;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Employee(int id, String name, String surname, String address, Integer postalNumber, String city, String userName, int employeeId, Person person) {
        super(id, name, surname, address, postalNumber, city, userName);
        this.employeeId = employeeId;
        this.person = person;
    }

    public Employee(int id, String name, String surname, String address, Integer postalNumber, String city, String userName) {
        super(id, name, surname, address, postalNumber, city, userName);
    }
}
