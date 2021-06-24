package com.github.madhav.SpringKafka.purchase_detail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.purchase.Purchase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PURCHASE_DETAIL")
public class PurchaseDetail {

    @Id
    @SequenceGenerator(
            name = "purchase_detail_sequence",
            sequenceName = "purchase_detail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_detail_sequence"
    )
    @Column(name = "PURCHASE_DETAIL_ID")
    private Long id;
    private Long quantity;
    private Double amount;

    // =======================================================
    // Many-to-one mapping of purchase details and purchase
    // =======================================================

    @ManyToOne
    @JoinColumn(name = "FK_PURCHASE_ID")
    @JsonIgnore
    private Purchase purchase;

    // ===================================================
    // Many-to-one mapping of purchase details and item
    // ===================================================

    @ManyToOne
    @JoinColumn(name = "FK_ITEM_ID")
    private Item item;

    // =============================================
    // Constructors
    // =============================================

    public PurchaseDetail() {
    }

    public PurchaseDetail(Long quantity, Double amount, Purchase purchase, Item item) {
        this.quantity = quantity;
        this.amount = amount;
        this.purchase = purchase;
        this.item = item;
    }

    public PurchaseDetail(Long id, Long quantity, Double amount, Purchase purchase, Item item) {
        this.id = id;
        this.quantity = quantity;
        this.amount = amount;
        this.purchase = purchase;
        this.item = item;
    }

    // =============================================
    // Getters
    // =============================================


    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Item getItem() {
        return item;
    }

    // =============================================
    // Setters
    // =============================================


    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", purchase=" + purchase +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDetail that = (PurchaseDetail) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(amount, that.amount) && Objects.equals(purchase, that.purchase) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, amount, purchase, item);
    }
}
