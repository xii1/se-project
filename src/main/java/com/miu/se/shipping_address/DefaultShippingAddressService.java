package com.miu.se.shipping_address;

import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.interfacing.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultShippingAddressService implements ShippingAddressService {
    @Autowired
    ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddress getShippingAddressById(Long id) {
        return shippingAddressRepository.getOne(id);
    }

    @Override
    public ShippingAddress save(ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    @Override
    public List<ShippingAddress> getAll() {
        return shippingAddressRepository.findAll();
    }
}
