package com.miu.se.common.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duong
 */
public class ProductTest {

    @Test
    void testValues() {
        Product product = new Product();
        product.setId((long)1234);
        Category category = new Category();
        category.setName("Beverage");
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        product.setCategories(categories);
        product.setAvailableQuantity(10);
        product.setUnitPrice(100.0);
        product.setName("Wine");

        Assertions.assertEquals(product.getId(), (long)1234);
        Assertions.assertEquals(product.getCategories().size(), 1);
        Assertions.assertEquals(product.getName(), "Wine");
        Assertions.assertEquals(product.getAvailableQuantity(), 10);
        Assertions.assertEquals(product.getUnitPrice(), 100.0);
        Assertions.assertNull(product.getCreatedBy());
        Assertions.assertNull(product.getResources());
        Assertions.assertNull(product.getReviews());
    }
}
