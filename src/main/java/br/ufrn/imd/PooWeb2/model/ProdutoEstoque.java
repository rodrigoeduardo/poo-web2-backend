package br.ufrn.imd.PooWeb2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProdutoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "estoqueId")
    private Estoque estoque;

    private Integer quantidade;
}