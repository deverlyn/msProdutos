package com.fiap.msProdutos.infra.gateways.produto;

import com.fiap.msProdutos.domain.entity.produto.Produto;
import com.fiap.msProdutos.infra.persistence.produto.ProdutoEntity;

public class ProdutoMapper {

    public Produto toDomain(ProdutoEntity input){
        return new Produto(
                input.getId(),
                input.getNome(),
                input.getDescricao(),
                input.getQuantidade()
        );
    }

    public ProdutoEntity toEntity(Produto input){
        return new ProdutoEntity(
                input.getId(),
                input.getNome(),
                input.getDescricao(),
                input.getQuantidade()
        );
    }
}
