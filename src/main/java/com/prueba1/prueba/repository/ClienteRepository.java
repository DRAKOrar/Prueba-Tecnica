package com.prueba1.prueba.repository;

import com.prueba1.prueba.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
