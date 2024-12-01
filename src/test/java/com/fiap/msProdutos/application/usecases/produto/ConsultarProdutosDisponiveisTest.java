package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ConsultarProdutosDisponiveisInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsultarProdutosDisponiveisTest {

    private ConsultarProdutosDisponiveis consultarProdutosDisponiveis;
    private ConsultarProdutosDisponiveisInterface consultarProdutosDisponiveisInterface;

    @BeforeEach
    void setUp() {
        consultarProdutosDisponiveisInterface = Mockito.mock(ConsultarProdutosDisponiveisInterface.class);
        consultarProdutosDisponiveis = new ConsultarProdutosDisponiveis(consultarProdutosDisponiveisInterface);
    }

    @Test
    void testConsultarProdutosDisponiveis() {
        List<Produto> produtos = List.of(new Produto(1L, "Produto 1", "Descrição 1", 10));
        when(consultarProdutosDisponiveisInterface.consultarProdutosDisponiveisInterface()).thenReturn(produtos);

        List<Produto> result = consultarProdutosDisponiveis.consultarProdutosDisponiveis();

        assertEquals(produtos, result);
        verify(consultarProdutosDisponiveisInterface).consultarProdutosDisponiveisInterface();
    }
}
