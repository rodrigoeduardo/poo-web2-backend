package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.Operacao;
import br.ufrn.imd.PooWeb2.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OperacaoService {
    @Autowired
    private OperacaoRepository operacaoRepository;

    public Operacao registrarOperacao(Operacao operacao) {
        return operacaoRepository.save(operacao);
    }

    public Optional<Operacao> buscarPorId(Long id) {
        return operacaoRepository.findById(id);
    }

    public List<Operacao> listarTodas() {
        return operacaoRepository.findAll();
    }

    public List<Operacao> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return operacaoRepository.findOperacoesByPeriodo(inicio, fim);
    }

    public void deletarOperacao(Long id) {
        operacaoRepository.deleteById(id);
    }

    public Operacao atualizarOperacao(Long id, Operacao novaOperacao) {
        Optional<Operacao> operacaoExistente = operacaoRepository.findById(id);

        if (operacaoExistente.isPresent()) {
            Operacao operacaoAtualizada = operacaoExistente.get();
            operacaoAtualizada.setProdutoEstoque(novaOperacao.getProdutoEstoque());
            operacaoAtualizada.setQuantidade(novaOperacao.getQuantidade());
            operacaoAtualizada.setTipo(novaOperacao.getTipo());
            operacaoAtualizada.setDataOperacao(novaOperacao.getDataOperacao());
            return operacaoRepository.save(operacaoAtualizada);
        } else {
            throw new RuntimeException("Operação não encontrada com ID: " + id);
        }
    }
}
