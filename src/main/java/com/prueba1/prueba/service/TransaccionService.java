package com.prueba1.prueba.service;

import com.prueba1.prueba.dto.TransaccionDTO;
import com.prueba1.prueba.entity.Transaccion;
import com.prueba1.prueba.repository.ProductoRepository;
import com.prueba1.prueba.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Convertir Transaccion a TransaccionDTO
    private TransaccionDTO convertirATransaccionDTO(Transaccion transaccion) {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setId(transaccion.getId());
        transaccionDTO.setTipoTransaccion(transaccion.getTipoTransaccion());
        transaccionDTO.setMonto(transaccion.getMonto());
        transaccionDTO.setFechaTransaccion(transaccion.getFechaTransaccion());
        transaccionDTO.setProductoId(transaccion.getProducto().getId()); // Asignar el ID del producto
        return transaccionDTO;
    }

    // Convertir TransaccionDTO a Transaccion
    private Transaccion convertirATransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(transaccionDTO.getTipoTransaccion());
        transaccion.setMonto(transaccionDTO.getMonto());
        transaccion.setFechaTransaccion(LocalDateTime.now());

        // Vincular la transacción a un producto
        productoRepository.findById(transaccionDTO.getProductoId()).ifPresent(transaccion::setProducto);

        return transaccion;
    }

    public TransaccionDTO crearTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = convertirATransaccion(transaccionDTO);
        transaccion = transaccionRepository.save(transaccion);
        return convertirATransaccionDTO(transaccion);
    }

    public Optional<TransaccionDTO> obtenerTransaccionPorId(Long id) {
        return transaccionRepository.findById(id).map(this::convertirATransaccionDTO);
    }

    public List<TransaccionDTO> obtenerTodasLasTransacciones() {
        return transaccionRepository.findAll().stream()
                .map(this::convertirATransaccionDTO)
                .collect(Collectors.toList());
    }

    public TransaccionDTO actualizarTransaccion(Long id, TransaccionDTO transaccionDTO) {
        return transaccionRepository.findById(id).map(transaccion -> {
            transaccion.setTipoTransaccion(transaccionDTO.getTipoTransaccion());
            transaccion.setMonto(transaccionDTO.getMonto());
            transaccion = transaccionRepository.save(transaccion);
            return convertirATransaccionDTO(transaccion);
        }).orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
    }

    public void eliminarTransaccion(Long id) {
        transaccionRepository.deleteById(id);
    }
}
