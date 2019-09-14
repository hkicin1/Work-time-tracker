package sample.models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String address;
    private Integer postalNumber;
    private String city;
    private Position position;
    private String userName;
    private String password;
    private int isAdmin;

    public User(int id, String name, String surname, String address, Integer postalNumber, String city, Position position, String userName, String password, int isAdmin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalNumber = postalNumber;
        this.city = city;
        this.position = position;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public User(String name, String surname, String address, Integer postalNumber, String city, Position position, String userName, String password, int isAdmin) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalNumber = postalNumber;
        this.city = city;
        this.position = position;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalNumber() {
        return postalNumber;
    }

    public void setPostalNumber(Integer postalNumber) {
        this.postalNumber = postalNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " + name + " " + surname + "\n";
    }
}
