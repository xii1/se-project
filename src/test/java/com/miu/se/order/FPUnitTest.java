package com.miu.se.order;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.Product;
import com.miu.se.common.interfacing.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FPUnitTest {
    private Order order;
    private Order order2;
    private Product product;
    private Product product2;

    @Autowired
    OrderService orderService;


//    @Test
//    public void testGetTopSellingProduct() {
//        List<Product> products = orderService.getTopSellingProduct(2021,1);
//        Assertions.assertEquals(0, products.size());
//        //Assertions.assertEquals(products.get(0), product);
//    }

//    @Test
//    public void getPopularPaymentGateway() {
//
//    }
//
//    @Test
//    public void getListOrderofProduct() {
//
//    }
}
