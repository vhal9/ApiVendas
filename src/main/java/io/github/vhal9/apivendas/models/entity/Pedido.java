package io.github.vhal9.apivendas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ped_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "ped_data")
    private LocalDate dataPedido;

    @Column(name = "ped_total", precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

}
