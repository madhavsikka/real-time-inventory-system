package com.github.madhav.SpringKafka.purchase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.address.Address;
import com.github.madhav.SpringKafka.customer.Customer;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PURCHASE")
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
    private String purchaseDate;

    @ManyToOne
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;


    @OneToMany(mappedBy = "purchase", cascade = CascadeType.REMOVE)
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<>();

    // =============================================
    // Many-to-one mapping of customer and purchases
    // =============================================

    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    @JsonIgnore
    private Customer customer;

    // =============================================
    // Constructors
    // =============================================

    public Purchase() {
    }

    public Purchase(Double totalAmount, String purchaseDate, Address address) {
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.address = address;
    }

    public Purchase(Double totalAmount, String purchaseDate, Address address, List<PurchaseDetail> purchaseDetailList, Customer customer) {
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.address = address;
        this.purchaseDetailList = purchaseDetailList;
        this.customer = customer;
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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<PurchaseDetail> getPurchaseDetailList() {
        return purchaseDetailList;
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

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailSet) {
        this.purchaseDetailList = purchaseDetailSet;
    }

    public void addPurchaseDetailToPurchase(PurchaseDetail purchaseDetail) {
        purchaseDetailList.add(purchaseDetail);
    }

    // =============================================
    // toString
    // =============================================


    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", address=" + address +
                ", purchaseDetailList=" + purchaseDetailList +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(totalAmount, purchase.totalAmount) && Objects.equals(purchaseDate, purchase.purchaseDate) && Objects.equals(address, purchase.address) && Objects.equals(purchaseDetailList, purchase.purchaseDetailList) && Objects.equals(customer, purchase.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAmount, purchaseDate, address, purchaseDetailList, customer);
    }
}
