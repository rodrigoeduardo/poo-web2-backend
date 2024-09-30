package br.ufrn.imd.PooWeb2.controller;

import br.ufrn.imd.PooWeb2.model.Operacao;
import br.ufrn.imd.PooWeb2.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/operacoes")
public class OperacaoController {
    @Autowired
    private OperacaoService operacaoService;

    @PostMapping
    public ResponseEntity<Operacao> registrarOperacao(@RequestBody Operacao operacao) {
        Operacao novaOperacao = operacaoService.registrarOperacao(operacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOperacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operacao> buscarPorId(@PathVariable Long id) {
        Optional<Operacao> operacao = operacaoService.buscarPorId(id);
        return operacao.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Operacao>> listarTodas() {
        List<Operacao> operacoes = operacaoService.listarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(operacoes);
    }

    @GetMapping("/buscar-por-periodo")
    public ResponseEntity<List<Operacao>> buscarPorPeriodo(
            @RequestParam("inicio") LocalDateTime inicio,
            @RequestParam("fim") LocalDateTime fim) {
        List<Operacao> operacoes = operacaoService.buscarPorPeriodo(inicio, fim);
        return ResponseEntity.status(HttpStatus.OK).body(operacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacao> atualizarOperacao(@PathVariable Long id, @RequestBody Operacao operacao) {
        try {
            Operacao operacaoAtualizada = operacaoService.atualizarOperacao(id, operacao);
            return ResponseEntity.status(HttpStatus.OK).body(operacaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOperacao(@PathVariable Long id) {
        Optional<Operacao> operacao = operacaoService.buscarPorId(id);
        if (operacao.isPresent()) {
            operacaoService.deletarOperacao(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}