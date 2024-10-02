package br.ufrn.imd.PooWeb2.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequest {
    private Long produtoId;
    private Integer quantidade;
}
