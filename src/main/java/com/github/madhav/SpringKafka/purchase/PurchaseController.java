package com.github.madhav.SpringKafka.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }

    @GetMapping(path = "/{purchaseId}")
    public Purchase getPurchaseById(@PathVariable("purchaseId") Long purchaseId) {
        return purchaseService.getPurchaseById(purchaseId);
    }

    @PostMapping
    public void registerNewPurchase(@RequestBody Purchase purchase) {
        purchaseService.addNewPurchase(purchase);
    }

    @DeleteMapping(path = "/{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") Long purchaseId) {
        purchaseService.deletePurchase(purchaseId);
    }

    @PutMapping(path = "/{purchaseId}")
    public void updatePurchase(
            @PathVariable("purchaseId") Long purchaseId,
            @RequestParam(required = false) Double totalAmount,
            @RequestParam(required = false) String status) {
        purchaseService.updatePurchase(purchaseId, totalAmount, status);
    }

    @PostMapping(path = "/{purchaseId}/address/add/{addressId}")
    public void addAddressToPurchase(
            @PathVariable("purchaseId") Long purchaseId,
            @PathVariable("addressId") Long addressId) {
        purchaseService.addAddressToPurchase(purchaseId, addressId);
    }

    @PostMapping(path = "/{purchaseId}/purchase_details/{purchaseDetailId}/add")
    public void addPurchaseDetailToPurchase(
            @PathVariable("purchaseId") Long purchaseId,
            @PathVariable("purchaseDetailId") Long purchaseDetailId) {
        purchaseService.addPurchaseDetailToPurchase(purchaseId, purchaseDetailId);
    }

    // NOTE for future use
    // @PutMapping("/orders/{id}/complete")
    // @PutMapping("/orders/{id}/shipped")
    // content/v2.1/{merchantId}/orders/{orderId}/updateShipment
    // {
    //  "shipmentId": string,
    //  "status": string,
    //  "trackingId": string,
    //  "deliveryDate": string,
    //}

}
