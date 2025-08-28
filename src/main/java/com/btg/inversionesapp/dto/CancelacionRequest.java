package com.btg.inversionesapp.dto;

import jakarta.validation.constraints.*;

public class CancelacionRequest {
    @NotBlank private String clienteId;
    @NotBlank private String fondoId;
    @NotBlank @Pattern(regexp = "EMAIL|SMS", flags = Pattern.Flag.CASE_INSENSITIVE) private String tipoNotificacion;

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getFondoId() { return fondoId; }
    public void setFondoId(String fondoId) { this.fondoId = fondoId; }
    public String getTipoNotificacion() { return tipoNotificacion; }
    public void setTipoNotificacion(String tipoNotificacion) { this.tipoNotificacion = tipoNotificacion; }
}
