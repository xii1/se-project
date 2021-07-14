package com.miu.se.order;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.Product;
import com.miu.se.common.interfacing.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
        //orderService.getTopSellingProduct();
    }

    @GetMapping(value = "/top-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getTopSellingProduct(@RequestParam int year, @RequestParam int k) {
        return orderService.getTopSellingProduct(year, k);
    }

    @GetMapping(value = "/top-payment-gateway", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getPopularPaymentGateway(@RequestParam int year, @RequestParam int k) {
        return orderService.getPopularPaymentGateway(year, k);
    }

    @GetMapping(value = "/list-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getListOrderofProduct(@RequestParam int year, @RequestParam int product_id) {
        return orderService.getListOrderofProduct(year, product_id);
    }

}
