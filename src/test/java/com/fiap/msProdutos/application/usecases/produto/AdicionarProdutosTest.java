package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.AdicionarProdutosInterface;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AdicionarProdutosTest {

    private AdicionarProdutos adicionarProdutos;
    private AdicionarProdutosInterface adicionarProdutosInterface;

    @BeforeEach
    void setUp() {
        adicionarProdutosInterface = Mockito.mock(AdicionarProdutosInterface.class);
        adicionarProdutos = new AdicionarProdutos(adicionarProdutosInterface);
    }

    @Test
    void testAdicionarProdutos() {
        Long id = 1L;
        int quantidade = 10;

        adicionarProdutos.adicionarProdutos(id, quantidade);

        verify(adicionarProdutosInterface, times(1)).adicionarProduto(id, quantidade);
    }
}