package com.prueba1.prueba.controller;
import com.prueba1.prueba.dto.TransaccionDTO;
import com.prueba1.prueba.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping
    public TransaccionDTO crearTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.crearTransaccion(transaccionDTO);
    }

    @GetMapping("/{id}")
    public Optional<TransaccionDTO> obtenerTransaccionPorId(@PathVariable Long id) {
        return transaccionService.obtenerTransaccionPorId(id);
    }

    @GetMapping
    public List<TransaccionDTO> obtenerTodasLasTransacciones() {
        return transaccionService.obtenerTodasLasTransacciones();
    }

    @PutMapping("/{id}")
    public TransaccionDTO actualizarTransaccion(@PathVariable Long id, @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.actualizarTransaccion(id, transaccionDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarTransaccion(@PathVariable Long id) {
        transaccionService.eliminarTransaccion(id);
    }
}