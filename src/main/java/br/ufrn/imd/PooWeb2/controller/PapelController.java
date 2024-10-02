package br.ufrn.imd.PooWeb2.controller;

import br.ufrn.imd.PooWeb2.model.Papel;
import br.ufrn.imd.PooWeb2.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/papeis")
public class PapelController {
    @Autowired
    private PapelService papelService;

    @PostMapping
    public ResponseEntity<Papel> criarPapel(@RequestBody Papel papel) {
        Papel novoPapel = papelService.salvarPapel(papel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPapel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Papel> buscarPorId(@PathVariable Long id) {
        Optional<Papel> papel = papelService.buscarPorId(id);
        return papel.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(papel.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<Papel>> listarTodos() {
        List<Papel> papeis = papelService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(papeis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Papel> atualizarPapel(@PathVariable Long id, @RequestBody Papel papel) {
        try {
            Papel papelAtualizado = papelService.atualizarPapel(id, papel);
            return ResponseEntity.status(HttpStatus.OK).body(papelAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPapel(@PathVariable Long id) {
        Optional<Papel> papel = papelService.buscarPorId(id);
        if (papel.isPresent()) {
            papelService.deletarPapel(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}