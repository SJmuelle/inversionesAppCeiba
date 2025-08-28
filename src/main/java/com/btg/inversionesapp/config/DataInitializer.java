package com.btg.inversionesapp.config;

import com.btg.inversionesapp.model.Fondo;
import com.btg.inversionesapp.repository.FondoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner seedFondos(FondoRepository repo) {
        return args -> {
            List<Fondo> base = Arrays.asList(
                make("FPV_BTG_PACTUAL_RECAUDADORA", new BigDecimal("75000"), Fondo.Categoria.FPV),
                make("FPV_BTG_PACTUAL_ECOPETROL",   new BigDecimal("125000"), Fondo.Categoria.FPV),
                make("DEUDAPRIVADA",                new BigDecimal("50000"), Fondo.Categoria.FIC),
                make("FDO-ACCIONES",                new BigDecimal("250000"), Fondo.Categoria.FIC),
                make("FPV_BTG_PACTUAL_DINAMICA",    new BigDecimal("100000"), Fondo.Categoria.FPV)
            );

            for (Fondo f : base) {
                repo.findByNombre(f.getNombre()).orElseGet(() -> {
                    log.info("Insertando fondo inicial: {} ({} - ${})", f.getNombre(), f.getCategoria(), f.getMontoMinimo());
                    return repo.save(f);
                });
            }
        };
    }

    private static Fondo make(String nombre, java.math.BigDecimal monto, Fondo.Categoria categoria) {
        Fondo f = new Fondo();
        f.setNombre(nombre);
        f.setMontoMinimo(monto);
        f.setCategoria(categoria);
        return f;
    }
}
