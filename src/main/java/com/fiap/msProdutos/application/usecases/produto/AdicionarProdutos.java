package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.AdicionarProdutosInterface;

public class AdicionarProdutos {

    private final AdicionarProdutosInterface adicionarProdutosInterface;

    public AdicionarProdutos(AdicionarProdutosInterface adicionarProdutosInterface) {
        this.adicionarProdutosInterface = adicionarProdutosInterface;
    }

    public void adicionarProdutos(Long id, int quantidade) {
        adicionarProdutosInterface.adicionarProduto(id, quantidade);
    }
}
