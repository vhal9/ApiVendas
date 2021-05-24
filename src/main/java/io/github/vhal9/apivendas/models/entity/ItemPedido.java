package io.github.vhal9.apivendas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_item_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_ped_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "item_ped_total")
    private Integer quantidade;

}
