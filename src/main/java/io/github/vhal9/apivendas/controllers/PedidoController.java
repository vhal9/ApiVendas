package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.models.dto.PedidoDTO;
import io.github.vhal9.apivendas.models.entity.Pedido;
import io.github.vhal9.apivendas.repositorys.PedidoRepository;
import io.github.vhal9.apivendas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {

        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();

    }

}
