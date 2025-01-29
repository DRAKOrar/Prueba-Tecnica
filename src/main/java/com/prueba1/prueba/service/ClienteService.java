package com.prueba1.prueba.service;

import com.prueba1.prueba.dto.ClienteDTO;
import com.prueba1.prueba.entity.Cliente;
import com.prueba1.prueba.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Convertir Cliente a ClienteDTO
    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setTipoIdentificacion(cliente.getTipoIdentificacion());
        clienteDTO.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        clienteDTO.setNombres(cliente.getNombres());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setCorreoElectronico(cliente.getCorreoElectronico());
        clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
        return clienteDTO;
    }

    // Convertir ClienteDTO a Cliente
    private Cliente convertirACliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setFechaCreacion(LocalDate.now());
        return cliente;
    }

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertirACliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return convertirAClienteDTO(cliente);
    }

    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).map(this::convertirAClienteDTO);
    }

    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombres(clienteDTO.getNombres());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
            cliente.setFechaModificacion(LocalDate.now());
            cliente = clienteRepository.save(cliente);
            return convertirAClienteDTO(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
