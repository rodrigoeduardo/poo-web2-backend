package br.ufrn.imd.PooWeb2.repository;

import br.ufrn.imd.PooWeb2.model.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Long> {
    List<ProdutoEstoque> findByEstoqueId(Long estoqueId);
    List<ProdutoEstoque> findByProdutoId(Long produtoId);
}