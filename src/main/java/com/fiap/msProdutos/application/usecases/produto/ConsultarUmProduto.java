package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ConsultarUmProdutoInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;

public class ConsultarUmProduto {

    private final ConsultarUmProdutoInterface consultarUmProdutoInterface;

    public ConsultarUmProduto(ConsultarUmProdutoInterface consultarUmProdutoInterface) {
        this.consultarUmProdutoInterface = consultarUmProdutoInterface;
    }

    public Produto consultarUmProduto(Long id) {
        return consultarUmProdutoInterface.consultarUmProduto(id);
    }
}
