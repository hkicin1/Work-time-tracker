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

    public Employee(int id, String name, String surname, String address, String postalNumber, String city, String userName, String password, int employeeId, Person person) {
        super(id, name, surname, address, postalNumber, city, userName, password);
        this.employeeId = employeeId;
        this.person = person;
    }

    public Employee(int id, String name, String surname, String address, String postalNumber, String city, String userName, String password) {
        super(id, name, surname, address, postalNumber, city, userName, password);
    }
}
