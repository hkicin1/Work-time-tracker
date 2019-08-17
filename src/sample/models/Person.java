package sample.models;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String address;
    private Integer postalNumber;
    private String city;
    private String userName;

    public Person(int id, String name, String surname, String address, Integer postalNumber, String city, String userName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalNumber = postalNumber;
        this.city = city;
        this.userName = userName;
    }

    public Person() {
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

}
