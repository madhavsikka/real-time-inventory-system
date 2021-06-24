package com.github.madhav.SpringKafka.customer;

import com.github.madhav.SpringKafka.cart.Cart;
import com.github.madhav.SpringKafka.cart.CartService;
import com.github.madhav.SpringKafka.cart_detail.CartDetail;
import com.github.madhav.SpringKafka.item.Item;
import com.github.madhav.SpringKafka.item.ItemService;
import com.github.madhav.SpringKafka.purchase.Purchase;
import com.github.madhav.SpringKafka.purchase.PurchaseService;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetail;
import com.github.madhav.SpringKafka.purchase_detail.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PurchaseService purchaseService;
    private final CartService cartService;
    private final ItemService itemService;
    private final PurchaseDetailService purchaseDetailService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PurchaseService purchaseService, CartService cartService, ItemService itemService, PurchaseDetailService purchaseDetailService) {
        this.customerRepository = customerRepository;
        this.purchaseService = purchaseService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.purchaseDetailService = purchaseDetailService;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer ID does not exist"));
    }

    public void addCartDetailToCustomerCart(Long customerId, Long cartDetailId) {
        Customer customer = getCustomerById(customerId);
        Cart cart = customer.getCart();
        if (Objects.isNull(cart)) {
            throw new IllegalStateException("Cart does not exist");
        }
        cartService.addCartDetailToCart(cart.getId(), cartDetailId);
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        customerRepository.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartService.addNewCart(cart);
        customer.setCart(cart);
        System.out.println(customer);
    }

    public void addItemToCart(Long customerId, Long itemId, Long quantity) {
        Customer customer = getCustomerById(customerId);
        Cart cart = customer.getCart();
        if (Objects.isNull(cart)) {
            throw new IllegalStateException("Cart does not exist");
        }
        cartService.addItemToCart(cart.getId(), itemId, quantity);
    }

    @Transactional
    public void buyCart(Long customerId) {
        Customer customer = getCustomerById(customerId);
        Cart cart = customer.getCart();
        if (Objects.isNull(cart)) {
            throw new IllegalStateException("Cart does not exist");
        }
        List<CartDetail> cartDetailSet = cart.getCartDetailList();

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);

        Double totalAmount = 0.0;
        for (CartDetail cartDetail : cartDetailSet) {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            Item item = cartDetail.getItem();
            Long stock = item.getStock();
            Long quantity = cartDetail.getQuantity();
            Double amount = cartDetail.getAmount();

            if (stock < quantity) throw new IllegalStateException("Insufficient Stock of Item");
            itemService.reduceItemStock(item.getId(), quantity);

            totalAmount += amount;
            purchaseDetail.setAmount(amount);
            purchaseDetail.setQuantity(quantity);
            purchaseDetail.setItem(item);
            purchaseDetail.setPurchase(purchase);
            purchase.addPurchaseDetailToPurchase(purchaseDetailService.addNewPurchaseDetail(purchaseDetail));
        }

        purchase.setTotalAmount(totalAmount);
        purchase.setStatus("ORDER RECEIVED");
        purchase.setPurchaseDate(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));

        customer.addPurchaseToCustomer(purchaseService.addNewPurchase(purchase));
        cartService.clearCart(cart.getId());
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalStateException("Customer ID: " + customerId + " does not exist");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId, String firstName, String lastName,
                               String contactNumber, String email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer ID: " + customerId + " does not exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(), firstName)) {
            customer.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(), lastName)) {
            customer.setLastName(lastName);
        }

        if (contactNumber != null && contactNumber.length() > 0 && !Objects.equals(customer.getContactNumber(), contactNumber)) {
            customer.setContactNumber(contactNumber);
        }

        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)) {
            customer.setEmail(email);
        }

    }


}
