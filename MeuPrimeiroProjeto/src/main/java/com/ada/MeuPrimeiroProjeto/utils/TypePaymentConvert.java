package com.ada.MeuPrimeiroProjeto.utils;

import com.ada.MeuPrimeiroProjeto.controller.dto.TypePaymentRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.TypePaymentResponse;
import com.ada.MeuPrimeiroProjeto.model.TypePayment;

import java.util.ArrayList;
import java.util.List;

public class TypePaymentConvert {

    public static TypePayment toEntity(TypePaymentRequest typePaymentRequest){
        TypePayment typePayment = new TypePayment();
        typePayment.setName(typePaymentRequest.getName());

        return typePayment;
    }

    public static TypePaymentResponse toResponse(TypePayment typePayment){
        TypePaymentResponse typePaymentResponse = new TypePaymentResponse();
        typePaymentResponse.setId(typePayment.getId());
        typePaymentResponse.setName(typePayment.getName());

        return typePaymentResponse;
    }

    public static List<TypePaymentResponse> toResponseList(List<TypePayment> typesPayments){
        List<TypePaymentResponse> typesPaymentResponses = new ArrayList<>();
        for(TypePayment typePayment: typesPayments){
            typesPaymentResponses.add(toResponse(typePayment));
        }
        return typesPaymentResponses;
    }

}

