package br.ufrn.imd.PooWeb2.repository;

import br.ufrn.imd.PooWeb2.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}