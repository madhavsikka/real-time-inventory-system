package com.github.madhav.SpringKafka.item;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    @Column(name = "ITEM_ID")
    private Long id;
    private Long stock;
    private String name;
    private Double unitPrice;

    // =============================================
    // Constructors
    // =============================================

    public Item() {
    }

    public Item(Long stock, String name, Double unitPrice) {
        this.stock = stock;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Item(Long id, Long stock, String name, Double unitPrice) {
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    // =============================================
    // Getters
    // =============================================

    public Long getId() {
        return id;
    }

    public Long getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }


    // =============================================
    // Setters
    // =============================================

    public void setId(Long id) {
        this.id = id;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // =============================================
    // Hash Code
    // =============================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    // =============================================
    // toString
    // =============================================

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", stock=" + stock +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
