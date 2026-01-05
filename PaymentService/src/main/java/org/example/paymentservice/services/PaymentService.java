package org.example.paymentservice.services;

public interface PaymentService {
    String createPaymentLink(long orderId, long amount) throws Exception;
}
