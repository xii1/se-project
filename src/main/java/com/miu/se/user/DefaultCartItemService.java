package com.miu.se.user;


import com.miu.se.common.entity.CartItem;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.User;
import com.miu.se.common.interfacing.CartItemService;
import com.miu.se.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DefaultCartItemService implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<CartItem> getAllItem(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public CartItem addCartItem(long user, long product, int quantity) {

        CartItem cartItem = new CartItem();
        User u = userRepository.findById(user).get();
        Product p= productRepository.findById(product).get();
        cartItem.setProduct(p);
        cartItem.setUser(u);
        cartItem.setQuantity(quantity);
        cartItem.setCreatedDate(new Date());
        cartItem.setUpdatedDate(new Date());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public boolean deleteCartItem(CartItem item) {
        cartItemRepository.delete(item);
        return false;
    }
}
