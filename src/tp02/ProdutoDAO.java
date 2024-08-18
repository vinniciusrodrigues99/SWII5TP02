package tp02;
//Vinnicius Oliveira Rodrigues - cb3021602
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private List<Produto> produtos;
    private static int chaveSequencial = 1;

    public ProdutoDAO() {
        this.produtos = new ArrayList<>();
        
        // Adicionando produtos de exemplo para teste
        Produto produto1 = new Produto("Produto A", 10, "Descrição A", 100, 50.0);
        produto1.setId(chaveSequencial++);
        produtos.add(produto1);

        Produto produto2 = new Produto("Produto B", 20, "Descrição B", 200, 150.0);
        produto2.setId(chaveSequencial++);
        produtos.add(produto2);
    }

    public List<Produto> listAllProdutos() {
        return produtos;
    }

    public void insertProduto(Produto produto) {
        produto.setId(chaveSequencial++);
        produtos.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());  // Log para verificar a inserção
    }

    public Produto getProduto(int id) {
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst().orElse(null);
    }

    public void updateProduto(Produto produto) {
        Produto produtoExistente = getProduto(produto.getId());
        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setUnidadeCompra(produto.getUnidadeCompra());
            produtoExistente.setDescricao(produto.getDescricao());
            produtoExistente.setQtdPrevistoMes(produto.getQtdPrevistoMes());
            produtoExistente.setPrecoMaxComprado(produto.getPrecoMaxComprado());
        }
    }

    public void deleteProduto(Produto produto) {
        produtos.removeIf(p -> p.getId() == produto.getId());
    }
}

