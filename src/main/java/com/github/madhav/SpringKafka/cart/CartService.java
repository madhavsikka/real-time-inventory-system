package com.github.madhav.SpringKafka.cart;

import com.github.madhav.SpringKafka.cart_detail.CartDetail;
import com.github.madhav.SpringKafka.cart_detail.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDetailService cartDetailService;

    @Autowired
    public CartService(CartRepository cartRepository, CartDetailService cartDetailService) {
        this.cartRepository = cartRepository;
        this.cartDetailService = cartDetailService;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Cart ID does not exist"));
    }

    public void addNewCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new IllegalStateException("Cart ID does not exist");
        }
        cartRepository.deleteById(cartId);
    }


    public void addCartDetailToCart(Long cartId, Long cartDetailId) {
        Cart cart = getCartById(cartId);
        CartDetail cartDetail = cartDetailService.getCartDetailById(cartDetailId);
        if (Objects.nonNull(cartDetail.getCart())) {
            throw new IllegalStateException("Cart Detail already has a Cart");
        }
        cart.addCartDetailToCart(cartDetail);
        cartDetail.setCart(cart);
    }

    public void addItemToCart(Long cartId, Long itemId, Long quantity) {
        Cart cart = getCartById(cartId);
        cart.addCartDetailToCart(cartDetailService.createNewCartDetailWithItem(cart, itemId, quantity));
    }

    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = getCartById(cartId);
        Set<CartDetail> cartDetailSet = cart.getCartDetailSet();
        cartDetailSet.clear();
    }
}
