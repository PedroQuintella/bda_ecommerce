package br.com.bda.ecommerce.controller;

import br.com.bda.ecommerce.model.entity.Produto;
import br.com.bda.ecommerce.model.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private ProdutoRepository produtoRepository;

    public ProdutoController(JdbcTemplate jdbcTemplate) {
        produtoRepository = new ProdutoRepository(jdbcTemplate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) throws Exception {
        return produtoRepository.cadastrar(produto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Produto> buscar() throws Exception {
        return produtoRepository.buscar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/busca-nome")
    public List<Produto> buscarPorNome(@RequestParam String nome) throws Exception {
        return produtoRepository.buscarPorNome(nome);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/busca-faixa-preco")
    public List<Produto> buscarPorFaixaDePreco(@RequestParam Float valorMinimo, Float valorMaximo) throws Exception {
        return produtoRepository.buscarPorFaixaDePreco(valorMinimo, valorMaximo);
    }

}
