package com.combustisv.config;

import com.combustisv.model.*;
import com.combustisv.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private GasolineraRepository gasolineraRepo;
    @Autowired private TipoCombustibleRepository tipoRepo;
    @Autowired private PrecioRepository precioRepo;

    @Override
    public void run(String... args) {
        if (tipoRepo.count() == 0) {
            TipoCombustible regular = tipoRepo.save(new TipoCombustible("Regular", "Gasolina regular 87 octanos"));
            TipoCombustible premium = tipoRepo.save(new TipoCombustible("Premium", "Gasolina premium 95 octanos"));
            TipoCombustible diesel = tipoRepo.save(new TipoCombustible("Diesel", "Diesel automotriz"));

            Gasolinera g1 = new Gasolinera("Puma San Salvador Centro", "Blvd. Los Heroes, San Salvador", "Puma");
            g1.setDepartamento("San Salvador"); g1.setMunicipio("San Salvador");
            g1 = gasolineraRepo.save(g1);

            Gasolinera g2 = new Gasolinera("Shell Escalon", "Blvd. Escalon #3500, San Salvador", "Shell");
            g2.setDepartamento("San Salvador"); g2.setMunicipio("San Salvador");
            g2 = gasolineraRepo.save(g2);

            Gasolinera g3 = new Gasolinera("Texaco Santa Tecla", "Carretera Panamericana, Santa Tecla", "Texaco");
            g3.setDepartamento("La Libertad"); g3.setMunicipio("Santa Tecla");
            g3 = gasolineraRepo.save(g3);

            Gasolinera g4 = new Gasolinera("Uno Soyapango", "Blvd. del Ejercito, Soyapango", "Uno");
            g4.setDepartamento("San Salvador"); g4.setMunicipio("Soyapango");
            g4 = gasolineraRepo.save(g4);

            Gasolinera g5 = new Gasolinera("Alba Petr. Mejicanos", "Calle Principal, Mejicanos", "Alba");
            g5.setDepartamento("San Salvador"); g5.setMunicipio("Mejicanos");
            g5 = gasolineraRepo.save(g5);

            precioRepo.save(new Precio(g1, regular, new BigDecimal("4.25")));
            precioRepo.save(new Precio(g1, premium, new BigDecimal("4.55")));
            precioRepo.save(new Precio(g1, diesel,  new BigDecimal("3.98")));
            precioRepo.save(new Precio(g2, regular, new BigDecimal("4.30")));
            precioRepo.save(new Precio(g2, premium, new BigDecimal("4.60")));
            precioRepo.save(new Precio(g2, diesel,  new BigDecimal("4.05")));
            precioRepo.save(new Precio(g3, regular, new BigDecimal("4.28")));
            precioRepo.save(new Precio(g3, premium, new BigDecimal("4.58")));
            precioRepo.save(new Precio(g3, diesel,  new BigDecimal("4.00")));
            precioRepo.save(new Precio(g4, regular, new BigDecimal("4.22")));
            precioRepo.save(new Precio(g4, premium, new BigDecimal("4.50")));
            precioRepo.save(new Precio(g4, diesel,  new BigDecimal("3.95")));
            precioRepo.save(new Precio(g5, regular, new BigDecimal("4.18")));
            precioRepo.save(new Precio(g5, premium, new BigDecimal("4.48")));
            precioRepo.save(new Precio(g5, diesel,  new BigDecimal("3.90")));

            System.out.println(">>> Datos de prueba cargados exitosamente <<<");
        }
    }
}
