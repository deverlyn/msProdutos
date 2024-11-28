package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ConsultarTodosOsProdutosInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;

import java.util.List;

public class ConsultarTodosOsProdutos {

    private final ConsultarTodosOsProdutosInterface consultarTodosOsProdutosInterface;

    public ConsultarTodosOsProdutos(ConsultarTodosOsProdutosInterface consultarTodosOsProdutosInterface) {
        this.consultarTodosOsProdutosInterface = consultarTodosOsProdutosInterface;
    }

    public List<Produto> consultarTodosOsProdutos(){
        return consultarTodosOsProdutosInterface.consultarTodosOsProdutosInterface();
    }
}
