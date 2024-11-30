package com.fiap.msProdutos.application.usecases.produto;

import com.fiap.msProdutos.application.gateways.produto.ConsultarTodosOsProdutosInterface;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ConsultarTodosOsProdutosTest {

    private ConsultarTodosOsProdutos consultarTodosOsProdutos;
    private ConsultarTodosOsProdutosInterface consultarTodosOsProdutosInterface;

    @BeforeEach
    void setUp() {
        consultarTodosOsProdutosInterface = Mockito.mock(ConsultarTodosOsProdutosInterface.class);
        consultarTodosOsProdutos = new ConsultarTodosOsProdutos(consultarTodosOsProdutosInterface);
    }

    @Test
    void testConsultarTodosOsProdutos() {
        List<Produto> produtos = List.of(new Produto(1L, "Produto 1", "Descrição 1", 10));
        when(consultarTodosOsProdutosInterface.consultarTodosOsProdutosInterface()).thenReturn(produtos);

        List<Produto> result = consultarTodosOsProdutos.consultarTodosOsProdutos();

        assertEquals(produtos, result);
        verify(consultarTodosOsProdutosInterface).consultarTodosOsProdutosInterface();
    }
}