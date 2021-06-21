package com.github.madhav.SpringKafka.cart_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cart_detail")
public class CartDetailController {

    private final CartDetailService cartDetailService;

    @Autowired
    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @GetMapping
    public List<CartDetail> getCartDetails() {
        return cartDetailService.getCartDetails();
    }

    @GetMapping(path = "/{cartDetailId}")
    public CartDetail getPurchaseDetailById(@PathVariable("cartDetailId") Long cartDetailId) {
        return cartDetailService.getCartDetailById(cartDetailId);
    }

    @PostMapping
    public void registerNewCartDetail(@RequestBody CartDetail cartDetail) {
        cartDetailService.addNewCartDetail(cartDetail);
    }

    @PostMapping(path = "/{cartDetailId}/item/{itemId}/add")
    public void addItemToPurchaseDetail(
            @PathVariable("cartDetailId") Long cartDetailId,
            @PathVariable("itemId") Long itemId) {
        cartDetailService.addItemToCartDetail(cartDetailId, itemId);
    }

    @DeleteMapping(path = "/{cartDetailId}")
    public void deleteCartDetail(@PathVariable("cartDetailId") Long cartDetailId) {
        cartDetailService.deleteCartDetail(cartDetailId);
    }

    @PostMapping(path = "/{cartDetailId}")
    public void updateCartDetail(
            @PathVariable("cartDetailId") Long cartDetailId,
            @RequestParam(required = false) Long quantity,
            @RequestParam(required = false) Double amount) {
        cartDetailService.updateCartDetail(cartDetailId, quantity, amount);
    }

}
