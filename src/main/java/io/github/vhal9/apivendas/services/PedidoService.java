package io.github.vhal9.apivendas.services;

import io.github.vhal9.apivendas.models.Enums.StatusPedido;
import io.github.vhal9.apivendas.models.dto.PedidoDTO;
import io.github.vhal9.apivendas.models.entity.Pedido;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer idPedido);

    void AtualizaStatusPedido(Integer idPedido, StatusPedido statusPedido);

}
