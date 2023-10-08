package com.ada.MeuPrimeiroProjeto.controller.dto;

import lombok.Getter;

@Getter
public class PaymentRequest {
    private String name;
    private Double price;
    private Integer typeId;

}
