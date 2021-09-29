package br.com.bda.ecommerce.model.repository;

import br.com.bda.ecommerce.model.entity.Produto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class ProdutoRepository {
    private JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Produto cadastrar(Produto produto) throws Exception {
        String sql = "INSERT INTO produto(id, nome, descricao, fotoUrl, dataCadastro, dataUltimaAtualizacao, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql,
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getFotoUrl(),
                produto.getDataCadastro(),
                produto.getDataUltimaAtualizacao(),
                produto.getPreco()
        );

        if(insert == 1) {
            return produto;
        }

        throw new Exception("Não foi possível cadastrar o Produto");
    }

    public List<Produto> buscar() throws Exception {
        String sql = "SELECT * FROM produto";
        List<Produto> search = (List<Produto>) jdbcTemplate.query(sql, new ProdutoMapper());

        if (search.size() > 0) {
            return (List<Produto>) jdbcTemplate.query(sql, new Object[]{}, new ProdutoMapper());
        }
        throw new Exception("Nenhum produto cadastrado");
    }

    public List<Produto> buscarPorNome(String nome) throws Exception {
        String sql = "SELECT * FROM produto WHERE nome = ?";
        List<Produto> search = (List<Produto>) jdbcTemplate.query(sql, new ProdutoMapper(), nome);

        if (search.size() > 0) {
            return (List<Produto>) jdbcTemplate.query(sql, new Object[]{nome}, new ProdutoMapper());
        }
        throw new Exception("Nenhum produto cadastrado com esse nome");
    }

    public List<Produto> buscarPorFaixaDePreco(Float valorMinimo, Float valorMaximo) throws Exception {
        String sql = "SELECT * FROM produto WHERE preco >= ? AND preco <= ?";
        List<Produto> search = (List<Produto>) jdbcTemplate.query(sql, new ProdutoMapper(), valorMinimo, valorMaximo);

        if (search.size() > 0) {
            return (List<Produto>) jdbcTemplate.query(sql, new Object[]{valorMinimo, valorMaximo}, new ProdutoMapper());
        }
        throw new Exception("Nenhum produto com preço dentro desta faixa de valores");
    }
}
