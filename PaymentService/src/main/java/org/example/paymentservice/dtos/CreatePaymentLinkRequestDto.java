package org.example.paymentservice.dtos;

import lombok.Data;

@Data
public class CreatePaymentLinkRequestDto {
    Long orderId;
    Long amount;
}
