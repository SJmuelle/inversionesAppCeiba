package com.btg.inversionesapp.service;

import com.btg.inversionesapp.dto.CancelacionRequest;
import com.btg.inversionesapp.dto.SuscripcionRequest;
import com.btg.inversionesapp.model.Cliente;
import com.btg.inversionesapp.model.Fondo;
import com.btg.inversionesapp.model.Transaccion;
import com.btg.inversionesapp.repository.ClienteRepository;
import com.btg.inversionesapp.repository.FondoRepository;
import com.btg.inversionesapp.repository.TransaccionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OperacionFondosService {

    private final ClienteRepository clienteRepo;
    private final FondoRepository fondoRepo;
    private final TransaccionRepository transaccionRepo;
    private final NotificacionService notificacionService;

    public OperacionFondosService(ClienteRepository clienteRepo,
                                  FondoRepository fondoRepo,
                                  TransaccionRepository transaccionRepo,
                                  NotificacionService notificacionService) {
        this.clienteRepo = clienteRepo;
        this.fondoRepo = fondoRepo;
        this.transaccionRepo = transaccionRepo;
        this.notificacionService = notificacionService;
    }

    @Transactional
    public Transaccion suscribirse(SuscripcionRequest req) {
        Cliente cliente = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Fondo fondo = fondoRepo.findById(req.getFondoId())
                .orElseGet(() -> fondoRepo.findByNombre(req.getFondoId())
                        .orElseThrow(() -> new IllegalArgumentException("Fondo no encontrado")));

        if (req.getMonto().compareTo(fondo.getMontoMinimo()) < 0) {
            throw new IllegalStateException("El monto mínimo para vincularse al fondo "
                    + fondo.getNombre() + " es COP $" + fondo.getMontoMinimo());
        }
        if (cliente.getSaldo().compareTo(req.getMonto()) < 0) {
            throw new IllegalStateException("No tiene saldo disponible para vincularse al fondo " + fondo.getNombre());
        }

        cliente.setSaldo(cliente.getSaldo().subtract(req.getMonto()));
        clienteRepo.save(cliente);

        Transaccion t = new Transaccion();
        t.setClienteId(cliente.getId());
        t.setFondoId(fondo.getId());
        t.setTipo(Transaccion.Tipo.APERTURA);
        t.setMonto(req.getMonto());
        t = transaccionRepo.save(t);

        notificacionService.enviar(cliente, "Suscripción a fondo",
                "Te has suscrito al fondo " + fondo.getNombre() + " por COP $" + req.getMonto(),
                req.getTipoNotificacion());
        return t;
    }

    @Transactional
    public Transaccion cancelar(CancelacionRequest req) {
        Cliente cliente = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Fondo fondo = fondoRepo.findById(req.getFondoId())
                .orElseGet(() -> fondoRepo.findByNombre(req.getFondoId())
                        .orElseThrow(() -> new IllegalArgumentException("Fondo no encontrado")));

        List<Transaccion> historial = transaccionRepo.findByClienteIdAndFondoId(cliente.getId(), fondo.getId());
        BigDecimal aperturas = historial.stream()
                .filter(t -> t.getTipo() == Transaccion.Tipo.APERTURA)
                .map(Transaccion::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal cancelaciones = historial.stream()
                .filter(t -> t.getTipo() == Transaccion.Tipo.CANCELACION)
                .map(Transaccion::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal neto = aperturas.subtract(cancelaciones);

        if (neto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("No hay saldo vinculado en el fondo " + fondo.getNombre() + " para cancelar.");
        }

        cliente.setSaldo(cliente.getSaldo().add(neto));
        clienteRepo.save(cliente);

        Transaccion t = new Transaccion();
        t.setClienteId(cliente.getId());
        t.setFondoId(fondo.getId());
        t.setTipo(Transaccion.Tipo.CANCELACION);
        t.setMonto(neto);
        t = transaccionRepo.save(t);

        notificacionService.enviar(cliente, "Cancelación de fondo",
                "Has cancelado tu vinculación al fondo " + fondo.getNombre() + " y se te ha devuelto COP $" + neto,
                req.getTipoNotificacion());
        return t;
    }

    public java.util.List<com.btg.inversionesapp.model.Transaccion> ultimasTransacciones(String clienteId, int limit) {
        return transaccionRepo.findByClienteIdOrderByFechaDesc(clienteId, PageRequest.of(0, Math.max(1, limit)));
    }
}
