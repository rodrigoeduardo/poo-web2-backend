package br.ufrn.imd.PooWeb2.service;

import br.ufrn.imd.PooWeb2.model.Papel;
import br.ufrn.imd.PooWeb2.repository.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PapelService {
    @Autowired
    private PapelRepository papelRepository;

    public Papel salvarPapel(Papel papel) {
        return papelRepository.save(papel);
    }

    public Optional<Papel> buscarPorId(Long id) {
        return papelRepository.findById(id);
    }

    public List<Papel> listarTodos() {
        return papelRepository.findAll();
    }

    public Papel atualizarPapel(Long id, Papel papelAtualizado) {
        Optional<Papel> papelExistente = papelRepository.findById(id);

        if (papelExistente.isPresent()) {
            Papel papel = papelExistente.get();
            papel.setNome(papelAtualizado.getNome());
            papel.setPermissoes(papelAtualizado.getPermissoes());
            return papelRepository.save(papel);
        } else {
            throw new RuntimeException("Papel não encontrado com ID: " + id);
        }
    }

    public void deletarPapel(Long id) {
        papelRepository.deleteById(id);
    }
}