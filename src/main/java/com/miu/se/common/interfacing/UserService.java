package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Role;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(final Long id);

    User save(final User user);

    List<User> getAll();

    void addRoles(Long userId, Role role);

    User getUserByEmail(String email);

    User createIfNotExist(User user);

    ShippingAddress getShippingAddrById(final Long id);

    void editShippingAddr(Long userId, Long shippingAddrId, ShippingAddress shippingAddress);

    boolean checkExists(String email);

    void clearCartItems(Long userId);
}
