package luizsantos.com.estoquemercadinho.service;

import luizsantos.com.estoquemercadinho.entity.Produto;
import luizsantos.com.estoquemercadinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Operação de CRUD: Criar/Salvar Produto
    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Operação de CRUD: Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Operação de CRUD: Buscar por ID
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Operação de CRUD: Atualizar Produto
    @Transactional
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    // Operação de CRUD: Deletar Produto
    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Produto não encontrado.");
        }
        produtoRepository.deleteById(id);
    }
}