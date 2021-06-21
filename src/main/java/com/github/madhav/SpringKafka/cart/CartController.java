package com.github.madhav.SpringKafka.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping(path = "/{cartId}")
    public Cart getCartById(@PathVariable("cartId") Long cartId) {
        return cartService.getCartById(cartId);
    }

    @PostMapping
    public void registerNewCart(@RequestBody Cart cart) {
        cartService.addNewCart(cart);
    }

    @DeleteMapping(path = "/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
    }

    @PostMapping(path = "/{cartId}/cart_detail/{cartDetailId}/add")
    public void addCartDetailToCart(
            @PathVariable("cartId") Long cartId,
            @PathVariable("cartDetailId") Long cartDetailId) {
        cartService.addCartDetailToCart(cartId, cartDetailId);
    }

}
