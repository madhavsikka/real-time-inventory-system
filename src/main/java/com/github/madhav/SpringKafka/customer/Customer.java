package com.github.madhav.SpringKafka.customer;

import com.github.madhav.SpringKafka.purchase.Purchase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "CUSTOMER_ID")
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;

    // =============================================
    // One-to-many mapping of customer and purchases
    // =============================================

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Purchase> purchaseSet;

    // =============================================
    // Constructors
    // =============================================

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public Customer(Long id, String firstName, String lastName, String email, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    // =============================================
    // Getters
    // =============================================

    public Long getId() {
        return id;
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

    public String getContactNumber() {
        return contactNumber;
    }

    // =============================================
    // Setters
    // =============================================

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void addPurchase(Purchase purchase) {
        purchaseSet.add(purchase);
    }

    // =============================================
    // toString
    // =============================================

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
