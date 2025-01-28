package com.prueba1.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoTransaccion;
    private double monto;
    private LocalDateTime fechaTransaccion;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
