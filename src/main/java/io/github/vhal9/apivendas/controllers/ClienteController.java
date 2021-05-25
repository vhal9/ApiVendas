package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.models.entity.Cliente;
import io.github.vhal9.apivendas.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getClientes() {

        return clienteRepository.findAll();

    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {

        return clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody @Valid Cliente cliente) {

        clienteRepository
                .findById(cliente.getId())
                .map( clienteExistente -> {
                    clienteRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {

        clienteRepository
                .findById(id)
                .map( cliente -> {
                    clienteRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

    }

}
