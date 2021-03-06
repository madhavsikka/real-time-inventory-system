package com.github.madhav.SpringKafka.purchase;

import com.github.madhav.SpringKafka.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final AddressService addressService;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, AddressService addressService) {
        this.purchaseRepository = purchaseRepository;
        this.addressService = addressService;
    }

    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalStateException("Purchase ID does not exist"));
    }

    public Purchase addNewPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void deletePurchase(Long purchaseId) {
        if (!purchaseRepository.existsById(purchaseId)) {
            throw new IllegalStateException("Purchase ID does not exist");
        }
        purchaseRepository.deleteById(purchaseId);
    }

    @Transactional
    public void updatePurchase(Long purchaseId, Double totalAmount) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalStateException("Purchase ID does not exist"));

        if (totalAmount > 0 && !Objects.equals(totalAmount, purchase.getTotalAmount())) {
            purchase.setTotalAmount(totalAmount);
        }
    }


}
