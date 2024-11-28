package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ChecarUmProdutoInterface;

public class ChecarUmProduto {

    private final ChecarUmProdutoInterface checarUmProduto;

    public ChecarUmProduto(ChecarUmProdutoInterface checarUmProduto) {
        this.checarUmProduto = checarUmProduto;
    }

    public Boolean checarUmProduto(Long id, int quantidade){
        return checarUmProduto.checarUmProduto(id, quantidade);
    }
}
