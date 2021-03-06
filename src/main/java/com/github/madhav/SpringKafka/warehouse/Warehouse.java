package com.github.madhav.SpringKafka.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.madhav.SpringKafka.address.Address;
import com.github.madhav.SpringKafka.item_detail.ItemDetail;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "WAREHOUSE")
public class Warehouse {

    @Id
    @SequenceGenerator(
            name = "warehouse_sequence",
            sequenceName = "warehouse_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "warehouse_sequence"
    )
    @Column(name = "WAREHOUSE_ID")
    private Long id;
    private String name;

    // =======================================================
    // Many-to-many relationship between warehouses and items
    // =======================================================

    //    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    //    @JoinTable(name = "WAREHOUSE_ITEM",
    //            joinColumns = {@JoinColumn(name = "WAREHOUSE_ID", referencedColumnName = "id")},
    //            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "id")}
    //    )
    //    private Set<Item> itemSet = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ItemDetail> itemDetailList = new ArrayList<>();


    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<>();

    // ========================================================
    // Many-to-one relationship between warehouses and address
    // ========================================================

    @OneToOne
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;

    // =============================================
    // Constructors
    // =============================================

    public Warehouse() {
    }

    public Warehouse(String name) {
        this.name = name;
    }

    public Warehouse(String name, List<ItemDetail> itemDetailList, List<PurchaseDetail> purchaseDetailList, Address address) {
        this.name = name;
        this.itemDetailList = itemDetailList;
        this.purchaseDetailList = purchaseDetailList;
        this.address = address;
    }

    public Warehouse(Long id, String name, List<ItemDetail> itemDetailList, List<PurchaseDetail> purchaseDetailList, Address address) {
        this.id = id;
        this.name = name;
        this.itemDetailList = itemDetailList;
        this.purchaseDetailList = purchaseDetailList;
        this.address = address;
    }

    // =============================================
    // Getters
    // =============================================


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ItemDetail> getItemDetailList() {
        return itemDetailList;
    }

    public Address getAddress() {
        return address;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setItemDetailList(List<ItemDetail> itemDetailList) {
        this.itemDetailList = itemDetailList;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addItemDetail(ItemDetail itemDetail) {
        itemDetailList.add(itemDetail);
    }

    public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
        this.purchaseDetailList = purchaseDetailList;
    }

    public void addPurchaseDetailToWarehouse(PurchaseDetail purchaseDetail) {
        purchaseDetailList.add(purchaseDetail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(name, warehouse.name) && Objects.equals(address, warehouse.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
