package io.github.vhal9.apivendas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.login-usuario.obrigatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{campo.senha-usuario.obrigatorio}")
    private String senha;

    @Column
    private boolean admin;
}
