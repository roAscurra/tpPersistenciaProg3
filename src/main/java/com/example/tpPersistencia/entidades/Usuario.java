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
@Table(name = "usuario")
public class Usuario extends BaseEntidad{
    private String nombre;
    private String password;
    private String rol;
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "usuario_id")
    @Builder.Default
    private List<Pedido> pedidosUsuario = new ArrayList<>();

    public void agregarPedidosUsuario(Pedido pdo){
        pedidosUsuario.add(pdo);
    }
}
