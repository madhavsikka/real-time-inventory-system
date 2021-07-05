package com.github.madhav.SpringKafka.item;

import com.github.madhav.SpringKafka.item_detail.ItemDetail;
import com.github.madhav.SpringKafka.item_detail.ItemDetailService;
import com.github.madhav.SpringKafka.purchase.Purchase;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetailService;
import com.github.madhav.SpringKafka.warehouse.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemDetailService itemDetailService;
    private final PurchaseDetailService purchaseDetailService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemDetailService itemDetailService, PurchaseDetailService purchaseDetailService) {
        this.itemRepository = itemRepository;
        this.itemDetailService = itemDetailService;
        this.purchaseDetailService = purchaseDetailService;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item ID does not exist"));
    }

    public void addNewItem(Item item) {
        itemRepository.save(item);
        System.out.println(item);
    }

    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalStateException("Item ID does not exist");
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public void reduceItemStock(Long itemId, Long reduceStockBy) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item ID does not exist"));
        Long currentStock = item.getStock();
        if (currentStock < reduceStockBy) {
            throw new IllegalStateException("Insufficient Stock");
        }
        item.setStock(currentStock - reduceStockBy);
        List<ItemDetail> itemDetailList = item.getItemDetailList();
        for (ItemDetail itemDetail : itemDetailList) {
            if (reduceStockBy <= 0) break;
            Long availableStock = itemDetail.getStock();
            long reduceFromThis = Math.min(reduceStockBy, availableStock);
            reduceStockBy -= reduceFromThis;
            itemDetailService.setStock(itemDetail.getId(), availableStock - reduceFromThis);
        }
    }

    @Transactional
    public void reduceItemStockForWarehouses(Item item, Long reduceStockBy, Purchase purchase) {

        Long currentStock = item.getStock();
        if (currentStock < reduceStockBy) {
            throw new IllegalStateException("Insufficient Stock");
        }

        item.setStock(currentStock - reduceStockBy);
        Double unitPrice = item.getUnitPrice();

        List<ItemDetail> itemDetailList = item.getItemDetailList();
        for (ItemDetail itemDetail : itemDetailList) {
            if (reduceStockBy <= 0) break;

            Long availableStock = itemDetail.getStock();
            long reduceFromThis = Math.min(reduceStockBy, availableStock);

            Warehouse warehouse = itemDetail.getWarehouse();

            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setAmount(unitPrice * reduceFromThis);
            purchaseDetail.setQuantity(reduceFromThis);
            purchaseDetail.setItem(item);
            purchaseDetail.setStatus("ORDER_RECEIVED");
            purchaseDetail.setPurchase(purchase);
            purchaseDetail.setWarehouse(warehouse);

            PurchaseDetail savedPurchaseDetail = purchaseDetailService.addNewPurchaseDetail(purchaseDetail);

            purchase.addPurchaseDetailToPurchase(savedPurchaseDetail);
            warehouse.addPurchaseDetailToWarehouse(savedPurchaseDetail);

            reduceStockBy -= reduceFromThis;
            itemDetailService.setStock(itemDetail.getId(), availableStock - reduceFromThis);
        }
    }

    @Transactional
    public void updateItem(Long itemId, Long stock) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item ID does not exist"));

        if (stock > 0 && !Objects.equals(stock, item.getStock())) {
            item.setStock(stock);
        }
    }
}
