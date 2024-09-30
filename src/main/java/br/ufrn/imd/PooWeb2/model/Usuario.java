package br.ufrn.imd.PooWeb2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "papelId")
    private Papel papel;
}
