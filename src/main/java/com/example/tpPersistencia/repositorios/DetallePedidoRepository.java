package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
