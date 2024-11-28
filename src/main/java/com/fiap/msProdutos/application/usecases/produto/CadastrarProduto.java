package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.CadastrarProdutoInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;

public class CadastrarProduto {
    private final CadastrarProdutoInterface cadastrarProdutoInterface;

    public CadastrarProduto(CadastrarProdutoInterface cadastrarProdutoInterface) {
        this.cadastrarProdutoInterface = cadastrarProdutoInterface;
    }

    public Produto cadastrarProduto(Produto produto) {
        return cadastrarProdutoInterface.cadastraProdutoInterface(produto);
    }
}
