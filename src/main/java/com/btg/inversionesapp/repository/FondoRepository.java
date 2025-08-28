package com.btg.inversionesapp.repository;

import com.btg.inversionesapp.model.Fondo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FondoRepository extends MongoRepository<Fondo, String> {
    Optional<Fondo> findByNombre(String nombre);
}
