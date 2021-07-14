package com.miu.se.order;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderItem;
import com.miu.se.common.entity.OrderStatus;
import com.miu.se.common.entity.Product;
import com.miu.se.common.interfacing.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultOrderService implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderById(final Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
    @Override
    public List<Product> getTopSellingProduct(int year, int k) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == OrderStatus.CONFIRMED)
                .filter(o -> o.getCreatedAt().getYear() + 1900 == year)
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProduct, Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet().stream()
                .sorted(Comparator.comparing(kv -> kv.getValue(), Comparator.reverseOrder()))
                .limit(k)
                .map(kv -> kv.getKey())
                .collect(Collectors.toList());
    }
    @Override
    public List<String> getPopularPaymentGateway(int year, int k) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == OrderStatus.CONFIRMED)
                .filter(o -> o.getCreatedAt().getYear() + 1900 == year)
                .collect(Collectors.groupingBy(o -> o.getTransaction().getPaymentGateway(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(kv -> kv.getValue(), Comparator.reverseOrder()))
                .limit(k)
                .map(kv -> kv.getKey())
                .collect(Collectors.toList());

    }
    public List<Order> getListOrderofProduct(int year, int product_id) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().getYear() + 1900 == year)
                .filter(o -> o.getItems().stream().anyMatch(i -> i.getProduct().getId() == product_id))
                .collect(Collectors.toList());
    }
}
