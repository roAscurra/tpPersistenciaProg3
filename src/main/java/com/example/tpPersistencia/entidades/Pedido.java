package com.example.tpPersistencia.entidades;

import com.example.tpPersistencia.util.Estado;
import com.example.tpPersistencia.util.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pedido")
public class Pedido  extends BaseEntidad{
    private String fecha;
    private Estado estado;
    private LocalDateTime horaEstimadaEntrega;
    private TipoEnvio tipoEnvio;
    private Double total;

    //DetallePedido
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> pedidos = new ArrayList<>();
    //Factura
    @OneToOne( cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    public void agregarDetallePedido(DetallePedido dp){
        pedidos.add(dp);
    }
}
