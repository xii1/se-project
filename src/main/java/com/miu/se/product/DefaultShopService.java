package com.miu.se.product;

import com.miu.se.common.entity.Category;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderStatus;
import com.miu.se.common.entity.Product;
import com.miu.se.common.entity.Shop;
import com.miu.se.common.util.TriFunction;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DefaultShopService {

    public TriFunction<Order, Integer, Integer, Boolean> orderInMonthOfYear = (order, month, year) -> (order.getStatus() == OrderStatus.CONFIRMED || order.getStatus() == OrderStatus.SHIPPED ) && order.getCreatedAt().getYear() == year && order.getCreatedAt().getMonth() == month;

    public TriFunction<Product, Integer, Integer, Double> getTotalProfitOfProductInMonth = (product, month, year) -> product.getOrderItems().stream()
            .filter(orderItem -> orderInMonthOfYear.apply(orderItem.getOrder(), month, year))
            .mapToDouble(orderItem -> orderItem.getProduct().getUnitPrice() * orderItem.getQuantity())
            .sum();

    public Function<Product, List<AbstractMap.SimpleEntry<Category, Product>>> mapProductToPairCategoryProduct = product -> product.getCategories().stream()
            .map(c -> new AbstractMap.SimpleEntry<>(c, product))
            .collect(Collectors.toList());

    public TriFunction<Product, Integer, Integer, AbstractMap.SimpleEntry<Product, Double>> pairProductAndProfit = (product, month, year) -> new AbstractMap.SimpleEntry<>(product, getTotalProfitOfProductInMonth.apply(product, month, year));

    public List<Category> getTopNProductPerCategoryOfYear(Shop shop, int month, int year, int top) {
        return shop.getProducts().stream()
                .flatMap(p -> mapProductToPairCategoryProduct.apply(p).stream())
                .collect(Collectors.groupingBy(kv -> kv.getKey().getId())).values().stream()
                .map(simpleEntries -> new AbstractMap.SimpleEntry<>(simpleEntries.get(0).getKey(), simpleEntries.stream().map(p -> pairProductAndProfit.apply(p.getValue(), month, year))))
                .map(kv -> new AbstractMap.SimpleEntry<>(kv.getKey(), kv.getValue()
                        .sorted(java.util.Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(top)
                        .map(AbstractMap.SimpleEntry::getKey)
                ))
                .map(kv -> {
                    kv.getKey().setProducts(kv.getValue().collect(Collectors.toList()));
                    return kv.getKey();
                }).collect(Collectors.toList());
    }

    public Double getProfit(Shop shop, int month, int year) {
        return shop.getProducts().stream()
                .mapToDouble(p -> getTotalProfitOfProductInMonth.apply(p, month, year))
                .sum();
    }

    public List<Product> getTopProducts(Shop shop, int month, int year, int top) {
        return shop.getProducts().stream()
                .map(p -> new AbstractMap.SimpleEntry<>(p, getTotalProfitOfProductInMonth.apply(p, month, year)))
                .sorted(java.util.Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(top)
                .map(AbstractMap.SimpleEntry::getKey)
                .collect(Collectors.toList());
    }
}
