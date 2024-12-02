package com.fiap.msProdutos.controller.produto;

import com.fiap.msProdutos.application.usecases.produto.*;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
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
    @Operation(summary = "Adicionar produtos", description = "Adiciona a quantidade especificada ao estoque de um produto.")
    public void adicionarProdutos(@PathVariable Long id, @PathVariable int quantidade) {
        adicionarProdutos.adicionarProdutos(id, quantidade);
    }

    @PostMapping
    @Operation(summary = "Cadastrar produtos", description = "Cadastra um novo produto já com uma quantidade.")
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
    @Operation(summary = "Consultar produtos disponíveis", description = "Retorna uma lista com apenas produtos disponíveis em estoque.")
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
    @Operation(summary = "Validar lista de produtos", description = "Valida a disponibilidade de uma lista de produtos.")
    public boolean checarUmaListaDeProdutos(@RequestBody List<ProdutoPedidoDTO> pedidoDTO) {
        List<Produto> produtos = new ArrayList<>();
        pedidoDTO.forEach(v -> produtos.add(new Produto(v.id(), v.quantidade())));
        return checarUmaListaDeProdutos.checarUmaListaDeProdutos(produtos);
    }

    @GetMapping("/consultar/{id}/{quantidade}")
    @Operation(summary = "Validar produto", description = "Valida a disponibilidade de um produto.")
    public Boolean consultarProduto(@PathVariable Long id, @PathVariable int quantidade) {
        return checarUmProduto.checarUmProduto(id, quantidade);
    }

    @GetMapping("/todos")
    @Operation(summary = "Consultar todos os produtos", description = "Consulta todos os produtos.")
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
    @Operation(summary = "Consultar um produto", description = "Consulta um produto.")
    public ProdutoDTO consultarUmProduto(@PathVariable Long id) {
        Produto produto = consultarUmProduto.consultarUmProduto(id);
        return new ProdutoDTO(produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getQuantidade()
        );
    }

    @DeleteMapping
    @Operation(summary = "Excluir um produto", description = "Exclui um produto do estoque.")
    public void excluirProduto(Long id) {
        excluirProduto.excluirProduto(id);
    }

    @PutMapping("/vender/{id}/{quantidade}")
    @Operation(summary = "Vender produto", description = "Realiza a venda de um produto, subtraindo sua quantidade.")
    public void venderProduto(@PathVariable Long id, @PathVariable int quantidade) {
        venderProduto.venderProduto(id, quantidade);
    }
}
