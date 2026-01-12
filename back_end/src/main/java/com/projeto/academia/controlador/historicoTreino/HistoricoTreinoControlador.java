package com.projeto.academia.controlador.historicoTreino;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.cmd.historicoTreino.FinalizarTreinoCmd;
import com.projeto.academia.dto.historicoTreino.HistoricoTreinoDTO;
import com.projeto.academia.servico.historicoTreino.HistoricoTreinoServico;

@RestController
@RequestMapping("historico")
public class HistoricoTreinoControlador {

    @Autowired
    private HistoricoTreinoServico historicoTreinoServico;

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarTreino(@RequestBody @Valid FinalizarTreinoCmd cmd) {
        return ResponseEntity.ok(historicoTreinoServico.finalizarTreino(cmd));
    }

    @GetMapping("/calendario/{idAluno}")
    public ResponseEntity<List<HistoricoTreinoDTO>> obterCalendarioAluno(@PathVariable long idAluno) {
        return ResponseEntity.ok(historicoTreinoServico.obterCalendarioAluno(idAluno));
    }



}
