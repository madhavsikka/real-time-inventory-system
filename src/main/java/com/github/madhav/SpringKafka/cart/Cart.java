package com.github.madhav.SpringKafka.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.customer.Customer;
import com.github.madhav.SpringKafka.cart_detail.CartDetail;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<CartDetail> cartDetailSet = new HashSet<>();

    // ===================================================
    // Constructors
    // ===================================================


    public Cart() {
    }

    public Cart(Long id, Customer customer, Set<CartDetail> cartDetailSet) {
        this.id = id;
        this.customer = customer;
        this.cartDetailSet = cartDetailSet;
    }

    public Cart(Customer customer, Set<CartDetail> cartDetailSet) {
        this.customer = customer;
        this.cartDetailSet = cartDetailSet;
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

    public Set<CartDetail> getCartDetailSet() {
        return cartDetailSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCartDetailSet(Set<CartDetail> cartDetailSet) {
        this.cartDetailSet = cartDetailSet;
    }

    public void addCartDetailToCart(CartDetail cartDetail) {
        cartDetailSet.add(cartDetail);
    }

    public void clearCart() {
        cartDetailSet.clear();
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
