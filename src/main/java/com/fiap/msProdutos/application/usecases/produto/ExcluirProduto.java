package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ExcluirProdutoInterface;

public class ExcluirProduto {

    private final ExcluirProdutoInterface excluirProdutoInterface;

    public ExcluirProduto(ExcluirProdutoInterface excluirProdutoInterface) {
        this.excluirProdutoInterface = excluirProdutoInterface;
    }

    public void excluirProduto(Long id) {
        excluirProdutoInterface.excluirProdutoInterface(id);
    }
}
