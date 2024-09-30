package br.ufrn.imd.PooWeb2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoqueId")
    private Estoque estoque;
}