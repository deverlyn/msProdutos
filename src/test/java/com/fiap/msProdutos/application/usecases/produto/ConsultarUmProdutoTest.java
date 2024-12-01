package com.fiap.msProdutos.application.usecases.produto;


import com.fiap.msProdutos.application.gateways.produto.ConsultarUmProdutoInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsultarUmProdutoTest {

    private ConsultarUmProduto consultarUmProduto;
    private ConsultarUmProdutoInterface consultarUmProdutoInterface;

    @BeforeEach
    void setUp() {
        consultarUmProdutoInterface = Mockito.mock(ConsultarUmProdutoInterface.class);
        consultarUmProduto = new ConsultarUmProduto(consultarUmProdutoInterface);
    }

    @Test
    void testConsultarUmProduto() {
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);
        when(consultarUmProdutoInterface.consultarUmProduto(anyLong())).thenReturn(produto);

        Produto result = consultarUmProduto.consultarUmProduto(1L);

        assertEquals(produto, result);
        verify(consultarUmProdutoInterface).consultarUmProduto(1L);
    }
}