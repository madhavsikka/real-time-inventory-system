package com.github.madhav.SpringKafka.item_detail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.warehouse.Warehouse;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_DETAIL")
public class ItemDetail {

    @Id
    @SequenceGenerator(
            name = "item_detail_sequence",
            sequenceName = "item_detail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_detail_sequence"
    )
    @Column(name = "ITEM_DETAIL_ID")
    Long id;
    Long stock;

    @ManyToOne
    @JoinColumn(name = "FK_ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "FK_WAREHOUSE_ID")
    @JsonIgnore
    private Warehouse warehouse;

    // =============================================
    // Constructors
    // =============================================

    public ItemDetail() {
    }

    public ItemDetail(Long stock, Item item, Warehouse warehouse) {
        this.stock = stock;
        this.item = item;
        this.warehouse = warehouse;
    }

    public ItemDetail(Long id, Long stock, Item item, Warehouse warehouse) {
        this.id = id;
        this.stock = stock;
        this.item = item;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public Long getStock() {
        return stock;
    }

    public Item getItem() {
        return item;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "id=" + id +
                ", stock=" + stock +
                ", item=" + item +
                ", warehouse=" + warehouse +
                '}';
    }
}
