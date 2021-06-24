package com.github.madhav.SpringKafka.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.item_detail.ItemDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    // =======================================================
    // Many-to-many relationship between warehouses and items
    // =======================================================

//    @ManyToMany(mappedBy = "itemSet", fetch = FetchType.LAZY)
//    private Set<Warehouse> warehouseSet = new HashSet<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ItemDetail> itemDetailList = new ArrayList<>();

    // =============================================
    // Constructors
    // =============================================

    public Item() {
    }

    public Item(String name, Double unitPrice, List<ItemDetail> itemDetailList) {
        this.name = name;
        this.stock = 0L;
        this.unitPrice = unitPrice;
        this.itemDetailList = itemDetailList;
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

    public List<ItemDetail> getItemDetailList() {
        return itemDetailList;
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

    public void addItemDetail(ItemDetail itemDetail) {
        itemDetailList.add(itemDetail);
    }

    // =============================================
    // Hash Code
    // =============================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(unitPrice, item.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unitPrice);
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
                ", itemDetailList=" + itemDetailList +
                '}';
    }
}
