package com.github.madhav.SpringKafka.warehouse;

import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getWarehouses() {
        return warehouseService.getWarehouses();
    }

    @GetMapping(path = "/{warehouseId}")
    public Warehouse getWarehouseById(@PathVariable("warehouseId") Long warehouseId) {
        return warehouseService.getWarehouseById(warehouseId);
    }

    @GetMapping(path = "/{warehouseId}/purchase_details")
    public List<PurchaseDetail> getPurchaseDetailsOfWarehouse(@PathVariable("warehouseId") Long warehouseId) {
        return warehouseService.getPurchaseDetailsOfWarehouse(warehouseId);
    }

    @PostMapping
    public void registerNewWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.addNewWarehouse(warehouse);
    }

    @PutMapping("/{warehouseId}/add_item_detail")
    public void addNewItemDetailToWarehouse(
            @PathVariable("warehouseId") Long warehouseId,
            @RequestParam(name = "itemId") Long itemId,
            @RequestParam(name = "stock") Long stock
    ) {
        warehouseService.addNewItemDetailToWarehouse(warehouseId, itemId, stock);
    }

    @DeleteMapping(path = "{warehouseId}")
    public void deleteWarehouse(@PathVariable("warehouseId") Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
    }
}
