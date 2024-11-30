package com.fiap.msProdutos.infra.controller.produto;

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
    public void processarMensagem(String mensagemJson) {
        try {
            // Parse o JSON recebido
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(mensagemJson);

            // Extraia os campos do JSON
            Long id = jsonNode.get("id").asLong();
            int quantidade = jsonNode.get("quantidade").asInt();

            venderProduto.venderProduto(id, quantidade);
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
