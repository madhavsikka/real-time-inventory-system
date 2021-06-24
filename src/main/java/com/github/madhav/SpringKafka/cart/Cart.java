package com.github.madhav.SpringKafka.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.cart_detail.CartDetail;
import com.github.madhav.SpringKafka.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    @Column(name = "CART_ID")
    private Long id;

    // =============================================
    // One-to-one mapping of customer and cart
    // =============================================

    @OneToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    @JsonIgnore
    private Customer customer;

    // ===================================================
    // One-to-many mapping of cart and purchaseDetails
    // ===================================================

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> cartDetailList = new ArrayList<>();

    // ===================================================
    // Constructors
    // ===================================================


    public Cart() {
    }

    public Cart(Customer customer, List<CartDetail> cartDetailList) {
        this.customer = customer;
        this.cartDetailList = cartDetailList;
    }

    public Cart(Long id, Customer customer, List<CartDetail> cartDetailList) {
        this.id = id;
        this.customer = customer;
        this.cartDetailList = cartDetailList;
    }

    // ===================================================
    // Getters
    // ===================================================

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCartDetailList(List<CartDetail> cartDetailSet) {
        this.cartDetailList = cartDetailSet;
    }

    public void addCartDetailToCart(CartDetail cartDetail) {
        cartDetailList.add(cartDetail);
    }

    public void clearCart() {
        cartDetailList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
