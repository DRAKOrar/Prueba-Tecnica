package com.prueba1.prueba.repository;

import com.prueba1.prueba.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    boolean existsByNumeroCuenta ( String numeroCuenta);
}
