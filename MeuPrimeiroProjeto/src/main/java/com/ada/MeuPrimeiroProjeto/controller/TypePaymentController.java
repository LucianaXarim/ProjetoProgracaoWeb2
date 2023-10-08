package com.ada.MeuPrimeiroProjeto.controller;

import com.ada.MeuPrimeiroProjeto.controller.dto.TypePaymentRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.TypePaymentResponse;
import com.ada.MeuPrimeiroProjeto.service.TypePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/type-payment")
public class TypePaymentController {
    @Autowired
    TypePaymentService typePaymentService;

    @GetMapping
    public ResponseEntity<List<TypePaymentResponse>> gettAllTypePayment(){
        return ResponseEntity.ok(typePaymentService.getAllTypePayments());
    }

    @PostMapping
    public ResponseEntity<TypePaymentResponse> saveTypePayment(@RequestBody TypePaymentRequest typePaymentRequest){
        TypePaymentResponse typePaymentResponse = typePaymentService.saveTypePayment(typePaymentRequest);
        return ResponseEntity.created(
                URI.create("/type-payment/"+typePaymentResponse.getId())
        ).body(typePaymentResponse);
    }

    @DeleteMapping("/{id}")
    private void deleteTypePayment(@PathVariable Integer id){
        typePaymentService.deleteTypePayment(id);
    }
}

