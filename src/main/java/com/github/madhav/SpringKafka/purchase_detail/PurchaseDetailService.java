package com.github.madhav.SpringKafka.purchase_detail;

import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ItemService itemService;

    @Autowired
    public PurchaseDetailService(PurchaseDetailRepository purchaseDetailRepository, ItemService itemService) {
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.itemService = itemService;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    public PurchaseDetail getPurchaseDetailById(Long purchaseDetailId) {
        return purchaseDetailRepository.findById(purchaseDetailId)
                .orElseThrow(() -> new IllegalStateException("Purchase Detail ID does not exist"));
    }

    public void addNewPurchaseDetail(PurchaseDetail purchaseDetail) {
        purchaseDetailRepository.save(purchaseDetail);
        System.out.println(purchaseDetail);
    }

    public void deletePurchaseDetail(Long purchaseDetailId) {
        if (!purchaseDetailRepository.existsById(purchaseDetailId)) {
            throw new IllegalStateException("Purchase Detail ID does not exist");
        }
        purchaseDetailRepository.deleteById(purchaseDetailId);
    }

    @Transactional
    public void addItemToPurchaseDetail(Long purchaseDetailId, Long itemId) {
        PurchaseDetail purchaseDetail = getPurchaseDetailById(purchaseDetailId);
        Item item = itemService.getItemById(itemId);
        if (Objects.nonNull(purchaseDetail.getItem())) {
            throw new IllegalStateException("Item already assigned to Purchase Detail");
        }
        purchaseDetail.setItem(item);
        purchaseDetailRepository.save(purchaseDetail);
    }

    @Transactional
    public void updatePurchaseDetail(Long purchaseDetailId, Long quantity, Double amount) {
        PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(purchaseDetailId)
                .orElseThrow(() -> new IllegalStateException("Purchase Detail ID does not exist"));

        if (quantity > 0 && !Objects.equals(quantity, purchaseDetail.getQuantity())) {
            purchaseDetail.setQuantity(quantity);
        }
        if (amount > 0 && !Objects.equals(amount, purchaseDetail.getAmount())) {
            purchaseDetail.setAmount(amount);
        }
    }

}
