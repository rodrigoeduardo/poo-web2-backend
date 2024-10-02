package br.ufrn.imd.PooWeb2.controller;

import br.ufrn.imd.PooWeb2.model.ProdutoEstoque;
import br.ufrn.imd.PooWeb2.service.ProdutoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/produto-estoque")
public class ProdutoEstoqueController {
    @Autowired
    private ProdutoEstoqueService produtoEstoqueService;

    @PostMapping
    public ResponseEntity<ProdutoEstoque> criarProdutoEstoque(@RequestBody ProdutoEstoque produtoEstoque) {
        ProdutoEstoque novoProdutoEstoque = produtoEstoqueService.salvarProdutoEstoque(produtoEstoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProdutoEstoque);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEstoque> buscarPorId(@PathVariable Long id) {
        Optional<ProdutoEstoque> produtoEstoque = produtoEstoqueService.buscarPorId(id);
        return produtoEstoque.map(estoque -> ResponseEntity.status(HttpStatus.OK).body(estoque)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEstoque>> listarTodos() {
        List<ProdutoEstoque> produtosEstoque = produtoEstoqueService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(produtosEstoque);
    }

    @GetMapping("/estoque/{estoqueId}")
    public ResponseEntity<List<ProdutoEstoque>> buscarPorEstoque(@PathVariable Long estoqueId) {
        List<ProdutoEstoque> produtosEstoque = produtoEstoqueService.buscarPorEstoque(estoqueId);
        return ResponseEntity.status(HttpStatus.OK).body(produtosEstoque);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<ProdutoEstoque>> buscarPorProduto(@PathVariable Long produtoId) {
        List<ProdutoEstoque> produtosEstoque = produtoEstoqueService.buscarPorProduto(produtoId);
        return ResponseEntity.status(HttpStatus.OK).body(produtosEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEstoque> atualizarProdutoEstoque(@PathVariable Long id, @RequestBody ProdutoEstoque produtoEstoque) {
        try {
            ProdutoEstoque produtoEstoqueAtualizado = produtoEstoqueService.atualizarProdutoEstoque(id, produtoEstoque);
            return ResponseEntity.status(HttpStatus.OK).body(produtoEstoqueAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoEstoque(@PathVariable Long id) {
        Optional<ProdutoEstoque> produtoEstoque = produtoEstoqueService.buscarPorId(id);
        if (produtoEstoque.isPresent()) {
            produtoEstoqueService.deletarProdutoEstoque(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}