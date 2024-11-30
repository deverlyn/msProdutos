package com.fiap.msProdutos.controller.produto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fiap.msProdutos.application.usecases.produto.*;
import com.fiap.msProdutos.domain.entity.produto.Produto;
import com.fiap.msProdutos.controller.produto.ProdutoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

public class ProdutoControllerTest {

    @Mock
    private AdicionarProdutos adicionarProdutos;

    @Mock
    private CadastrarProduto cadastrarProduto;

    @Mock
    private ChecarUmaListaDeProdutos checarUmaListaDeProdutos;

    @Mock
    private ChecarUmProduto checarUmProduto;

    @Mock
    private ConsultarProdutosDisponiveis cconsultarProdutosDisponiveis;

    @Mock
    private ConsultarTodosOsProdutos consultarTodosOsProdutos;

    @Mock
    private ConsultarUmProduto consultarUmProduto;

    @Mock
    private ExcluirProduto excluirProduto;

    @Mock
    private VenderProduto venderProduto;

    @InjectMocks
    private ProdutoController produtoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void testAdicionarProdutos() throws Exception {
        Long id = 1L;
        int quantidade = 10;

        mockMvc.perform(put("/produtos/adicionar/{id}/{quantidade}", id, quantidade))
                .andExpect(status().isOk());

        verify(adicionarProdutos).adicionarProdutos(id, quantidade);
    }

    @Test
    void testCadastrarProduto() throws Exception {
        ProdutoDTO dto = new ProdutoDTO(1L, "Produto 1", "Descrição 1", 10);
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);

        when(cadastrarProduto.cadastrarProduto(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":\"Produto 1\",\"descricao\":\"Descrição 1\",\"quantidade\":10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto 1"))
                .andExpect(jsonPath("$.descricao").value("Descrição 1"))
                .andExpect(jsonPath("$.quantidade").value(10));

        verify(cadastrarProduto).cadastrarProduto(any(Produto.class));
    }

    @Test
    void testConsultarProdutosDisponiveis() throws Exception {
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);
        List<Produto> produtos = List.of(produto);

        when(cconsultarProdutosDisponiveis.consultarProdutosDisponiveis()).thenReturn(produtos);

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[0].descricao").value("Descrição 1"))
                .andExpect(jsonPath("$[0].quantidade").value(10));

        verify(cconsultarProdutosDisponiveis).consultarProdutosDisponiveis();
    }

    @Test
    void testChecarUmaListaDeProdutos() throws Exception {
        List<ProdutoPedidoDTO> pedidoDTO = List.of(new ProdutoPedidoDTO(1L, 10));
        List<Produto> produtos = List.of(new Produto(1L, 10));

        when(checarUmaListaDeProdutos.checarUmaListaDeProdutos(anyList())).thenReturn(true);

        mockMvc.perform(post("/produtos/consultar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":1,\"quantidade\":10}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(checarUmaListaDeProdutos).checarUmaListaDeProdutos(anyList());
    }

    @Test
    void testConsultarProduto() throws Exception {
        Long id = 1L;
        int quantidade = 10;

        when(checarUmProduto.checarUmProduto(id, quantidade)).thenReturn(true);

        mockMvc.perform(get("/produtos/consultar/{id}/{quantidade}", id, quantidade))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(checarUmProduto).checarUmProduto(id, quantidade);
    }

    @Test
    void testConsultarTodosOsProdutos() throws Exception {
        Produto produto = new Produto(1L, "Produto 1", "Descrição 1", 10);
        List<Produto> produtos = List.of(produto);

        when(consultarTodosOsProdutos.consultarTodosOsProdutos()).thenReturn(produtos);

        mockMvc.perform(get("/produtos/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[0].descricao").value("Descrição 1"))
                .andExpect(jsonPath("$[0].quantidade").value(10));

        verify(consultarTodosOsProdutos).consultarTodosOsProdutos();
    }

    @Test
    void testConsultarUmProduto() throws Exception {
        Long id = 1L;
        Produto produto = new Produto(id, "Produto 1", "Descrição 1", 10);

        when(consultarUmProduto.consultarUmProduto(id)).thenReturn(produto);

        mockMvc.perform(get("/produtos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto 1"))
                .andExpect(jsonPath("$.descricao").value("Descrição 1"))
                .andExpect(jsonPath("$.quantidade").value(10));

        verify(consultarUmProduto).consultarUmProduto(id);
    }

    @Test
    void testExcluirProduto() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/produtos")
                        .param("id", id.toString()))
                .andExpect(status().isOk());

        verify(excluirProduto).excluirProduto(id);
    }

    @Test
    void testVenderProduto() throws Exception {
        Long id = 1L;
        int quantidade = 10;

        mockMvc.perform(put("/produtos/vender/{id}/{quantidade}", id, quantidade))
                .andExpect(status().isOk());

        verify(venderProduto).venderProduto(id, quantidade);
    }
}
