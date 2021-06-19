package com.github.madhav.SpringKafka.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping(path = "/{addressId}")
    public Address getAddressById(@PathVariable("addressId") Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PostMapping
    public void registerNewAddress(@RequestBody Address address) {
        addressService.addNewAddress(address);
    }

    @DeleteMapping(path = "{addressId}")
    public void deleteAddress(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddress(addressId);
    }

    @PutMapping(path = "{addressId}")
    public void updateAddress(
            @PathVariable("addressId") Long addressId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String addressLine1,
            @RequestParam(required = false) String addressLine2,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String contactNumber) {
        addressService.updateAddress(addressId, name, addressLine1, addressLine2,
                city, state, postalCode, contactNumber);
    }
}
