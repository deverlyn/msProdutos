package com.fiap.msProdutos.controller.produto;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.msProdutos.application.usecases.produto.VenderProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
public class ProdutoRabbitConsumerTest {

    @Mock
    private VenderProduto venderProduto;

    @InjectMocks
    private ProdutoRabbitConsumer produtoRabbitConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessarMensagem() throws Exception {
        String mensagemJson = "{\"id\":1,\"quantidade\":10}";

//        produtoRabbitConsumer.processarMensagem(mensagemJson);

        verify(venderProduto, times(1)).venderProduto(1L, 10);
    }

    @Test
    void testProcessarMensagemComErro() {
        String mensagemJson = "invalid json";

//        produtoRabbitConsumer.processarMensagem(mensagemJson);

        // No interaction with venderProduto should happen
        verify(venderProduto, times(0)).venderProduto(1L, 10);
    }
}