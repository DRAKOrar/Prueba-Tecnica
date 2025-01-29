package com.prueba1.prueba.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductoDTO {
    private Long id;
    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private double saldo;
    private boolean exentaGMF;
    private LocalDate fechaCreacion;
    private Long clienteId;
}