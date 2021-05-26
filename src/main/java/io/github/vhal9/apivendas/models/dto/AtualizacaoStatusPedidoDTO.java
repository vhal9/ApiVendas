package io.github.vhal9.apivendas.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AtualizacaoStatusPedidoDTO {

    @NotEmpty(message = "{campo.status-pedido-obrigatorio}")
    private String novoStatus;

}
