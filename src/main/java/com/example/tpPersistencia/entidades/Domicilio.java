package com.example.tpPersistencia.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "domicilio")
public class Domicilio extends BaseEntidad{
    private String calle;
    private String numero;
    private String localidad;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "domicilios_id")
    @Builder.Default
    private List<Pedido> pedidosDomicilio = new ArrayList<>();

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "cliente_id")
    private Cliente cliente;
    public void agregarPedidosDomicilio(Pedido ped){
        pedidosDomicilio.add(ped);
    }
}
