package br.ufrn.imd.PooWeb2.controller;

import br.ufrn.imd.PooWeb2.model.Loja;
import br.ufrn.imd.PooWeb2.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/lojas")
public class LojaController {
    @Autowired
    private LojaService lojaService;

    @PostMapping
    public ResponseEntity<Loja> criarLoja(@RequestBody Loja loja) {
        Loja novaLoja = lojaService.criarLoja(loja);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaLoja);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loja> buscarPorId(@PathVariable Long id) {
        Optional<Loja> loja = lojaService.buscarPorId(id);
        return loja.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Loja>> listarTodas() {
        List<Loja> lojas = lojaService.listarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(lojas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loja> atualizarLoja(@PathVariable Long id, @RequestBody Loja loja) {
        try {
            Loja lojaAtualizada = lojaService.atualizarLoja(id, loja);
            return ResponseEntity.status(HttpStatus.OK).body(lojaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLoja(@PathVariable Long id) {
        Optional<Loja> loja = lojaService.buscarPorId(id);
        if (loja.isPresent()) {
            lojaService.deletarLoja(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}