package io.github.vhal9.apivendas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @Column(name = "prod_preco_unitario")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco;

}
