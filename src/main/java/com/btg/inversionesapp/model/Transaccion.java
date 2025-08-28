package com.btg.inversionesapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "transacciones")
public class Transaccion {
    @Id
    private String id = UUID.randomUUID().toString();
    private String clienteId;
    private String fondoId;
    private Tipo tipo;
    private BigDecimal monto;
    private LocalDateTime fecha = LocalDateTime.now();

    public enum Tipo { APERTURA, CANCELACION }

    public Transaccion() {}

    public Transaccion(String id, String clienteId, String fondoId, Tipo tipo, BigDecimal monto, LocalDateTime fecha) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.clienteId = clienteId;
        this.fondoId = fondoId;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha != null ? fecha : LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getFondoId() { return fondoId; }
    public void setFondoId(String fondoId) { this.fondoId = fondoId; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
