package com.inventory.inventory.businesslayer.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
/**
 * @author TomaVasilev
 * @version 1.0
 * */
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate addedOn;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String phoneNumber, LocalDate addedOn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addedOn = addedOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }



    @Override
    public String toString() {
        return "Client{" +
                "id= " + id +
                "\n, firstName='" + firstName + '\'' +
                ",\n lastName='" + lastName + '\'' +
                ",\n email='" + email + '\'' +
                ",\n phoneNumber='" + phoneNumber + '\'' +
                ",\n addedOn=" + addedOn +
                '}';
    }
}
