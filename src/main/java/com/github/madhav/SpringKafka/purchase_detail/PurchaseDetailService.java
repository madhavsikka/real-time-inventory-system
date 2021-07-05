package com.github.madhav.SpringKafka.purchase_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public PurchaseDetail updatePurchaseDetailStatus(Long purchaseDetailId, String status) {

        PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(purchaseDetailId)
                .orElseThrow(() -> new IllegalStateException("Purchase Detail ID does not exist"));

        purchaseDetail.setStatus(status);
        return purchaseDetail;
    }

}
