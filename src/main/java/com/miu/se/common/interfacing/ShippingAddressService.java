package com.miu.se.common.interfacing;

import com.miu.se.common.entity.ShippingAddress;

import java.util.List;

public interface ShippingAddressService {
    ShippingAddress getShippingAddressById(final Long id);

    ShippingAddress save(final ShippingAddress shippingAddress);

    public List<ShippingAddress> getAll();
}
