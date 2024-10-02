package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.*;
import br.ufrn.imd.PooWeb2.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private OperacaoService operacaoService;

    public Estoque salvarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Optional<Estoque> buscarPorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque atualizarEstoque(Long id, Estoque estoqueAtualizado) {
        Optional<Estoque> estoqueExistente = estoqueRepository.findById(id);

        if (estoqueExistente.isPresent()) {
            Estoque estoque = estoqueExistente.get();
            estoque.setProdutos(estoqueAtualizado.getProdutos());
            return estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Estoque não encontrado com ID: " + id);
        }
    }

    public void deletarEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public Estoque adicionarProduto(Long estoqueId, Produto produto, Integer quantidade) {
        Optional<Estoque> estoqueExistente = estoqueRepository.findById(estoqueId);

        if (estoqueExistente.isPresent()) {
            Estoque estoque = estoqueExistente.get();

            Optional<ProdutoEstoque> produtoEstoqueExistente = estoque.getProdutos().stream()
                    .filter(pe -> pe.getProduto().getId().equals(produto.getId()))
                    .findFirst();

            ProdutoEstoque produtoEstoque;
            if (produtoEstoqueExistente.isPresent()) {
                produtoEstoque = produtoEstoqueExistente.get();
                produtoEstoque.setQuantidade(produtoEstoque.getQuantidade() + quantidade);
            } else {
                produtoEstoque = new ProdutoEstoque();
                produtoEstoque.setProduto(produto);
                produtoEstoque.setEstoque(estoque);
                produtoEstoque.setQuantidade(quantidade);
                estoque.getProdutos().add(produtoEstoque);
            }

            Operacao operacao = new Operacao();
            operacao.setDataOperacao(LocalDateTime.now());
            operacao.setQuantidade(quantidade);
            operacao.setTipo(TipoOperacao.ENTRADA);
            operacao.setProdutoEstoque(produtoEstoque);
            operacaoService.registrarOperacao(operacao);

            return estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Estoque não encontrado com ID: " + estoqueId);
        }
    }

    public Estoque removerProduto(Long estoqueId, Produto produto, Integer quantidade) {
        Optional<Estoque> estoqueExistente = estoqueRepository.findById(estoqueId);

        if (estoqueExistente.isPresent()) {
            Estoque estoque = estoqueExistente.get();

            Optional<ProdutoEstoque> produtoEstoqueExistente = estoque.getProdutos().stream()
                    .filter(pe -> pe.getProduto().getId().equals(produto.getId()))
                    .findFirst();

            if (produtoEstoqueExistente.isPresent()) {
                ProdutoEstoque produtoEstoque = produtoEstoqueExistente.get();
                int novaQuantidade = produtoEstoque.getQuantidade() - quantidade;

                if (novaQuantidade <= 0) {
                    estoque.getProdutos().remove(produtoEstoque);
                } else {
                    produtoEstoque.setQuantidade(novaQuantidade);
                }

                Operacao operacao = new Operacao();
                operacao.setDataOperacao(LocalDateTime.now());
                operacao.setQuantidade(quantidade);
                operacao.setTipo(TipoOperacao.SAIDA);
                operacao.setProdutoEstoque(produtoEstoque);
                operacaoService.registrarOperacao(operacao);

                return estoqueRepository.save(estoque);
            } else {
                throw new RuntimeException("Produto não encontrado no estoque.");
            }
        } else {
            throw new RuntimeException("Estoque não encontrado com ID: " + estoqueId);
        }
    }
}