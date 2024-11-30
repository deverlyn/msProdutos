package com.fiap.msProdutos.infra.gateways.produto;


import com.fiap.msProdutos.domain.entity.produto.Produto;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoMapperTest {

    private final ProdutoMapper mapper = new ProdutoMapper();

    @Test
    void testToDomain() {
        ProdutoEntity entity = new ProdutoEntity(1L, "Produto 1", "Descrição 1", 10);
        Produto produto = mapper.toDomain(entity);

        assertEquals(entity.getId(), produto.getId());
        assertEquals(entity.getNome(), produto.getNome());
        assertEquals(entity.getDescricao(), produto.getDescricao());
        assertEquals(entity.getQuantidade(), produto.getQuantidade());
    }

    @Test
    void testToEntity() {
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);
        ProdutoEntity entity = mapper.toEntity(produto);

        assertEquals(produto.getId(), entity.getId());
        assertEquals(produto.getNome(), entity.getNome());
        assertEquals(produto.getDescricao(), entity.getDescricao());
        assertEquals(produto.getQuantidade(), entity.getQuantidade());
    }
}