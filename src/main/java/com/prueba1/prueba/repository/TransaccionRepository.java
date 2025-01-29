package com.prueba1.prueba.repository;

import com.prueba1.prueba.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion,Long> {
}
