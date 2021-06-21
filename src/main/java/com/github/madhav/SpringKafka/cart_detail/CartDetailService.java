package com.github.madhav.SpringKafka.cart_detail;

import com.github.madhav.SpringKafka.cart.Cart;
import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CartDetailService {

    private final CartDetailRepository cartDetailRepository;
    private final ItemService itemService;

    @Autowired
    public CartDetailService(CartDetailRepository cartDetailRepository, ItemService itemService) {
        this.cartDetailRepository = cartDetailRepository;
        this.itemService = itemService;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetailRepository.findAll();
    }

    public CartDetail getCartDetailById(Long cartDetailId) {
        return cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new IllegalStateException("Cart Detail ID does not exist"));
    }

    public void addNewCartDetail(CartDetail cartDetail) {
        cartDetailRepository.save(cartDetail);
        System.out.println(cartDetail);
    }

    public CartDetail createNewCartDetailWithItem(Cart cart, Long itemId, Long quantity) {
        CartDetail cartDetail = new CartDetail();
        Item item = itemService.getItemById(itemId);
        cartDetail.setItem(item);
        cartDetail.setQuantity(quantity);
        cartDetail.setAmount(quantity * item.getUnitPrice());
        cartDetail.setCart(cart);
        return cartDetailRepository.save(cartDetail);
    }

    public void deleteCartDetail(Long cartDetailId) {
        if (!cartDetailRepository.existsById(cartDetailId)) {
            throw new IllegalStateException("Cart Detail ID does not exist");
        }
        cartDetailRepository.deleteById(cartDetailId);
    }

    @Transactional
    public void addItemToCartDetail(Long cartDetailId, Long itemId) {
        CartDetail cartDetail = getCartDetailById(cartDetailId);
        Item item = itemService.getItemById(itemId);
        if (Objects.nonNull(cartDetail.getItem())) {
            throw new IllegalStateException("Item already assigned to Cart Detail");
        }
        if (Objects.isNull(cartDetail.getQuantity())) {
            throw new IllegalStateException("Quantity missing from Cart Detail");
        }
        cartDetail.setAmount(cartDetail.getQuantity() * item.getUnitPrice());
        cartDetail.setItem(item);
        cartDetailRepository.save(cartDetail);
    }

    @Transactional
    public void updateCartDetail(Long cartDetailId, Long quantity, Double amount) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new IllegalStateException("Cart Detail ID does not exist"));

        if (quantity > 0 && !Objects.equals(quantity, cartDetail.getQuantity())) {
            cartDetail.setQuantity(quantity);
        }
        if (amount > 0 && !Objects.equals(amount, cartDetail.getAmount())) {
            cartDetail.setAmount(amount);
        }
    }

}
