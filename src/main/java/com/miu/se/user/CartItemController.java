package com.miu.se.user;

import com.miu.se.common.entity.CartItem;
import com.miu.se.common.interfacing.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("CartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Operation(security = @SecurityRequirement(name = "auth"))
    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CartItem> getAllItem(@PathVariable Long userId) {
         List<CartItem> items =  cartItemService.getAllItem(userId);
         return ( List<CartItem>) Hibernate.unproxy(items);
    }

    @Operation(security = @SecurityRequirement(name = "auth"))
    @PostMapping(value = "/{userId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartItem addCartItem(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Integer quanity) {
        return  cartItemService.addCartItem(userId,productId,quanity);
    }

}
