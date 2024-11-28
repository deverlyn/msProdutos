package com.fiap.msProdutos.infra.controller.produto;

import com.fiap.msProdutos.application.usecases.produto.*;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final AdicionarProdutos adicionarProdutos;
    private final CadastrarProduto cadastrarProduto;
    private final ChecarUmaListaDeProdutos checarUmaListaDeProdutos;
    private final ChecarUmProduto checarUmProduto;
    private final ConsultarProdutosDisponiveis cconsultarProdutosDisponiveis;
    private final ConsultarTodosOsProdutos consultarTodosOsProdutos;
    private final ConsultarUmProduto consultarUmProduto;
    private final ExcluirProduto excluirProduto;
    private final VenderProduto venderProduto;

    public ProdutoController(AdicionarProdutos adicionarProdutos, CadastrarProduto cadastrarProduto, ChecarUmaListaDeProdutos checarUmaListaDeProdutos, ChecarUmProduto checarUmProduto, ConsultarProdutosDisponiveis cconsultarProdutosDisponiveis, ConsultarTodosOsProdutos consultarTodosOsProdutos, ConsultarUmProduto consultarUmProduto, ExcluirProduto excluirProduto, VenderProduto venderProduto) {
        this.adicionarProdutos = adicionarProdutos;
        this.cadastrarProduto = cadastrarProduto;
        this.checarUmaListaDeProdutos = checarUmaListaDeProdutos;
        this.checarUmProduto = checarUmProduto;
        this.cconsultarProdutosDisponiveis = cconsultarProdutosDisponiveis;
        this.consultarTodosOsProdutos = consultarTodosOsProdutos;
        this.consultarUmProduto = consultarUmProduto;
        this.excluirProduto = excluirProduto;
        this.venderProduto = venderProduto;
    }

    @PutMapping("/adicionar/{id}/{quantidade}")
    public void adicionarProdutos(@PathVariable Long id, @PathVariable int quantidade) {
        adicionarProdutos.adicionarProdutos(id, quantidade);
    }

    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO dto) {
        Produto salvo = cadastrarProduto.cadastrarProduto(new Produto(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.quantidade()
        ));
        return new ProdutoDTO(salvo.getId(), salvo.getNome(), salvo.getDescricao(), salvo.getQuantidade());
    }

    @GetMapping
    public List<ProdutoDTO> consultarProdutosDisponiveis() {
        List<ProdutoDTO> produtos = new ArrayList<>();
        cconsultarProdutosDisponiveis.consultarProdutosDisponiveis().forEach(v ->
                produtos.add(new ProdutoDTO(
                        v.getId(),
                        v.getNome(),
                        v.getDescricao(),
                        v.getQuantidade()
                )));
        return produtos;
    }

    @PostMapping("/consultar")
    public boolean checarUmaListaDeProdutos(@RequestBody List<ProdutoPedidoDTO> pedidoDTO) {
        List<Produto> produtos = new ArrayList<>();
        pedidoDTO.forEach(v -> produtos.add(new Produto(v.id(), v.quantidade())));
        return checarUmaListaDeProdutos.checarUmaListaDeProdutos(produtos);
    }

    @GetMapping("/consultar/{id}/{quantidade}")
    public Boolean consultarProduto(@PathVariable Long id, @PathVariable int quantidade) {
        return checarUmProduto.checarUmProduto(id, quantidade);
    }

    @GetMapping("/todos")
    public List<ProdutoDTO> consultarTodosOsProdutos() {
        List<ProdutoDTO> produtos = new ArrayList<>();
        consultarTodosOsProdutos.consultarTodosOsProdutos().forEach(v ->
                produtos.add(new ProdutoDTO(
                        v.getId(),
                        v.getNome(),
                        v.getDescricao(),
                        v.getQuantidade()
                )));
        return produtos;
    }

    @GetMapping("/{id}")
    public ProdutoDTO consultarUmProduto(@PathVariable Long id) {
        Produto produto = consultarUmProduto.consultarUmProduto(id);
        return new ProdutoDTO(produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade()
        );
    }

    @DeleteMapping
    public void excluirProduto(Long id) {
        excluirProduto.excluirProduto(id);
    }

    @PutMapping("/vender/{id}/{quantidade}")
    public void venderProduto(@PathVariable Long id, @PathVariable int quantidade) {
        venderProduto.venderProduto(id, quantidade);
    }
}
