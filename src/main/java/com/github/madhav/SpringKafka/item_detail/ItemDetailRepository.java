package com.github.madhav.SpringKafka.item_detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {
    @Query(value = "SELECT * FROM item_detail WHERE fk_item_id = ?1 and fk_warehouse_id = ?2", nativeQuery = true)
    ItemDetail findByItemIdAndWarehouseId(Long itemId, Long warehouseId);
    //    @Query(value = "UPDATE item_detail detail set detail.stock = ?3 WHERE detail.fk_item_id = ?1 and detail.fk_warehouse_id = ?2", nativeQuery = true)
}
