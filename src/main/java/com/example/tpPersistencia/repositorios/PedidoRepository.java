package com.example.tpPersistencia.repositorios;

import com.example.tpPersistencia.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
