package com.github.madhav.SpringKafka.purchase;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String status;
    private String deliveryName;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryPostalCode;
    private String deliveryContactNumber;


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

    public Purchase(Double totalAmount, String purchaseDate, String status, String deliveryName, String deliveryAddressLine1, String deliveryAddressLine2, String deliveryCity, String deliveryState, String deliveryPostalCode, String deliveryContactNumber, List<PurchaseDetail> purchaseDetailList, Customer customer) {
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.deliveryName = deliveryName;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.deliveryAddressLine2 = deliveryAddressLine2;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryContactNumber = deliveryContactNumber;
        this.purchaseDetailList = purchaseDetailList;
        this.customer = customer;
    }

    public Purchase(Long id, Double totalAmount, String purchaseDate, String status, String deliveryName, String deliveryAddressLine1, String deliveryAddressLine2, String deliveryCity, String deliveryState, String deliveryPostalCode, String deliveryContactNumber, List<PurchaseDetail> purchaseDetailList, Customer customer) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.deliveryName = deliveryName;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.deliveryAddressLine2 = deliveryAddressLine2;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryContactNumber = deliveryContactNumber;
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

    public String getStatus() {
        return status;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public String getDeliveryContactNumber() {
        return deliveryContactNumber;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    public void setDeliveryContactNumber(String deliveryContactNumber) {
        this.deliveryContactNumber = deliveryContactNumber;
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
                ", status='" + status + '\'' +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryAddressLine1='" + deliveryAddressLine1 + '\'' +
                ", deliveryAddressLine2='" + deliveryAddressLine2 + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryPostalCode='" + deliveryPostalCode + '\'' +
                ", deliveryContactNumber='" + deliveryContactNumber + '\'' +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(totalAmount, purchase.totalAmount) && Objects.equals(purchaseDate, purchase.purchaseDate) && Objects.equals(status, purchase.status) && Objects.equals(deliveryName, purchase.deliveryName) && Objects.equals(deliveryAddressLine1, purchase.deliveryAddressLine1) && Objects.equals(deliveryAddressLine2, purchase.deliveryAddressLine2) && Objects.equals(deliveryCity, purchase.deliveryCity) && Objects.equals(deliveryState, purchase.deliveryState) && Objects.equals(deliveryPostalCode, purchase.deliveryPostalCode) && Objects.equals(deliveryContactNumber, purchase.deliveryContactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAmount, purchaseDate, status, deliveryName, deliveryAddressLine1, deliveryAddressLine2, deliveryCity, deliveryState, deliveryPostalCode, deliveryContactNumber);
    }
}
