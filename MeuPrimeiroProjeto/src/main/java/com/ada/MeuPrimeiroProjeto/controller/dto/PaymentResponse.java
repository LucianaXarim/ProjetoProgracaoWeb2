package com.ada.MeuPrimeiroProjeto.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentResponse {
    private Integer id;
    private String name;
    private Double price;
    private TypePaymentResponse type;
}
