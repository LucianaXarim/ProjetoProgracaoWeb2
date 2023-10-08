package com.ada.MeuPrimeiroProjeto.service;

import com.ada.MeuPrimeiroProjeto.controller.dto.TypePaymentRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.TypePaymentResponse;
import com.ada.MeuPrimeiroProjeto.model.TypePayment;
import com.ada.MeuPrimeiroProjeto.repository.TypePaymentRepository;
import com.ada.MeuPrimeiroProjeto.utils.TypePaymentConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePaymentService {
    @Autowired
    TypePaymentRepository typePaymentRepository;

    public List<TypePaymentResponse> getAllTypePayments(){
        return TypePaymentConvert.toResponseList(typePaymentRepository.findAll());
    }

    public TypePaymentResponse saveTypePayment(TypePaymentRequest typePaymentRequest){
        TypePayment typePayment = typePaymentRepository.save(TypePaymentConvert.toEntity(typePaymentRequest));
        return TypePaymentConvert.toResponse(typePayment);
    }

    public void deleteTypePayment(Integer id){
        typePaymentRepository.deleteById(id);
    }
}
