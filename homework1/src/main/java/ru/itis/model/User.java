package ru.itis.model;

public class User {
    String name;
    String surname;
    String passwordNumber;
    String age;
    String passportDate;

    public User() {
    }

    public User(String name, String surname, String passwordNumber, String age, String passportDate) {
        this.name = name;
        this.surname = surname;
        this.passwordNumber = passwordNumber;
        this.age = age;
        this.passportDate = passportDate;
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

    public String getPasswordNumber() {
        return passwordNumber;
    }

    public void setPasswordNumber(String passwordNumber) {
        this.passwordNumber = passwordNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(String passportDate) {
        this.passportDate = passportDate;
    }
}
