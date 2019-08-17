package sample.models;

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

    public Admin(int id, String name, String surname, String address, Integer postalNumber, String city, String userName, int adminId, Person person) {
        super(id, name, surname, address, postalNumber, city, userName);
        this.adminId = adminId;
        this.person = person;
    }

    public Admin(int adminId, Person person) {
        this.adminId = adminId;
        this.person = person;
    }

    public Admin(int id, String name, String surname, String address, Integer postalNumber, String city, String userName) {
        super(id, name, surname, address, postalNumber, city, userName);
    }
}
