package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
}
