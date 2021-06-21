package com.github.madhav.SpringKafka.purchase_detail;

import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PurchaseDetailService {

    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    public PurchaseDetailService(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetailRepository.findAll();
    }

    public PurchaseDetail getPurchaseDetailById(Long purchaseDetailId) {
        return purchaseDetailRepository.findById(purchaseDetailId)
                .orElseThrow(() -> new IllegalStateException("Purchase Detail ID does not exist"));
    }

    public PurchaseDetail addNewPurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    @Transactional
    public PurchaseDetail updatePurchaseDetail(Long purchaseDetailId, Long quantity, Double amount,
                                               Purchase purchase, Item item) {

        PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(purchaseDetailId)
                .orElseThrow(() -> new IllegalStateException("Purchase Detail ID does not exist"));

        if (quantity > 0 && !Objects.equals(quantity, purchaseDetail.getQuantity())) {
            purchaseDetail.setQuantity(quantity);
        }
        if (amount > 0 && !Objects.equals(amount, purchaseDetail.getAmount())) {
            purchaseDetail.setAmount(amount);
        }
        if (purchase != null && !Objects.equals(purchase, purchaseDetail.getPurchase())) {
            purchaseDetail.setPurchase(purchase);
        }
        if (item != null && !Objects.equals(item, purchaseDetail.getItem())) {
            purchaseDetail.setItem(item);
        }
        return purchaseDetail;
    }

}
