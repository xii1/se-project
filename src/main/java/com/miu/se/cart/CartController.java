package com.miu.se.cart;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.Transaction;
import com.miu.se.common.interfacing.CartService;
import com.miu.se.common.request.ProcessPaymentRequest;
import com.miu.se.common.request.SaveOrderTransactionRequest;
import com.miu.se.common.response.ProcessPaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /** After user press Checkout, BE saves order information, and then return a payment gateway url to FE.
     * @param request includes user id, and shipping address.
     * @return url.
     * @throws Exception
     */
    @PostMapping(path = "/proceed_payment")
    public ProcessPaymentResponse proceedPayment(@RequestBody ProcessPaymentRequest request) throws Exception {
        final Long userId = request.getUserId();
        final ShippingAddress shippingAddress = request.getShippingAddress();
        if (userId == null) {
            throw new Exception("User is required");
        }
        if (shippingAddress == null) {
            throw new Exception("Shipping address is required");
        }
        return cartService.proceedPayment(userId, shippingAddress);
    }

    /**
     * After completing payment, FE sends an transaction to server
     * @param request request.
     * @return response.
     * @throws Exception exception.
     */
    @PostMapping(path = "/save_order_transaction")
    public Order saveOrderTransaction(@RequestBody SaveOrderTransactionRequest request) throws Exception {
        Long orderId = request.getOrderId();
        Transaction transaction = request.getTransaction();
        if (orderId == null) {
            throw new Exception("User is required");
        }
        if (transaction == null) {
            throw new Exception("Transaction is required");
        }
        return cartService.saveOrderTransaction(orderId, transaction);
    }
}
