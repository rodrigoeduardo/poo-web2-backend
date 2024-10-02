package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.Estoque;
import br.ufrn.imd.PooWeb2.model.Loja;
import br.ufrn.imd.PooWeb2.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaService {
    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private EstoqueService estoqueService;

    public Loja criarLoja(Loja loja) {
        Estoque estoque = new Estoque();
        estoque = estoqueService.salvarEstoque(estoque);
        loja.setEstoque(estoque);
        return lojaRepository.save(loja);
    }

    public Optional<Loja> buscarPorId(Long id) {
        return lojaRepository.findById(id);
    }

    public List<Loja> listarTodas() {
        return lojaRepository.findAll();
    }

    public Loja atualizarLoja(Long id, Loja lojaAtualizada) {
        Optional<Loja> lojaExistente = lojaRepository.findById(id);

        if (lojaExistente.isPresent()) {
            Loja loja = lojaExistente.get();
            loja.setNome(lojaAtualizada.getNome());
            loja.setEndereco(lojaAtualizada.getEndereco());
            loja.setEstoque(lojaAtualizada.getEstoque());
            return lojaRepository.save(loja);
        } else {
            throw new RuntimeException("Loja não encontrada com ID: " + id);
        }
    }

    public void deletarLoja(Long id) {
        lojaRepository.deleteById(id);
    }
}