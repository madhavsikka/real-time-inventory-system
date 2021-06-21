package com.github.madhav.SpringKafka.customer;

import com.github.madhav.SpringKafka.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CartService cartService;

    @Autowired
    public CustomerController(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @PostMapping(path = "{customerId}/buy")
    public void addPurchaseToCustomer(
            @PathVariable("customerId") Long customerId) {
        customerService.buyCart(customerId);
    }

    @PutMapping(path = "/{customerId}/cart/{cartDetailId}/add")
    public void addCartDetailToCustomerCart(
            @PathVariable("customerId") Long customerId,
            @PathVariable("cartDetailId") Long cartDetailId) {
        customerService.addCartDetailToCustomerCart(customerId, cartDetailId);
    }

    @PutMapping(path = "/{customerId}/cart/add_item")
    public void addItemToCustomerCart(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "itemId") Long itemId,
            @RequestParam(name = "quantity") Long quantity) {
        customerService.addItemToCart(customerId, itemId, quantity);
    }

    @DeleteMapping(path = "/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String contactNumber,
            @RequestParam(required = false) String email) {
        customerService.updateCustomer(customerId, firstName, lastName, contactNumber, email);
    }

}
