package com.ada.MeuPrimeiroProjeto.controller;

import com.ada.MeuPrimeiroProjeto.controller.dto.OrderRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.OrderResponse;
import com.ada.MeuPrimeiroProjeto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.saveOrder(orderRequest);
        return ResponseEntity.created(URI.create("/order/"+orderResponse.getId())).body(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrder(
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "productId", required = false) Integer productId
    ){
        return ResponseEntity.ok(orderService.getAllOrders(userId, productId));
    }


//O bloco acima foi simplicado pela chamada imediatamente acima + add da chamada na orderService
// O objetivo foi realizar chamadas genericas dom ?users por exemplo direto na URL do postmana
//    @GetMapping("/order")
//  public List<OrderResponse> getAllOrders(){
    //      return orderService.getAllOrders();
//  }
//  @GetMapping("/order/user/{userId}")
    //  public List<OrderResponse> getAllByUser(@PathVariable Integer userId){
    //      return orderService.getAllByUser(userId);
    //  }

    //  @GetMapping("/order/product/{productId}")
    //  public List<OrderResponse> getAllByProduct(@PathVariable Integer productId){
    //      return orderService.getAllByProduct(productId);
    //}

}
