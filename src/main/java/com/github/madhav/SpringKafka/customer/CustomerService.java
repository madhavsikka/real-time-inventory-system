package com.github.madhav.SpringKafka.customer;

import com.github.madhav.SpringKafka.item.ItemService;
import com.github.madhav.SpringKafka.purchase.Purchase;
import com.github.madhav.SpringKafka.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PurchaseService purchaseService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PurchaseService purchaseService) {
        this.customerRepository = customerRepository;
        this.purchaseService = purchaseService;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer ID does not exist"));
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        customerRepository.save(customer);
        System.out.println(customer);
    }

    @Transactional
    public void addPurchaseToCustomer(Long customerId, Long purchaseId) {
        Customer customer = getCustomerById(customerId);
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        if (Objects.nonNull(purchase.getCustomer())) {
            throw new IllegalStateException("Purchase already has a customer");
        }
        customer.addPurchase(purchase);
        purchase.setCustomer(customer);
    }

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
