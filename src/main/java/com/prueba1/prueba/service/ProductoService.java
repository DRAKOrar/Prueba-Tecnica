package com.prueba1.prueba.service;

import com.prueba1.prueba.dto.ProductoDTO;
import com.prueba1.prueba.entity.Producto;
import com.prueba1.prueba.repository.ClienteRepository;
import com.prueba1.prueba.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Convertir Producto a ProductoDTO
    private ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setTipoCuenta(producto.getTipoCuenta());
        productoDTO.setNumeroCuenta(producto.getNumeroCuenta());
        productoDTO.setEstado(producto.getEstado());
        productoDTO.setSaldo(producto.getSaldo());
        productoDTO.setExentaGMF(producto.isExentaGMF());
        productoDTO.setFechaCreacion(producto.getFechaCreacion());
        productoDTO.setClienteId(producto.getCliente().getId()); // Asignar el ID del cliente
        return productoDTO;
    }

    // Convertir ProductoDTO a Producto
    private Producto convertirAProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setTipoCuenta(productoDTO.getTipoCuenta());
        producto.setNumeroCuenta(productoDTO.getNumeroCuenta());
        producto.setEstado(productoDTO.getEstado());
        producto.setSaldo(productoDTO.getSaldo());
        producto.setExentaGMF(productoDTO.isExentaGMF());
        producto.setFechaCreacion(LocalDate.now());

        // Vincular el producto a un cliente
        clienteRepository.findById(productoDTO.getClienteId()).ifPresent(producto::setCliente);

        return producto;
    }

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = convertirAProducto(productoDTO);
        producto = productoRepository.save(producto);
        return convertirAProductoDTO(producto);
    }

    public Optional<ProductoDTO> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).map(this::convertirAProductoDTO);
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        return productoRepository.findById(id).map(producto -> {
            producto.setTipoCuenta(productoDTO.getTipoCuenta());
            producto.setEstado(productoDTO.getEstado());
            producto.setSaldo(productoDTO.getSaldo());
            producto.setExentaGMF(productoDTO.isExentaGMF());
            producto.setFechaModificacion(LocalDate.now());
            producto = productoRepository.save(producto);
            return convertirAProductoDTO(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
