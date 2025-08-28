package com.btg.inversionesapp.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class SuscripcionRequest {
    @NotBlank private String clienteId;
    @NotBlank private String fondoId;
    @NotNull @Positive private BigDecimal monto;
    @NotBlank @Pattern(regexp = "EMAIL|SMS", flags = Pattern.Flag.CASE_INSENSITIVE) private String tipoNotificacion;

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getFondoId() { return fondoId; }
    public void setFondoId(String fondoId) { this.fondoId = fondoId; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getTipoNotificacion() { return tipoNotificacion; }
    public void setTipoNotificacion(String tipoNotificacion) { this.tipoNotificacion = tipoNotificacion; }
}
