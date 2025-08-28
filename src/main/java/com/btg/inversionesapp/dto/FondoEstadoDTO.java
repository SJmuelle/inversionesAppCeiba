package com.btg.inversionesapp.dto;

import java.math.BigDecimal;

public class FondoEstadoDTO {
    private String fondoId;
    private String nombre;
    private String categoria;
    private BigDecimal montoMinimo;
    private boolean suscrito;
    private BigDecimal montoVinculado;

    public FondoEstadoDTO() {}

    public FondoEstadoDTO(String fondoId, String nombre, String categoria, BigDecimal montoMinimo, boolean suscrito, BigDecimal montoVinculado) {
        this.fondoId = fondoId;
        this.nombre = nombre;
        this.categoria = categoria;
        this.montoMinimo = montoMinimo;
        this.suscrito = suscrito;
        this.montoVinculado = montoVinculado;
    }

    public String getFondoId() { return fondoId; }
    public void setFondoId(String fondoId) { this.fondoId = fondoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getMontoMinimo() { return montoMinimo; }
    public void setMontoMinimo(BigDecimal montoMinimo) { this.montoMinimo = montoMinimo; }

    public boolean isSuscrito() { return suscrito; }
    public void setSuscrito(boolean suscrito) { this.suscrito = suscrito; }

    public BigDecimal getMontoVinculado() { return montoVinculado; }
    public void setMontoVinculado(BigDecimal montoVinculado) { this.montoVinculado = montoVinculado; }
}
