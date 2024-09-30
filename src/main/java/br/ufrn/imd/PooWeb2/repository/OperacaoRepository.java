package br.ufrn.imd.PooWeb2.repository;

import br.ufrn.imd.PooWeb2.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
    @Query("SELECT o FROM Operacao o WHERE o.dataOperacao BETWEEN :inicio AND :fim")
    List<Operacao> findOperacoesByPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
