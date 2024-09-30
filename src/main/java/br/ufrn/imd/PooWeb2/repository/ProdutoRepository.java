package br.ufrn.imd.PooWeb2.repository;

import br.ufrn.imd.PooWeb2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
