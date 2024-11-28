package com.fiap.msProdutos.infra.controller.produto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        String descricao,
        int quantidade
) {
}
