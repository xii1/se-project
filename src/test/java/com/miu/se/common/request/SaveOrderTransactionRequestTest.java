package com.miu.se.common.request;

import com.miu.se.common.entity.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author duong
 */
public class SaveOrderTransactionRequestTest {
    @Test
    void testValues() {
        SaveOrderTransactionRequest request = new SaveOrderTransactionRequest();
        request.setUserId((long)1234);
        request.setOrderId((long)1234);
        Transaction transaction = new Transaction();
        request.setTransaction(transaction);

        Assertions.assertEquals(request.getUserId().longValue(), (long)1234);
        Assertions.assertEquals(request.getUserId().longValue(), (long)1234);
        Assertions.assertNotNull(request.getTransaction());
    }
}
