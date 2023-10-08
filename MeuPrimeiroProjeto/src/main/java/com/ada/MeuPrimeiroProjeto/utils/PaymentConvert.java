package com.ada.MeuPrimeiroProjeto.utils;

import com.ada.MeuPrimeiroProjeto.controller.dto.PaymentRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.PaymentResponse;
import com.ada.MeuPrimeiroProjeto.model.Payment;
import com.ada.MeuPrimeiroProjeto.model.TypePayment;

import java.util.ArrayList;
import java.util.List;

public class PaymentConvert {

    public static Payment toEntity(PaymentRequest paymentRequest, TypePayment typePayment){
        Payment payment = new Payment();
        payment.setName(paymentRequest.getName());
        payment.setPrice(paymentRequest.getPrice());
        payment.setType(typePayment);
        return payment;
    }

    public static PaymentResponse toResponse(Payment payment){
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setName(payment.getName());
        paymentResponse.setPrice(payment.getPrice());
        paymentResponse.setType(TypePaymentConvert.toResponse(payment.getType()));
        return paymentResponse;
    }

    public static List<PaymentResponse> toResponseList(List<Payment> payments){
        List<PaymentResponse> paymentResponses = new ArrayList<>();
        for(Payment payment: payments){
            paymentResponses.add(toResponse(payment));
        }
        return paymentResponses;
    }
}