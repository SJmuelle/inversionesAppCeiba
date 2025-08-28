package com.btg.inversionesapp.controller;

import com.btg.inversionesapp.dto.FondosPorClienteResponse;
import com.btg.inversionesapp.service.ReporteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping("/fondos-por-cliente")
    public FondosPorClienteResponse fondosPorCliente(@RequestParam String cedula) {
        return service.fondosPorCedula(cedula);
    }
}
