package com.btg.inversionesapp.repository;

import com.btg.inversionesapp.model.Transaccion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    List<Transaccion> findByClienteIdOrderByFechaDesc(String clienteId, Pageable pageable);
    List<Transaccion> findByClienteIdAndFondoId(String clienteId, String fondoId);
}
