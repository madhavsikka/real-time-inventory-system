package com.github.madhav.SpringKafka.customer;

import com.github.madhav.SpringKafka.cart.Cart;
import com.github.madhav.SpringKafka.purchase.Purchase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
    // One-to-one mapping of customer and cart
    // =============================================

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    // =============================================
    // One-to-many mapping of customer and purchases
    // =============================================

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Purchase> purchaseSet = new HashSet<>();

    // =============================================
    // Constructors
    // =============================================

    public Customer() {
    }

    public Customer(String firstName, String lastName, String contactNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.cart = new Cart();
    }

    public Customer(String firstName, String lastName, String contactNumber, String email, Cart cart, Set<Purchase> purchaseSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.cart = cart;
        this.purchaseSet = purchaseSet;
    }

    public Customer(Long id, String firstName, String lastName, String contactNumber, String email, Cart cart, Set<Purchase> purchaseSet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.cart = cart;
        this.purchaseSet = purchaseSet;
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

    public Cart getCart() {
        return cart;
    }

    public Set<Purchase> getPurchaseSet() {
        return purchaseSet;
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

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setPurchaseSet(Set<Purchase> purchaseSet) {
        this.purchaseSet = purchaseSet;
    }

    public void addPurchaseToCustomer(Purchase purchase) {
        purchaseSet.add(purchase);
    }

    // =============================================
    // toString
    // =============================================

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", cart=" + cart +
                ", purchaseSet=" + purchaseSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(contactNumber, customer.contactNumber) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, contactNumber, email);
    }
}
