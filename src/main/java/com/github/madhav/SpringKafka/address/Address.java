package com.github.madhav.SpringKafka.address;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    @Column(name = "ADDRESS_ID")
    private Long id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String contactNumber;

    // =============================================
    // Constructors
    // =============================================

    public Address() {
    }

    public Address(String name, String addressLine1, String addressLine2, String city, String state, String postalCode, String contactNumber) {
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.contactNumber = contactNumber;
    }

    public Address(Long id, String name, String addressLine1, String addressLine2, String city, String state, String postalCode, String contactNumber) {
        this.id = id;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.contactNumber = contactNumber;
    }

    // =============================================
    // Getters
    // =============================================

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    // =============================================
    // Setters
    // =============================================

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // =============================================
    // toString
    // =============================================

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
