package com.prueba1.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private double saldo;
    private boolean exentaGMF;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    @ManyToMany
    @JoinColumn(name = "cliente_Id")
    private Cliente cliente;
}
