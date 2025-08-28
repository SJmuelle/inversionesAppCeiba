package com.btg.inversionesapp.controller;

import com.btg.inversionesapp.model.Fondo;
import com.btg.inversionesapp.repository.FondoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fondos")
@CrossOrigin(origins = "*")
public class FondoController {

    private final FondoRepository repo;

    public FondoController(FondoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Fondo> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Fondo crear(@Valid @RequestBody Fondo f) {
        return repo.save(f);
    }
}
