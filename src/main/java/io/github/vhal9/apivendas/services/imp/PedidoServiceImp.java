package io.github.vhal9.apivendas.services.imp;

import io.github.vhal9.apivendas.exceptions.RegraDeNegocioException;
import io.github.vhal9.apivendas.models.dto.ItemPedidoDTO;
import io.github.vhal9.apivendas.models.dto.PedidoDTO;
import io.github.vhal9.apivendas.models.entity.Cliente;
import io.github.vhal9.apivendas.models.entity.ItemPedido;
import io.github.vhal9.apivendas.models.entity.Pedido;
import io.github.vhal9.apivendas.models.entity.Produto;
import io.github.vhal9.apivendas.repositorys.ClienteRepository;
import io.github.vhal9.apivendas.repositorys.ItemPedidoRepository;
import io.github.vhal9.apivendas.repositorys.PedidoRepository;
import io.github.vhal9.apivendas.repositorys.ProdutoRepository;
import io.github.vhal9.apivendas.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImp implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {

        Integer idCliente = pedidoDTO.getCliente();

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, pedidoDTO.getItens());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItensPedido(itensPedido);

        return pedido;

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer idPedido) {

        return pedidoRepository.findByIdFetchItensPedido(idPedido);

    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itensPedido) {

        if(itensPedido.isEmpty()) {

            throw new RegraDeNegocioException("Não é possível realizar um pedido sem itens");

        }

        return itensPedido
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() ->
                                    new RegraDeNegocioException("Código de produto inválido: " + idProduto)
                            );

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
