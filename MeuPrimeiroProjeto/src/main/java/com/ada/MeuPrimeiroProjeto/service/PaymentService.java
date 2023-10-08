package com.ada.MeuPrimeiroProjeto.service;

import com.ada.MeuPrimeiroProjeto.controller.dto.PaymentRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.PaymentResponse;

import com.ada.MeuPrimeiroProjeto.model.Payment;
import com.ada.MeuPrimeiroProjeto.model.TypePayment;
import com.ada.MeuPrimeiroProjeto.repository.PaymentRepository;
import com.ada.MeuPrimeiroProjeto.repository.TypePaymentRepository;
import com.ada.MeuPrimeiroProjeto.utils.PaymentConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    TypePaymentRepository typePaymentRepository;

    public PaymentResponse savePayment(PaymentRequest paymentRequest){
        TypePayment typePayment = typePaymentRepository.findById(paymentRequest.getTypeId()).get();
        Payment payment = PaymentConvert.toEntity(paymentRequest, typePayment);
        return PaymentConvert.toResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> getAllPayment(Integer typePayment){
        if(typePayment != null){
            return getAllByTypePayment(typePayment);
        }
        return PaymentConvert.toResponseList((paymentRepository.findAll()));
    }

    public List<PaymentResponse>getAllByTypePayment(Integer typePayment){
        return PaymentConvert.toResponseList(paymentRepository.findPaymentByType(typePayment));
    }

}
