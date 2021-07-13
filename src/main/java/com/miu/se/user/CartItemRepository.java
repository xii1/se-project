package com.miu.se.user;

import com.miu.se.common.entity.CartItem;
import com.miu.se.common.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findByUserId(Long userId);
}
