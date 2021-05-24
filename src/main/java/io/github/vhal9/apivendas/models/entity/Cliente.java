package io.github.vhal9.apivendas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name="tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_nome", length = 100)
    private String nome;

    @Column(name = "cli_cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
