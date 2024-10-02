package br.ufrn.imd.PooWeb2.controller;

import br.ufrn.imd.PooWeb2.model.Estoque;
import br.ufrn.imd.PooWeb2.model.Produto;
import br.ufrn.imd.PooWeb2.model.dto.ProdutoRequest;
import br.ufrn.imd.PooWeb2.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/estoques")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.salvarEstoque(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstoque);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Long id) {
        Optional<Estoque> estoque = estoqueService.buscarPorId(id);
        return estoque.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        List<Estoque> estoques = estoqueService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(estoques);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        try {
            Estoque estoqueAtualizado = estoqueService.atualizarEstoque(id, estoque);
            return ResponseEntity.status(HttpStatus.OK).body(estoqueAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        Optional<Estoque> estoque = estoqueService.buscarPorId(id);
        if (estoque.isPresent()) {
            estoqueService.deletarEstoque(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{estoqueId}/adicionar-produto")
    public ResponseEntity<Estoque> adicionarProduto(@PathVariable Long estoqueId,
                                                    @RequestBody ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setId(produtoRequest.getProdutoId());
        Estoque estoqueAtualizado = estoqueService.adicionarProduto(estoqueId, produto, produtoRequest.getQuantidade());
        return ResponseEntity.status(HttpStatus.OK).body(estoqueAtualizado);
    }

    @PostMapping("/{estoqueId}/remover-produto")
    public ResponseEntity<Estoque> removerProduto(@PathVariable Long estoqueId,
                                                  @RequestBody ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setId(produtoRequest.getProdutoId());
        Estoque estoqueAtualizado = estoqueService.removerProduto(estoqueId, produto, produtoRequest.getQuantidade());
        return ResponseEntity.status(HttpStatus.OK).body(estoqueAtualizado);
    }
}