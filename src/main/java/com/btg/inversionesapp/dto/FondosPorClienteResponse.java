package com.btg.inversionesapp.dto;

import java.math.BigDecimal;
import java.util.List;

public class FondosPorClienteResponse {
    private String clienteId;
    private String cedula;
    private String nombre;
    private BigDecimal saldo;
    private List<FondoEstadoDTO> fondos;

    public FondosPorClienteResponse() {}

    public FondosPorClienteResponse(String clienteId, String cedula, String nombre, BigDecimal saldo, List<FondoEstadoDTO> fondos) {
        this.clienteId = clienteId;
        this.cedula = cedula;
        this.nombre = nombre;
        this.saldo = saldo;
        this.fondos = fondos;
    }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public List<FondoEstadoDTO> getFondos() { return fondos; }
    public void setFondos(List<FondoEstadoDTO> fondos) { this.fondos = fondos; }
}
