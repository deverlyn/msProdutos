package com.fiap.msProdutos.infra.persistence.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedidoMessage {

    private Long id;
    private int quantidade;
}
