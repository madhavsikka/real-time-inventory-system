package com.github.madhav.SpringKafka.purchase;

import com.github.madhav.SpringKafka.address.Address;
import com.github.madhav.SpringKafka.customer.Customer;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Purchase {

    @Id
    @SequenceGenerator(
            name = "purchase_sequence",
            sequenceName = "purchase_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_sequence"
    )
    @Column(name = "PURCHASE_ID")
    private Long id;
    private Double totalAmount;
    private LocalDate purchaseDate;
    private String status;

    // =============================================
    // Many-to-one mapping of customer and purchases
    // =============================================

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    // =============================================
    // Many-to-one mapping of purchases and address
    // =============================================

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    // ===================================================
    // One-to-many mapping of purchase and purchaseDetails
    // ===================================================

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseDetail> purchaseDetailSet = new HashSet<>();

    // =============================================
    // Constructors
    // =============================================

    public Purchase() {
    }

    public Purchase(Double totalAmount, LocalDate purchaseDate, String status, Customer customer, Address address) {
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.customer = customer;
        this.address = address;
    }

    public Purchase(Long id, Double totalAmount, LocalDate purchaseDate, String status, Customer customer, Address address) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.customer = customer;
        this.address = address;
    }

    // =============================================
    // Getters
    // =============================================

    public Long getId() {
        return id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getAddress() {
        return address;
    }

    // =============================================
    // Setters
    // =============================================

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addPurchaseDetail(PurchaseDetail purchaseDetail) {
        purchaseDetailSet.add(purchaseDetail);
    }

    // =============================================
    // toString
    // =============================================

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", purchaseDate=" + purchaseDate +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
