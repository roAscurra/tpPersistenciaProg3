package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
