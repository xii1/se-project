package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.Product;

import java.util.List;

public interface OrderService {
    public Order save(Order order);
    public Order getOrderById(final Long id);

    public List<Order> getAll();
    public List<Product> getTopSellingProduct(int year, int k);
    public List<String> getPopularPaymentGateway(int year, int k);
    public List<Order> getListOrderofProduct(int year, int product_id);
}
