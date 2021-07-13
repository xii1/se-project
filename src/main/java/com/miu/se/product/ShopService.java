package com.miu.se.product;

import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Shop;
import com.miu.se.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Shop save(Shop shop) {
        Long Id = 0L;
        shop.setUser(new User(Id));
        return shopRepository.save(shop);
    }

    public Product save(Product product, Long shopId, Long categoryId) {
        Product result = productRepository.save(product);
        Shop shop = shopRepository.getOne(shopId);
        Category category = categoryRepository.getOne(categoryId);
        category.getProducts().add(result);
        shop.getProducts().add(result);
        categoryRepository.save(category);
        shopRepository.save(shop);
        return result;
    }

    public Shop getOne(Long id) {
        return shopRepository.getOne(id);
    }
}
