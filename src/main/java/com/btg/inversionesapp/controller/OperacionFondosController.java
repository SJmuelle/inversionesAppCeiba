package com.btg.inversionesapp.controller;

import com.btg.inversionesapp.dto.CancelacionRequest;
import com.btg.inversionesapp.dto.SuscripcionRequest;
import com.btg.inversionesapp.model.Transaccion;
import com.btg.inversionesapp.service.OperacionFondosService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OperacionFondosController {

    private final OperacionFondosService service;

    public OperacionFondosController(OperacionFondosService service) {
        this.service = service;
    }

    @PostMapping("/suscripciones")
    public Transaccion suscribirse(@Valid @RequestBody SuscripcionRequest req) {
        return service.suscribirse(req);
    }

    @PostMapping("/cancelaciones")
    public Transaccion cancelar(@Valid @RequestBody CancelacionRequest req) {
        return service.cancelar(req);
    }

    @GetMapping("/transacciones/{clienteId}")
    public List<Transaccion> ultimas(@PathVariable String clienteId, @RequestParam(defaultValue = "10") int limit) {
        return service.ultimasTransacciones(clienteId, limit);
    }
}
