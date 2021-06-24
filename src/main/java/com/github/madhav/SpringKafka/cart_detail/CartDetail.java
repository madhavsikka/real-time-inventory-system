package com.github.madhav.SpringKafka.cart_detail;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.cart.Cart;
import com.github.madhav.SpringKafka.item.Item;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CART_DETAIL")
public class CartDetail {

    @Id
    @SequenceGenerator(
            name = "cart_detail_sequence",
            sequenceName = "cart_detail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_detail_sequence"
    )
    @Column(name = "CART_DETAIL_ID")
    private Long id;
    private Long quantity;
    private Double amount;

    // =======================================================
    // Many-to-one mapping of purchase details and purchase
    // =======================================================

    @ManyToOne
    @JoinColumn(name = "FK_CART_ID")
    @JsonIgnore
    private Cart cart;

    // ===================================================
    // Many-to-one mapping of purchase details and item
    // ===================================================

    @ManyToOne
    @JoinColumn(name = "FK_ITEM_ID")
    private Item item;

    // =============================================
    // Constructors
    // =============================================

    public CartDetail() {
    }

    public CartDetail(Long quantity) {
        this.quantity = quantity;
    }

    public CartDetail(Long quantity, Double amount, Cart cart, Item item) {
        this.quantity = quantity;
        this.amount = amount;
        this.cart = cart;
        this.item = item;
    }

    public CartDetail(Long id, Long quantity, Double amount, Cart cart, Item item) {
        this.id = id;
        this.quantity = quantity;
        this.amount = amount;
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
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

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // =============================================
    // toString
    // =============================================

    @Override
    public String toString() {
        return "CartDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", cart=" + cart +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetail that = (CartDetail) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(amount, that.amount) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, amount, item);
    }
}
