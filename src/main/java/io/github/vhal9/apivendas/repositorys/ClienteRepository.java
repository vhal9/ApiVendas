package io.github.vhal9.apivendas.repositorys;

import io.github.vhal9.apivendas.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
