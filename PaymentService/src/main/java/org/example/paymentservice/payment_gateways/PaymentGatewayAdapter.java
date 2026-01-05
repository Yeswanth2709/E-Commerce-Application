package org.example.paymentservice.payment_gateways;


public interface PaymentGatewayAdapter {
    public String createPaymentLink(long orderId, long amount) throws Exception;
}
