package org.example.entities;

import com.github.javafaker.Faker;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String zip;
    private final String city;
    private final String shippingMethod;
    private final String payment;

    public Customer() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.address = faker.address().streetAddress();
        this.zip = faker.number().digits(5);
        this.city = faker.address().city();
        this.shippingMethod = "Click and collect";
        this.payment = "Pay by Cash on Delivery";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public String getPayment() {
        return payment;
    }
}
