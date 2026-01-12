package com.projeto.academia.controlador.treino;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.cmd.treino.AtualizarTreinoCmd;
import com.projeto.academia.cmd.treino.CadastrarTreinoCmd;
import com.projeto.academia.dto.treino.TreinoDTO;
import com.projeto.academia.servico.treino.TreinoServico;

@RestController
@RequestMapping("treino")
public class TreinoControlador {

    @Autowired
    private TreinoServico treinoServico;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar (@RequestBody @Valid CadastrarTreinoCmd cmd) {
        return ResponseEntity.ok(treinoServico.cadastrarTreino(cmd));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarTreinoCmd cmd) {
        return ResponseEntity.ok(treinoServico.atualizarTreino(cmd));
    }

    @DeleteMapping("/excluir/{idTreino}")
    public ResponseEntity<String> excluir(@PathVariable long idTreino) {
        return ResponseEntity.ok(treinoServico.excluirTreino(idTreino));
    }

    @GetMapping("/{idTreino}")
    public ResponseEntity<TreinoDTO> obterTreino(@PathVariable long idTreino) {
        return ResponseEntity.ok(treinoServico.obterTreino(idTreino));
    }
}
