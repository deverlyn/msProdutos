package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.CadastrarProdutoInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CadastrarProdutoTest {

    private CadastrarProduto cadastrarProduto;
    private CadastrarProdutoInterface cadastrarProdutoInterface;

    @BeforeEach
    void setUp() {
        cadastrarProdutoInterface = Mockito.mock(CadastrarProdutoInterface.class);
        cadastrarProduto = new CadastrarProduto(cadastrarProdutoInterface);
    }

    @Test
    void testCadastrarProduto() {
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);
        when(cadastrarProdutoInterface.cadastraProdutoInterface(any(Produto.class))).thenReturn(produto);

        Produto result = cadastrarProduto.cadastrarProduto(produto);

        assertEquals(produto, result);
        verify(cadastrarProdutoInterface).cadastraProdutoInterface(produto);
    }
}
