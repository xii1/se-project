package com.miu.se.common.dto;

import com.miu.se.common.entity.Shop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostShopDTO {
    private String name;

    public Shop toShop() {
        Shop shop = new Shop();
        shop.setName(this.name);
        return shop;
    }
}
