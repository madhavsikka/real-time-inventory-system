package com.github.madhav.SpringKafka.item_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemDetailService {

    private final ItemDetailRepository itemDetailRepository;

    @Autowired
    public ItemDetailService(ItemDetailRepository itemDetailRepository) {
        this.itemDetailRepository = itemDetailRepository;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetailRepository.findAll();
    }

    public ItemDetail getItemDetailById(Long itemDetailId) {
        return itemDetailRepository.findById(itemDetailId)
                .orElseThrow(() -> new IllegalStateException("Item Detail does not exist"));
    }

    public ItemDetail getItemDetailByItemIdAndWarehouseId(Long itemId, Long warehouseId) {
        return itemDetailRepository.findByItemIdAndWarehouseId(itemId, warehouseId);
    }

    public ItemDetail addNewItemDetail(ItemDetail itemDetail) {
        return itemDetailRepository.save(itemDetail);
    }

    public void deleteItemDetail(Long itemDetailId) {
        if (!itemDetailRepository.existsById(itemDetailId)) {
            throw new IllegalStateException("Item Detail does not exist");
        }
        itemDetailRepository.deleteById(itemDetailId);
    }

    @Transactional
    public void setStock(Long itemDetailId, Long stock) {
        ItemDetail itemDetail = itemDetailRepository.findById(itemDetailId)
                .orElseThrow(() -> new IllegalStateException("Item Detail does not exist"));
        itemDetail.setStock(stock);
    }

    @Transactional
    public boolean addStockByItemIdAndWarehouseId(Long itemId, Long warehouseId, Long stock) {
        ItemDetail itemDetail = getItemDetailByItemIdAndWarehouseId(itemId, warehouseId);
        if (itemDetail == null) return false;
        itemDetail.setStock(itemDetail.getStock() + stock);
        return true;
    }
}
