package sample.java;

public class Admin extends Person {
    private int adminId;
    private Person person;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Admin(int id, String name, String surname, String address, String postalNumber, String city, String userName, String password, int adminId, Person person) {
        super(id, name, surname, address, postalNumber, city, userName, password);
        this.adminId = adminId;
        this.person = person;
    }

    public Admin(int id, String name, String surname, String address, String postalNumber, String city, String userName, String password) {
        super(id, name, surname, address, postalNumber, city, userName, password);
    }
}
