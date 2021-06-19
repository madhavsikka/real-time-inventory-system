package com.github.madhav.SpringKafka.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalStateException("Address does not exist"));
    }

    public void addNewAddress(Address address) {
        addressRepository.save(address);
        System.out.println(address);
    }

    public void deleteAddress(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new IllegalStateException("Address does not exist");
        }
        addressRepository.deleteById(addressId);
    }

    @Transactional
    public void updateAddress(Long addressId, String name, String addressLine1,
                              String addressLine2, String city, String state,
                              String postalCode, String contactNumber) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalStateException("Address ID does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(name, address.getName())) {
            address.setName(name);
        }
        if (addressLine1 != null && addressLine1.length() > 0 && !Objects.equals(addressLine1, address.getAddressLine1())) {
            address.setAddressLine1(addressLine1);
        }
        if (addressLine2 != null && addressLine2.length() > 0 && !Objects.equals(addressLine2, address.getAddressLine2())) {
            address.setAddressLine2(addressLine2);
        }
        if (city != null && city.length() > 0 && !Objects.equals(city, address.getCity())) {
            address.setCity(city);
        }
        if (state != null && state.length() > 0 && !Objects.equals(state, address.getState())) {
            address.setState(state);
        }
        if (postalCode != null && postalCode.length() > 0 && !Objects.equals(postalCode, address.getPostalCode())) {
            address.setPostalCode(postalCode);
        }
        if (contactNumber != null && contactNumber.length() > 0 && !Objects.equals(contactNumber, address.getContactNumber())) {
            address.setContactNumber(contactNumber);
        }
    }

}
