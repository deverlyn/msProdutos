package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.VenderProdutoInterface;

public class VenderProduto {

    private final VenderProdutoInterface venderProdutoInterface;

    public VenderProduto(VenderProdutoInterface venderProdutoInterface) {
        this.venderProdutoInterface = venderProdutoInterface;
    }

    public void venderProduto(Long id, int quantidade) {
        venderProdutoInterface.venderProdutoInterface(id, quantidade);
    }
}
