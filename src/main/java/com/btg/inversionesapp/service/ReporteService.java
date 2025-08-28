package com.btg.inversionesapp.service;

import com.btg.inversionesapp.dto.FondoEstadoDTO;
import com.btg.inversionesapp.dto.FondosPorClienteResponse;
import com.btg.inversionesapp.model.Cliente;
import com.btg.inversionesapp.model.Fondo;
import com.btg.inversionesapp.model.Transaccion;
import com.btg.inversionesapp.repository.ClienteRepository;
import com.btg.inversionesapp.repository.FondoRepository;
import com.btg.inversionesapp.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    private final ClienteRepository clienteRepo;
    private final FondoRepository fondoRepo;
    private final TransaccionRepository transaccionRepo;

    public ReporteService(ClienteRepository clienteRepo, FondoRepository fondoRepo, TransaccionRepository transaccionRepo) {
        this.clienteRepo = clienteRepo;
        this.fondoRepo = fondoRepo;
        this.transaccionRepo = transaccionRepo;
    }

    public FondosPorClienteResponse fondosPorCedula(String cedula) {
        Cliente cliente = clienteRepo.findByCedula(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado por cédula"));

        List<Fondo> fondos = fondoRepo.findAll();
        List<FondoEstadoDTO> resultado = new ArrayList<>();

        for (Fondo f : fondos) {
            List<Transaccion> historial = transaccionRepo.findByClienteIdAndFondoId(cliente.getId(), f.getId());
            BigDecimal aperturas = historial.stream()
                    .filter(t -> t.getTipo() == Transaccion.Tipo.APERTURA)
                    .map(Transaccion::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal cancelaciones = historial.stream()
                    .filter(t -> t.getTipo() == Transaccion.Tipo.CANCELACION)
                    .map(Transaccion::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal neto = aperturas.subtract(cancelaciones);

            boolean suscrito = neto.compareTo(BigDecimal.ZERO) > 0;

            resultado.add(new FondoEstadoDTO(
                    f.getId(),
                    f.getNombre(),
                    f.getCategoria() != null ? f.getCategoria().name() : null,
                    f.getMontoMinimo(),
                    suscrito,
                    neto
            ));
        }

        return new FondosPorClienteResponse(
                cliente.getId(),
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getSaldo(),
                resultado
        );
    }
}
