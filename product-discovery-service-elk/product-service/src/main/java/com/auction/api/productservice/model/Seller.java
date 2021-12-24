package com.auction.api.productservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Seller {

    @NotNull(message = "First Name cannot be null.")
    @Size(max = 30, min = 5, message = "First Name should have 5 to 30 characters")
    private String firstName;
    @NotNull(message = "Last Name cannot be null.")
    @Size(max = 25, min = 3, message = "Last Name should have 3 to 25 characters")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    @NotNull(message = "Mobile No. cannot be null.")
    @Size(max = 10, min = 10, message = "Mobile No. is limited to 10 Numbers")
    @Pattern(regexp = "[0-9]+", message = "Mobile NO. can contain only numbers.")
    private String phone;
    @NotNull(message = "Email Id cannot be null.")
    @Email(message = "Not a valid email address.")
    private String email;

    public Seller(String firstName, String lastName, String address, String city, String state, String pin, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.phone = phone;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pin='" + pin + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
