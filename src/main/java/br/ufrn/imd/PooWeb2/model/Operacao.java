package br.ufrn.imd.PooWeb2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Operacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produtoEstoqueId")
    private ProdutoEstoque produtoEstoque;

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    private LocalDateTime dataOperacao;
}