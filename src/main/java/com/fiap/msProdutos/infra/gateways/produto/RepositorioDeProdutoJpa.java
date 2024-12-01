package com.fiap.msProdutos.infra.gateways.produto;

import com.fiap.msProdutos.application.gateways.produto.*;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoEntity;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RepositorioDeProdutoJpa implements
        AdicionarProdutosInterface,
        CadastrarProdutoInterface,
        ChecarUmaListaDeProdutosInterface,
        ChecarUmProdutoInterface,
        ConsultarProdutosDisponiveisInterface,
        ConsultarTodosOsProdutosInterface,
        ConsultarUmProdutoInterface,
        ExcluirProdutoInterface,
        VenderProdutoInterface {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public RepositorioDeProdutoJpa(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void adicionarProduto(Long id, int quantidade) {
        ProdutoEntity entity = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Produto não encontrado"));
        entity.setQuantidade(entity.getQuantidade() + quantidade);
        repository.save(entity);
    }

    @Override
    public Produto cadastraProdutoInterface(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        ProdutoEntity entity = new ProdutoEntity(produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade());

        ProdutoEntity savedEntity = repository.save(entity);
        Produto result = mapper.toDomain(savedEntity);
        if (result == null) {
            throw new IllegalStateException("Mapped Produto não pode ser nulo");
        }
        return result;
    }

    @Override
    public boolean checarUmaListaDeProdutos(List<Produto> produtos) {
        List<Long> idsProdutos = produtos.stream()
                .map(Produto::getId)
                .toList();

        List<ProdutoEntity> produtosNoBanco = repository.findAllById(idsProdutos);

        Map<Long, ProdutoEntity> mapaProdutos = produtosNoBanco.stream()
                .collect(Collectors.toMap(ProdutoEntity::getId, produto -> produto));

        for (Produto produto : produtos) {
            ProdutoEntity entity = mapaProdutos.get(produto.getId());
            if (entity == null || produto.getQuantidade() > entity.getQuantidade()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean checarUmProduto(Long id, int quantidade) {
        ProdutoEntity entity = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Produto não encontrado"));
        return quantidade <= entity.getQuantidade();
    }

    @Override
    public List<Produto> consultarProdutosDisponiveisInterface() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .filter(Objects::nonNull) // Filter out null values
                .filter(produto -> produto.getQuantidade() > 0)
                .toList();
    }

    @Override
    public List<Produto> consultarTodosOsProdutosInterface() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Produto consultarUmProduto(Long id) {
        return mapper.toDomain(repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Produto não encontrado")));
    }

    @Override
    public void excluirProdutoInterface(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void venderProdutoInterface(Long id, int quantidade) {
        ProdutoEntity entity = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Produto não encontrado"));
        if (entity.getQuantidade() >= quantidade) {
            entity.setQuantidade(entity.getQuantidade() - quantidade);
            repository.save(entity);
        } else {
            throw new IllegalArgumentException("Quantidade maior do que o disponível");
        }
    }
}
