package com.fiap.msProdutos.application.usecases.produto;


import com.fiap.msProdutos.application.gateways.produto.ExcluirProdutoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ExcluirProdutoTest {

    private ExcluirProduto excluirProduto;
    private ExcluirProdutoInterface excluirProdutoInterface;

    @BeforeEach
    void setUp() {
        excluirProdutoInterface = Mockito.mock(ExcluirProdutoInterface.class);
        excluirProduto = new ExcluirProduto(excluirProdutoInterface);
    }

    @Test
    void testExcluirProduto() {
        Long id = 1L;

        excluirProduto.excluirProduto(id);

        verify(excluirProdutoInterface).excluirProdutoInterface(id);
    }
}