package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.models.entity.Cliente;
import io.github.vhal9.apivendas.repositorys.ClienteRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes/")
@Api("Api Clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getClientes() {

        return clienteRepository.findAll();

    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado.")
    })
    public Cliente getClienteById(
            @ApiParam(
                    value = "ID do cliente",
                    example = "1"
            )
            @PathVariable Integer id) {

        return clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PostMapping
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    @PutMapping
    @ApiOperation("Altera um cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente alterado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
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
    @ApiOperation("Deleta um cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente deletado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @ApiParam(
                    value = "ID do cliente",
                    example = "1"
            )
            @PathVariable Integer id) {

        clienteRepository
                .findById(id)
                .map( cliente -> {
                    clienteRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado."));

    }

}
