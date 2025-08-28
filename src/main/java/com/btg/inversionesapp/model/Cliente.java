package com.btg.inversionesapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "clientes")
public class Cliente {
    @Id
    private String id;
    @Indexed(unique = true)
    private String cedula;
    private String nombre;
    private String email;
    private String telefono;
    private BigDecimal saldo = new BigDecimal("500000.00");

    public Cliente() {}

    public Cliente(String id, String cedula, String nombre, String email, String telefono, BigDecimal saldo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo != null ? saldo : new BigDecimal("500000.00");
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
}
