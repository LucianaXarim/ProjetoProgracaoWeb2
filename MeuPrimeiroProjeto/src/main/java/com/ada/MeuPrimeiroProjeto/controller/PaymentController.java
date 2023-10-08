package com.ada.MeuPrimeiroProjeto.controller;

import com.ada.MeuPrimeiroProjeto.controller.dto.PaymentRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.PaymentResponse;
import com.ada.MeuPrimeiroProjeto.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllProduct(
            @RequestParam(name = "typePayment", required = false) Integer typePayment
    ){
        return ResponseEntity.ok(paymentService.getAllPayment(typePayment));
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> savePayment(@RequestBody PaymentRequest paymentRequest){
        PaymentResponse paymentResponse = paymentService.savePayment(paymentRequest);
        return ResponseEntity.created(URI.create("/payment/"+paymentResponse.getId())).body(paymentResponse);
    }
}

