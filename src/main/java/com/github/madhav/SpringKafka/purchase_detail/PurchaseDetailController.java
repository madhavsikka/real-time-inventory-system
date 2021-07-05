package com.github.madhav.SpringKafka.purchase_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/{purchaseDetailId}/status")
    public void updatePurchaseDetailStatus(
            @PathVariable("purchaseDetailId") Long purchaseDetailId,
            @RequestParam("status") String status
    ) {
        purchaseDetailService.updatePurchaseDetailStatus(purchaseDetailId, status);
    }
}
