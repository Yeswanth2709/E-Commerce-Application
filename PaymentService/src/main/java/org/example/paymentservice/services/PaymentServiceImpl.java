package org.example.paymentservice.services;

import org.example.paymentservice.payment_gateways.PaymentGatewayAdapter;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentGatewayAdapter paymentGatewayAdapter;

    public PaymentServiceImpl(PaymentGatewayAdapter paymentGatewayAdapter) {
        this.paymentGatewayAdapter = paymentGatewayAdapter;
    }

    @Override
    public String createPaymentLink(long orderId, long amount) throws Exception {
        return paymentGatewayAdapter.createPaymentLink(orderId, amount);
    }
}
