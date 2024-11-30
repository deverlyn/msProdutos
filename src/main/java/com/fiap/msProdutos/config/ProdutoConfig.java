package com.fiap.msProdutos.config;

import com.fiap.msProdutos.application.gateways.produto.*;
import com.fiap.msProdutos.application.usecases.produto.*;
import com.fiap.msProdutos.infra.gateways.produto.ProdutoMapper;
import com.fiap.msProdutos.infra.gateways.produto.RepositorioDeProdutoJpa;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    AdicionarProdutos adicionarProdutos(AdicionarProdutosInterface adicionarProdutosInterface){
        return new AdicionarProdutos(adicionarProdutosInterface);
    }

    @Bean
    CadastrarProduto cadastrarProduto(CadastrarProdutoInterface cadastrarProdutoInterface){
        return new CadastrarProduto(cadastrarProdutoInterface);
    }

    @Bean
    ChecarUmaListaDeProdutos checarUmaListaDeProdutos(ChecarUmaListaDeProdutosInterface checarUmaListaDeProdutosInterface){
        return new ChecarUmaListaDeProdutos(checarUmaListaDeProdutosInterface);
    }

    @Bean
    ChecarUmProduto checarUmProduto(ChecarUmProdutoInterface checarUmProdutoInterface){
        return new ChecarUmProduto(checarUmProdutoInterface);
    }

    @Bean
    ConsultarProdutosDisponiveis consultarProdutosDisponiveis(ConsultarProdutosDisponiveisInterface consultarProdutosDisponiveisInterface){
        return new ConsultarProdutosDisponiveis(consultarProdutosDisponiveisInterface);
    }

    @Bean
    ConsultarTodosOsProdutos consultarTodosOsProdutos(ConsultarTodosOsProdutosInterface consultarTodosOsProdutosInterface){
        return new ConsultarTodosOsProdutos(consultarTodosOsProdutosInterface);
    }

    @Bean
    ConsultarUmProduto consultarUmProduto(ConsultarUmProdutoInterface consultarUmProdutoInterface){
        return new ConsultarUmProduto(consultarUmProdutoInterface);
    }

    @Bean
    ExcluirProduto excluirProduto(ExcluirProdutoInterface excluirProdutoInterface){
        return new ExcluirProduto(excluirProdutoInterface);
    }

    @Bean
    VenderProduto venderProduto(VenderProdutoInterface venderProdutoInterface){
        return new VenderProduto(venderProdutoInterface);
    }

    @Bean
    RepositorioDeProdutoJpa repositorioDeProdutoJpa(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper){
        return new RepositorioDeProdutoJpa(produtoRepository, produtoMapper);
    }

    @Bean
    ProdutoMapper mapper(){
        return new ProdutoMapper();
    }

}
