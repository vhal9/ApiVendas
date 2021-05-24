package io.github.vhal9.apivendas.repositorys;

import io.github.vhal9.apivendas.models.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
