package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ConsultarProdutosDisponiveisInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;

import java.util.List;

public class ConsultarProdutosDisponiveis {
    private final ConsultarProdutosDisponiveisInterface consultarProdutosDisponiveisInterface;

    public ConsultarProdutosDisponiveis(ConsultarProdutosDisponiveisInterface consultarProdutosDisponiveisInterface) {
        this.consultarProdutosDisponiveisInterface = consultarProdutosDisponiveisInterface;
    }

    public List<Produto> consultarProdutosDisponiveis() {
        return consultarProdutosDisponiveisInterface.consultarProdutosDisponiveisInterface();
    }
}
