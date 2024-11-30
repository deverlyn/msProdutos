package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ChecarUmProdutoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChecarUmProdutoTest {

    private ChecarUmProduto checarUmProduto;
    private ChecarUmProdutoInterface checarUmProdutoInterface;

    @BeforeEach
    void setUp() {
        checarUmProdutoInterface = Mockito.mock(ChecarUmProdutoInterface.class);
        checarUmProduto = new ChecarUmProduto(checarUmProdutoInterface);
    }

    @Test
    void testChecarUmProduto() {
        Long id = 1L;
        int quantidade = 10;
        when(checarUmProdutoInterface.checarUmProduto(anyLong(), anyInt())).thenReturn(true);

        Boolean result = checarUmProduto.checarUmProduto(id, quantidade);

        assertTrue(result);
        verify(checarUmProdutoInterface).checarUmProduto(id, quantidade);
    }
}