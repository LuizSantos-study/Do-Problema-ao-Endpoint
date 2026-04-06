package luizsantos.com.estoquemercadinho.controller;

import luizsantos.com.estoquemercadinho.entity.Movimentacao;
import luizsantos.com.estoquemercadinho.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Movimentacao movimentacao) {
        try {
            Movimentacao novaMovimentacao = movimentacaoService.registrar(movimentacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaMovimentacao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarHistorico() {
        List<Movimentacao> historico = movimentacaoService.listarHistorico();
        return ResponseEntity.ok(historico);
    }
}