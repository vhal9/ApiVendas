package io.github.vhal9.apivendas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private Integer id;

    @Column(name = "prod_descricao")
    private String descricao;

    @Column(name = "prod_preco_unitario")
    private BigDecimal preco;

}
