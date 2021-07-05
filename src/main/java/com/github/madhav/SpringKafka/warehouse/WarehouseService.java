package com.github.madhav.SpringKafka.warehouse;

import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.item.ItemService;
import com.github.madhav.SpringKafka.item_detail.ItemDetail;
import com.github.madhav.SpringKafka.item_detail.ItemDetailService;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ItemService itemService;
    private final ItemDetailService itemDetailService;


    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, ItemService itemService, ItemDetailService itemDetailService) {
        this.warehouseRepository = warehouseRepository;
        this.itemService = itemService;
        this.itemDetailService = itemDetailService;
    }

    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new IllegalStateException("Warehouse does not exist"));
    }

    public void addNewWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public List<PurchaseDetail> getPurchaseDetailsOfWarehouse(Long warehouseId) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        return warehouse.getPurchaseDetailList();
    }

    @Transactional
    public void addNewItemDetailToWarehouse(Long warehouseId, Long itemId, Long stock) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        Item item = itemService.getItemById(itemId);

        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setStock(stock);
        itemDetail.setItem(item);
        itemDetail.setWarehouse(warehouse);
        ItemDetail createdItemDetail = itemDetailService.addNewItemDetail(itemDetail);
        warehouse.addItemDetail(createdItemDetail);
        item.addItemDetail(createdItemDetail);
        item.setStock(item.getStock() + stock);
    }

    public void deleteWarehouse(Long warehouseId) {
        if (!warehouseRepository.existsById(warehouseId)) {
            throw new IllegalStateException("Warehouse does not exist");
        }
        warehouseRepository.deleteById(warehouseId);
    }

}
