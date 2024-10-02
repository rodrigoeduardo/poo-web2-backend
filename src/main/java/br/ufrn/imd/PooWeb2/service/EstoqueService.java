package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.Estoque;
import br.ufrn.imd.PooWeb2.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

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
}