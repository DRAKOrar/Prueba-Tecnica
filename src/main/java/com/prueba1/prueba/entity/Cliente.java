package com.prueba1.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellido;
    private String correoElectronico;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto>productos;
}
