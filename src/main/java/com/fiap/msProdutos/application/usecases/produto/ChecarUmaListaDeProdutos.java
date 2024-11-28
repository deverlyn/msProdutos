package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ChecarUmaListaDeProdutosInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;

import java.util.List;

public class ChecarUmaListaDeProdutos {

    private final ChecarUmaListaDeProdutosInterface checarUmaListaDeProdutosInterface;

    public ChecarUmaListaDeProdutos(ChecarUmaListaDeProdutosInterface checarUmaListaDeProdutosInterface) {
        this.checarUmaListaDeProdutosInterface = checarUmaListaDeProdutosInterface;
    }

    public boolean checarUmaListaDeProdutos(List<Produto> produtos) {
        return checarUmaListaDeProdutosInterface.checarUmaListaDeProdutos(produtos);
    }
}
