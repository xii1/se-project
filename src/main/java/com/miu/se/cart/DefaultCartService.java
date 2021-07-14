package com.miu.se.cart;

import com.miu.se.common.entity.CartItem;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderItem;
import com.miu.se.common.entity.OrderStatus;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.Transaction;
import com.miu.se.common.interfacing.CartItemService;
import com.miu.se.common.interfacing.CartService;
import com.miu.se.common.interfacing.OrderService;
import com.miu.se.common.interfacing.PaymentGatewayService;
import com.miu.se.common.interfacing.ShippingAddressService;
import com.miu.se.common.interfacing.UserService;
import com.miu.se.common.response.ProcessPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCartService implements CartService {
    @Autowired
    PaymentGatewayService paymentGatewayService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ShippingAddressService shippingAddressService;

    @Override
    public ProcessPaymentResponse proceedPayment(Long userId, ShippingAddress shippingAddress) throws Exception {
        List<CartItem> cartItems = cartItemService.getAllItem(userId);
        if (cartItems.isEmpty()) {
            throw new Exception("Cart of user is empty");
        }
        // Create order item based on cart item.
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
           OrderItem orderItem = new OrderItem();
           orderItem.setProduct(cartItem.getProduct());
           orderItem.setQuantity(cartItem.getQuantity());
           //orderItem.setUnitPrice(cartItem.getProduct().getUnitPrice());
           return orderItem;
        }).collect(Collectors.toList());
        // Create shipping address
        ShippingAddress savedOrderAddress = shippingAddressService.save(shippingAddress);
        // Create order.
        Order order = new Order();
        order.setItems(orderItems);
        order.setStatus(OrderStatus.WAITFORPAYMENT);

        order.setCreatedAt(Calendar.getInstance().getTime());
        //order.setPaymentGateway("VNPay");
        order.setUpdatedAt(Calendar.getInstance().getTime());
        //order.setShippingAddress(savedOrderAddress);
        // Save order.
        order = orderService.save(order);
        // Get payment gateway url for front end.
        String paymentUrl = paymentGatewayService.getPaymentOrderUrl(order.getId(), order.calculateTotalCost());
        // Clear cart items
        userService.clearCartItems(userId);
        return new ProcessPaymentResponse(order.getId(), paymentUrl);
    }

    @Override
    public Order saveOrderTransaction(Long orderId, Transaction transaction) throws Exception {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            throw new Exception("Order is not exist");
        }
        // Validate transaction
        boolean isValid = paymentGatewayService.isValidTransaction(transaction);
        if (!isValid) {
            throw new Exception("Transaction is not valid");
        }
        // Save transaction.
        transaction.setOrder(order);
        order.setTransaction(transaction);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setUpdatedAt(Calendar.getInstance().getTime());
        // orderService.save(order);
        return null;
    }
}
