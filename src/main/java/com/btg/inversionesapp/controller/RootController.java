package com.btg.inversionesapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public String home() {
        return "InversionesApp OK. Usa /api/fondos, /api/clientes, /api/reportes/...";
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
