package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
