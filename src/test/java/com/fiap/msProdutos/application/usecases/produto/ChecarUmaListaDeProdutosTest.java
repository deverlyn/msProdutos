package com.fiap.msProdutos.application.usecases.produto;


import com.fiap.msProdutos.application.gateways.produto.ChecarUmaListaDeProdutosInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChecarUmaListaDeProdutosTest {

    private ChecarUmaListaDeProdutos checarUmaListaDeProdutos;
    private ChecarUmaListaDeProdutosInterface checarUmaListaDeProdutosInterface;

    @BeforeEach
    void setUp() {
        checarUmaListaDeProdutosInterface = Mockito.mock(ChecarUmaListaDeProdutosInterface.class);
        checarUmaListaDeProdutos = new ChecarUmaListaDeProdutos(checarUmaListaDeProdutosInterface);
    }

    @Test
    void testChecarUmaListaDeProdutos() {
        List<Produto> produtos = List.of(new Produto(1L, "Produto 1", "Descrição 1", 10));
        when(checarUmaListaDeProdutosInterface.checarUmaListaDeProdutos(anyList())).thenReturn(true);

        boolean result = checarUmaListaDeProdutos.checarUmaListaDeProdutos(produtos);

        assertTrue(result);
        verify(checarUmaListaDeProdutosInterface).checarUmaListaDeProdutos(produtos);
    }
}