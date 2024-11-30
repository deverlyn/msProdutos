package com.fiap.msProdutos.application.usecases.produto;
import com.fiap.msProdutos.application.gateways.produto.VenderProdutoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class VenderProdutoTest {

    private VenderProduto venderProduto;
    private VenderProdutoInterface venderProdutoInterface;

    @BeforeEach
    void setUp() {
        venderProdutoInterface = Mockito.mock(VenderProdutoInterface.class);
        venderProduto = new VenderProduto(venderProdutoInterface);
    }

    @Test
    void testVenderProduto() {
        Long id = 1L;
        int quantidade = 10;

        venderProduto.venderProduto(id, quantidade);

        verify(venderProdutoInterface).venderProdutoInterface(id, quantidade);
    }
}