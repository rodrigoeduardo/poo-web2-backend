package br.ufrn.imd.PooWeb2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Papel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    @ElementCollection(targetClass = Permissao.class)
    @CollectionTable(name = "papel_permissoes", joinColumns = @JoinColumn(name = "papelId"))
    @Enumerated(EnumType.STRING)
    private List<Permissao> permissoes;
}
