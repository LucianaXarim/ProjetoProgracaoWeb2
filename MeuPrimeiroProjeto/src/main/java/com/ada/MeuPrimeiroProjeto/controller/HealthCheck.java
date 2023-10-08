package com.ada.MeuPrimeiroProjeto.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheck {
    @RequestMapping("/")
    public String heathCheck(){
                return "Apllication Running Lu 2023.10.08 as 11:01h!!";
    }
}
