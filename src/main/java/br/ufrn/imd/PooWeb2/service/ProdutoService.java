package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.Produto;
import br.ufrn.imd.PooWeb2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Long id, Produto novoProduto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produtoAtualizado = produtoExistente.get();
            produtoAtualizado.setNome(novoProduto.getNome());
            produtoAtualizado.setDescricao(novoProduto.getDescricao());
            produtoAtualizado.setPrecoCusto(novoProduto.getPrecoCusto());
            produtoAtualizado.setPrecoVenda(novoProduto.getPrecoVenda());
            return produtoRepository.save(produtoAtualizado);
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}