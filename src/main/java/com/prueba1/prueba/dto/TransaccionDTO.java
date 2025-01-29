package com.prueba1.prueba.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransaccionDTO {
    private Long id;
    private String tipoTransaccion;
    private double monto;
    private LocalDateTime fechaTransaccion;
    private Long productoId; // Para vincular la transacción a un producto
}