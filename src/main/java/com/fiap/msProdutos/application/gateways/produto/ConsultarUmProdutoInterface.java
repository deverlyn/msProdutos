package com.fiap.msProdutos.application.gateways.produto;

import com.fiap.msProdutos.domain.entity.produto.Produto;

public interface ConsultarUmProdutoInterface {
    Produto consultarUmProduto(Long id);
}
