package com.example.tpPersistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Cliente")
public class Cliente extends BaseEntidad {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn( name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidosCliente = new ArrayList<>();
    public void agregarPedidosCliente(Pedido pedido){
        pedidosCliente.add(pedido);
    }

}
