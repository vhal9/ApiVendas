package io.github.vhal9.apivendas.repositorys;

import io.github.vhal9.apivendas.models.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
