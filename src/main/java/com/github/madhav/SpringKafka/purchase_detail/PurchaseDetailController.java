package com.github.madhav.SpringKafka.purchase_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/purchase_detail")
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @Autowired
    public PurchaseDetailController(PurchaseDetailService purchaseDetailService) {
        this.purchaseDetailService = purchaseDetailService;
    }

    @GetMapping
    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetailService.getPurchaseDetails();
    }

    @GetMapping(path = "/{purchaseDetailId}")
    public PurchaseDetail getPurchaseDetailById(@PathVariable("purchaseDetailId") Long purchaseDetailId) {
        return purchaseDetailService.getPurchaseDetailById(purchaseDetailId);
    }

    @PostMapping
    public void registerNewPurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {
        purchaseDetailService.addNewPurchaseDetail(purchaseDetail);
    }

    @PostMapping(path = "/{purchaseDetailId}/item/{itemId}/add")
    public void addItemToPurchaseDetail(
            @PathVariable("purchaseDetailId") Long purchaseDetailId,
            @PathVariable("itemId") Long itemId) {
        purchaseDetailService.addItemToPurchaseDetail(purchaseDetailId, itemId);
    }

    @DeleteMapping(path = "/{purchaseDetailId}")
    public void deletePurchaseDetail(@PathVariable("purchaseDetailId") Long purchaseDetailId) {
        purchaseDetailService.deletePurchaseDetail(purchaseDetailId);
    }

    @PostMapping(path = "/{purchaseDetailId}")
    public void updatePurchaseDetail(
            @PathVariable("purchaseDetailId") Long purchaseDetailId,
            @RequestParam(required = false) Long quantity,
            @RequestParam(required = false) Double amount) {
        purchaseDetailService.updatePurchaseDetail(purchaseDetailId, quantity, amount);
    }

}
