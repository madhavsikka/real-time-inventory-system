package com.github.madhav.SpringKafka.purchase;

import com.github.madhav.SpringKafka.address.Address;
import com.github.madhav.SpringKafka.address.AddressService;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailService purchaseDetailService;
    private final AddressService addressService;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseDetailService purchaseDetailService, AddressService addressService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailService = purchaseDetailService;
        this.addressService = addressService;
    }

    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalStateException("Purchase ID does not exist"));
    }

    public void addNewPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
        System.out.println(purchase);
    }

    @Transactional
    public void addPurchaseDetailToPurchase(Long purchaseId, Long purchaseDetailId) {
        Purchase purchase = getPurchaseById(purchaseId);
        PurchaseDetail purchaseDetail = purchaseDetailService.getPurchaseDetailById(purchaseDetailId);
        if (Objects.nonNull(purchaseDetail.getPurchase())) {
            throw new IllegalStateException("Purchase Detail already has a Purchase");
        }
        purchase.addPurchaseDetail(purchaseDetail);
        purchaseDetail.setPurchase(purchase);
    }

    @Transactional
    public void addAddressToPurchase(Long purchaseId, Long addressId) {
        Purchase purchase = getPurchaseById(purchaseId);
        Address address = addressService.getAddressById(addressId);
        if (Objects.nonNull(purchase.getAddress())) {
            throw new IllegalStateException("Purchase already has an address");
        }
        purchase.setAddress(address);
    }

    public void deletePurchase(Long purchaseId) {
        if (!purchaseRepository.existsById(purchaseId)) {
            throw new IllegalStateException("Purchase ID does not exist");
        }
        purchaseRepository.deleteById(purchaseId);
    }

    @Transactional
    public void updatePurchase(Long purchaseId, Double totalAmount, String status) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalStateException("Purchase ID does not exist"));

        if (totalAmount > 0 && !Objects.equals(totalAmount, purchase.getTotalAmount())) {
            purchase.setTotalAmount(totalAmount);
        }
        if (status != null && status.length() > 0 && !Objects.equals(status, purchase.getStatus())) {
            purchase.setStatus(status);
        }
    }


}
