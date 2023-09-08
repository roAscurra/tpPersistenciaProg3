package com.example.tpPersistencia.entidades;
import com.example.tpPersistencia.util.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Factura")
public class Factura extends BaseEntidad{
    private Date fecha;
    private int numero;
    private Double descuento;
    private FormaPago formaPago;
    private int total;
}
