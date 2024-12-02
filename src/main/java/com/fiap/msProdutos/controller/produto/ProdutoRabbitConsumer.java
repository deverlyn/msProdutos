package com.fiap.msProdutos.controller.produto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.msProdutos.application.usecases.produto.VenderProduto;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoPedidoMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoRabbitConsumer {

    @Autowired
    private VenderProduto venderProduto;

    @RabbitListener(queues = "pedidosQueue")
    public void processarMensagem(ProdutoPedidoMessage produtoPedido) {
        try {
            venderProduto.venderProduto(produtoPedido.getId(), produtoPedido.getQuantidade());
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
