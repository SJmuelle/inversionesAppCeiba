package com.btg.inversionesapp.controller;

import com.btg.inversionesapp.model.Cliente;
import com.btg.inversionesapp.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteRepository repo;

    public ClienteController(ClienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Cliente crear(@Valid @RequestBody Cliente c) {
        if (c.getSaldo() == null) {
            c.setSaldo(new java.math.BigDecimal("500000.00"));
        }
        return repo.save(c);
    }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable String id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }
}
