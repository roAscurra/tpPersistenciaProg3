package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio,Long> {
}
