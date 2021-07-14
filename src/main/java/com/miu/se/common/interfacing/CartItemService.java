package com.miu.se.common.interfacing;

import com.miu.se.common.entity.CartItem;

import java.util.List;

public interface CartItemService {



    public List<CartItem> getAllItem(Long userId);
    public CartItem addCartItem(long userId, long productId, int quantity);
    boolean deleteCartItem(CartItem item);
}
