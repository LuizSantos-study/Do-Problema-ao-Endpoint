package luizsantos.com.estoquemercadinho.repository;

import luizsantos.com.estoquemercadinho.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByProdutoId(Long produtoId);
}