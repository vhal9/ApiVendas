package io.github.vhal9.apivendas.repositorys;

import io.github.vhal9.apivendas.models.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query(" select p from Pedido p left join fetch p.itensPedido where p.id = :id")
    Optional<Pedido> findByIdFetchItensPedido(@Param("id") Integer id);
    
}
