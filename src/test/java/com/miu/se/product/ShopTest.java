package com.miu.se.product;

import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderItem;
import com.miu.se.common.entity.OrderStatus;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShopTest {
    private Order order;
    private Product product;
    private Product product2;
    private Category category;
    private Shop shop;

    @BeforeAll
    public void setUp() {
        order = new Order();
        order.setStatus(OrderStatus.CONFIRMED);

        Calendar c1 = Calendar.getInstance();
        c1.set(2021, 5, 25);
        Date sDate = c1.getTime();
        order.setCreatedAt(sDate);

        category = new Category();
        category.setId(1L);

        product = new Product();
        product.setUnitPrice(200);

        product2 = new Product();
        product2.setUnitPrice(200);

        OrderItem item1 = new OrderItem();
        item1.setOrder(order);
        item1.setProduct(product);
        item1.setQuantity(2);

        OrderItem item2 = new OrderItem();
        item2.setOrder(order);
        item2.setProduct(product);
        item2.setQuantity(1);

        List<OrderItem> orderItems = new ArrayList<>() {{
            add(item1);
            add(item2);
        }};

        product.setOrderItems(orderItems);
        product2.setOrderItems(new ArrayList<>());

        category.setProducts(new ArrayList<>() {{
            add(product);
            add(product2);
        }});

        product.setCategories(new ArrayList<>() {{
            add(category);
        }});
        product2.setCategories(new ArrayList<>() {{
            add(category);
        }});

        shop = new Shop();
        shop.setProducts(new ArrayList<>() {{
            add(product);
            add(product2);
        }});

    }

    @Test
    public void testOrderInMonthOfYear(){
        DefaultShopService shopService = new DefaultShopService();
        Assertions.assertFalse(shopService.orderInMonthOfYear.apply(order, 5, 2021));
        Assertions.assertFalse(shopService.orderInMonthOfYear.apply(order, 6, 2021));
    }

    @Test
    public void testGetTotalProfitOfProductInMonth() {
        DefaultShopService shopService = new DefaultShopService();
        Assertions.assertEquals(0, shopService.getTotalProfitOfProductInMonth.apply(product, 5, 2021));
        Assertions.assertEquals(0, shopService.getTotalProfitOfProductInMonth.apply(product, 6, 2021));
    }

    @Test
    public void testMapProductToPairCategoryProduct() {
        DefaultShopService shopService = new DefaultShopService();
        List<AbstractMap.SimpleEntry<Category, Product>> list = shopService.mapProductToPairCategoryProduct.apply(product);
        Assertions.assertEquals(list.size(), 1);
        Assertions.assertEquals(list.get(0).getKey(), category);
        Assertions.assertEquals(list.get(0).getValue(), product);
    }

    @Test
    public void testPairProductAndProfit() {
        DefaultShopService shopService = new DefaultShopService();
        AbstractMap.SimpleEntry<Product, Double> pair = shopService.pairProductAndProfit.apply(product, 5, 2021);
        Assertions.assertEquals(0, pair.getValue());
        Assertions.assertEquals(product, pair.getKey());
    }

    @Test
    public void testGetProfit() {
        DefaultShopService shopService = new DefaultShopService();
        Assertions.assertEquals(0, shopService.getProfit(shop, 5, 2021));
    }

    @Test
    public void testGetTopProducts() {
        DefaultShopService shopService = new DefaultShopService();
        List<Product> products = shopService.getTopProducts(shop, 5, 2021, 5);
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals(product, products.get(0));
        Assertions.assertEquals(product2, products.get(1));
    }

    @Test
    public void testGetTopNProductPerCategoryOfYear() {
        DefaultShopService shopService = new DefaultShopService();
        List<Category> categories = shopService.getTopNProductPerCategoryOfYear(shop, 5, 2021, 5);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(category, categories.get(0));
        Assertions.assertEquals(product, categories.get(0).getProducts().get(0));
        Assertions.assertEquals(product2, categories.get(0).getProducts().get(1));
    }
}
