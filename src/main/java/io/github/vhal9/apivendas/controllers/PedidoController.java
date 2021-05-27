package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.models.Enums.StatusPedido;
import io.github.vhal9.apivendas.models.dto.AtualizacaoStatusPedidoDTO;
import io.github.vhal9.apivendas.models.dto.InformacoesItemPedidoDTO;
import io.github.vhal9.apivendas.models.dto.InformacoesPedidoDTO;
import io.github.vhal9.apivendas.models.dto.PedidoDTO;
import io.github.vhal9.apivendas.models.entity.ItemPedido;
import io.github.vhal9.apivendas.models.entity.Pedido;
import io.github.vhal9.apivendas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos/")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO) {

        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();

    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getPedido(@PathVariable Integer id) {

        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converterPedido(p))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido nao encontrado."));
                
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusPedido(@PathVariable("id") Integer id,
                                   @RequestBody @Valid AtualizacaoStatusPedidoDTO atualizacaoStatusPedidoDTO) {

        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
        pedidoService.AtualizaStatusPedido(id, StatusPedido.valueOf(novoStatus));

    }

    private InformacoesPedidoDTO converterPedido(Pedido pedido) {

        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .statusPedido(pedido.getStatusPedido().name())
                .itens(converterItensPedido(pedido.getItensPedido()))
                .build();

    }

    private List<InformacoesItemPedidoDTO> converterItensPedido(List<ItemPedido> itens) {

        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());

    }

}
