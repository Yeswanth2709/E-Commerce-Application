package org.example.paymentservice.controllers;

import org.example.paymentservice.dtos.CreatePaymentLinkRequestDto;
import org.example.paymentservice.services.PaymentService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createPaymentLink(@RequestBody CreatePaymentLinkRequestDto requestDto) {
        try {
            String url = paymentService.createPaymentLink(requestDto.getOrderId(), requestDto.getAmount());
            return new ResponseEntity<>(url, HttpStatusCode.valueOf(200));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
