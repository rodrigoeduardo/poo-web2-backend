package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.ProdutoEstoque;
import br.ufrn.imd.PooWeb2.repository.ProdutoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoEstoqueService {
    @Autowired
    private ProdutoEstoqueRepository produtoEstoqueRepository;

    public ProdutoEstoque salvarProdutoEstoque(ProdutoEstoque produtoEstoque) {
        return produtoEstoqueRepository.save(produtoEstoque);
    }

    public Optional<ProdutoEstoque> buscarPorId(Long id) {
        return produtoEstoqueRepository.findById(id);
    }

    public List<ProdutoEstoque> listarTodos() {
        return produtoEstoqueRepository.findAll();
    }

    public List<ProdutoEstoque> buscarPorEstoque(Long estoqueId) {
        return produtoEstoqueRepository.findByEstoqueId(estoqueId);
    }

    public List<ProdutoEstoque> buscarPorProduto(Long produtoId) {
        return produtoEstoqueRepository.findByProdutoId(produtoId);
    }

    public ProdutoEstoque atualizarProdutoEstoque(Long id, ProdutoEstoque produtoEstoqueAtualizado) {
        Optional<ProdutoEstoque> produtoEstoqueExistente = produtoEstoqueRepository.findById(id);

        if (produtoEstoqueExistente.isPresent()) {
            ProdutoEstoque produtoEstoque = produtoEstoqueExistente.get();
            produtoEstoque.setProduto(produtoEstoqueAtualizado.getProduto());
            produtoEstoque.setEstoque(produtoEstoqueAtualizado.getEstoque());
            produtoEstoque.setQuantidade(produtoEstoqueAtualizado.getQuantidade());
            return produtoEstoqueRepository.save(produtoEstoque);
        } else {
            throw new RuntimeException("ProdutoEstoque não encontrado com ID: " + id);
        }
    }

    public void deletarProdutoEstoque(Long id) {
        produtoEstoqueRepository.deleteById(id);
    }
}