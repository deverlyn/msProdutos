package com.fiap.msProdutos.config;
import com.fiap.msProdutos.application.gateways.produto.*;
import com.fiap.msProdutos.application.usecases.produto.*;
import com.fiap.msProdutos.infra.gateways.produto.ProdutoMapper;
import com.fiap.msProdutos.infra.gateways.produto.RepositorioDeProdutoJpa;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ProdutoConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testAdicionarProdutosBean() {
        AdicionarProdutos adicionarProdutos = context.getBean(AdicionarProdutos.class);
        assertNotNull(adicionarProdutos);
    }

    @Test
    void testCadastrarProdutoBean() {
        CadastrarProduto cadastrarProduto = context.getBean(CadastrarProduto.class);
        assertNotNull(cadastrarProduto);
    }

    @Test
    void testChecarUmaListaDeProdutosBean() {
        ChecarUmaListaDeProdutos checarUmaListaDeProdutos = context.getBean(ChecarUmaListaDeProdutos.class);
        assertNotNull(checarUmaListaDeProdutos);
    }

    @Test
    void testChecarUmProdutoBean() {
        ChecarUmProduto checarUmProduto = context.getBean(ChecarUmProduto.class);
        assertNotNull(checarUmProduto);
    }

    @Test
    void testConsultarProdutosDisponiveisBean() {
        ConsultarProdutosDisponiveis consultarProdutosDisponiveis = context.getBean(ConsultarProdutosDisponiveis.class);
        assertNotNull(consultarProdutosDisponiveis);
    }

    @Test
    void testConsultarTodosOsProdutosBean() {
        ConsultarTodosOsProdutos consultarTodosOsProdutos = context.getBean(ConsultarTodosOsProdutos.class);
        assertNotNull(consultarTodosOsProdutos);
    }

    @Test
    void testConsultarUmProdutoBean() {
        ConsultarUmProduto consultarUmProduto = context.getBean(ConsultarUmProduto.class);
        assertNotNull(consultarUmProduto);
    }

    @Test
    void testExcluirProdutoBean() {
        ExcluirProduto excluirProduto = context.getBean(ExcluirProduto.class);
        assertNotNull(excluirProduto);
    }

    @Test
    void testVenderProdutoBean() {
        VenderProduto venderProduto = context.getBean(VenderProduto.class);
        assertNotNull(venderProduto);
    }

    @Test
    void testRepositorioDeProdutoJpaBean() {
        RepositorioDeProdutoJpa repositorioDeProdutoJpa = context.getBean(RepositorioDeProdutoJpa.class);
        assertNotNull(repositorioDeProdutoJpa);
    }

    @Test
    void testProdutoMapperBean() {
        ProdutoMapper produtoMapper = context.getBean(ProdutoMapper.class);
        assertNotNull(produtoMapper);
    }
}