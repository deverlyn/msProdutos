package com.fiap.msProdutos.infra.gateways.produto;

import com.fiap.msProdutos.domain.entity.produto.Produto;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoEntity;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class RepositorioDeProdutoJpaTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoMapper mapper;

    @InjectMocks
    private RepositorioDeProdutoJpa repositorioDeProdutoJpa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarProduto() {
        Long id = 1L;
        int quantidade = 10;
        ProdutoEntity entity = new ProdutoEntity(id, "Produto 1", "Descrição 1", 5);

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        repositorioDeProdutoJpa.adicionarProduto(id, quantidade);

        assertEquals(15, entity.getQuantidade());
        verify(repository).save(entity);
    }

    @Test
    void testCadastraProdutoInterface() {
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);
        ProdutoEntity entity = new ProdutoEntity(1L, "Produto 1", "Descrição 1", 10);

        when(mapper.toDomain(any(ProdutoEntity.class))).thenReturn(produto);
        when(repository.save(any(ProdutoEntity.class))).thenReturn(entity);

        Produto result = repositorioDeProdutoJpa.cadastraProdutoInterface(produto);

        assertNotNull(result);
        assertEquals(produto, result);
        verify(repository).save(any(ProdutoEntity.class));
    }

    @Test
    void testChecarUmaListaDeProdutos() {
        Produto produto1 = new Produto(1L, "Produto 1", "Descrição 1", 5);
        Produto produto2 = new Produto(2L, "Produto 2", "Descrição 2", 3);
        List<Produto> produtos = List.of(produto1, produto2);

        ProdutoEntity entity1 = new ProdutoEntity(1L, "Produto 1", "Descrição 1", 10);
        ProdutoEntity entity2 = new ProdutoEntity(2L, "Produto 2", "Descrição 2", 5);
        List<ProdutoEntity> produtosNoBanco = List.of(entity1, entity2);

        when(repository.findAllById(anyList())).thenReturn(produtosNoBanco);

        boolean result = repositorioDeProdutoJpa.checarUmaListaDeProdutos(produtos);

        assertTrue(result);
    }

    @Test
    void testChecarUmProduto() {
        Long id = 1L;
        int quantidade = 5;
        ProdutoEntity entity = new ProdutoEntity(id, "Produto 1", "Descrição 1", 10);

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        boolean result = repositorioDeProdutoJpa.checarUmProduto(id, quantidade);

        assertTrue(result);
    }

    @Test
    void testConsultarProdutosDisponiveisInterface() {
        ProdutoEntity entity1 = new ProdutoEntity(1L, "Produto 1", "Descrição 1", 10);
        ProdutoEntity entity2 = new ProdutoEntity(2L, "Produto 2", "Descrição 2", 0);
        List<ProdutoEntity> entities = List.of(entity1, entity2);
        Produto produto1 = new Produto(1L, "Produto 1", "Descrição 1", 10);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDomain(entity1)).thenReturn(produto1);
        when(mapper.toDomain(entity2)).thenReturn(null);

        List<Produto> result = repositorioDeProdutoJpa.consultarProdutosDisponiveisInterface();

        assertEquals(1, result.size());
        assertEquals(produto1, result.get(0));
    }

    @Test
    void testConsultarTodosOsProdutosInterface() {
        ProdutoEntity entity1 = new ProdutoEntity(1L, "Produto 1", "Descrição 1", 10);
        ProdutoEntity entity2 = new ProdutoEntity(2L, "Produto 2", "Descrição 2", 5);
        List<ProdutoEntity> entities = List.of(entity1, entity2);
        Produto produto1 = new Produto(1L, "Produto 1", "Descrição 1", 10);
        Produto produto2 = new Produto(2L, "Produto 2", "Descrição 2", 5);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDomain(entity1)).thenReturn(produto1);
        when(mapper.toDomain(entity2)).thenReturn(produto2);

        List<Produto> result = repositorioDeProdutoJpa.consultarTodosOsProdutosInterface();

        assertEquals(2, result.size());
        assertEquals(produto1, result.get(0));
        assertEquals(produto2, result.get(1));
    }

    @Test
    void testConsultarUmProduto() {
        Long id = 1L;
        ProdutoEntity entity = new ProdutoEntity(id, "Produto 1", "Descrição 1", 10);
        Produto produto = new Produto(id, "Produto 1", "Descrição 1", 10);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(produto);

        Produto result = repositorioDeProdutoJpa.consultarUmProduto(id);

        assertNotNull(result);
        assertEquals(produto, result);
    }

    @Test
    void testExcluirProdutoInterface() {
        Long id = 1L;

        repositorioDeProdutoJpa.excluirProdutoInterface(id);

        verify(repository).deleteById(id);
    }

    @Test
    void testVenderProdutoInterface() {
        Long id = 1L;
        int quantidade = 5;
        ProdutoEntity entity = new ProdutoEntity(id, "Produto 1", "Descrição 1", 10);

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        repositorioDeProdutoJpa.venderProdutoInterface(id, quantidade);

        assertEquals(5, entity.getQuantidade());
        verify(repository).save(entity);
    }
}