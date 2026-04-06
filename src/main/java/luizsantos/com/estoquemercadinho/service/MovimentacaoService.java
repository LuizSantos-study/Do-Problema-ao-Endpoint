package luizsantos.com.estoquemercadinho.service;

import luizsantos.com.estoquemercadinho.entity.Movimentacao;
import luizsantos.com.estoquemercadinho.entity.Produto;
import luizsantos.com.estoquemercadinho.entity.Tipo;
import luizsantos.com.estoquemercadinho.repository.MovimentacaoRepository;
import luizsantos.com.estoquemercadinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Movimentacao registrar(Movimentacao movimentacao) {
        Produto produto = produtoRepository.findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (movimentacao.getTipo() == Tipo.SAIDA) {
            validarSaida(produto, movimentacao.getQuantidade());
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - movimentacao.getQuantidade());
        } else if (movimentacao.getTipo() == Tipo.ENTRADA) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + movimentacao.getQuantidade());
        }

        produtoRepository.save(produto);

        movimentacao.setDataHora(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    private void validarSaida(Produto produto, Integer quantidadeSolicitada) {
        if (produto.getQuantidadeEstoque() <= 0) {
            throw new RuntimeException("Operação cancelada: O estoque deste produto está zerado.");
        }

        if (produto.getQuantidadeEstoque() < quantidadeSolicitada) {
            throw new RuntimeException("Operação cancelada: Estoque insuficiente (Disponível: "
                    + produto.getQuantidadeEstoque() + ").");
        }
    }

    public List<Movimentacao> listarHistorico() {
        return movimentacaoRepository.findAll();
    }
}