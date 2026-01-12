package com.projeto.academia.controlador.exercicio;

import java.util.List;

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

import com.projeto.academia.cmd.exercicio.AtualizarExercicioCmd;
import com.projeto.academia.cmd.exercicio.CadastrarExercicioCmd;
import com.projeto.academia.dto.exercicio.ExercicioDTO;
import com.projeto.academia.dto.exercicio.ExerciciosAlunoDTO;
import com.projeto.academia.servico.exercicio.ExercicioServico;

@RestController
@RequestMapping("exercicio")
public class ExercicioControlador {

    @Autowired
    private ExercicioServico exercicioServico;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar (@RequestBody @Valid CadastrarExercicioCmd cmd) {
        return ResponseEntity.ok(exercicioServico.cadastrarExercicio(cmd));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar (@RequestBody @Valid AtualizarExercicioCmd cmd) {
        return ResponseEntity.ok(exercicioServico.atualizarExercicio(cmd));
    }

    @DeleteMapping("/excluir/{idExercicio}")
    public ResponseEntity<String> excluir(@PathVariable long idExercicio) {
        return ResponseEntity.ok(exercicioServico.excluirExercicio(idExercicio));
    }

    @GetMapping("/{idExercicio}")
    public ResponseEntity<ExercicioDTO> obterExercicio(@PathVariable long idExercicio) {
        return ResponseEntity.ok(exercicioServico.obterExercicio(idExercicio));
    }

    @GetMapping("/listar-exercicios-treino/{idTreino}")
    public ResponseEntity<List<ExercicioDTO>> listarExerciciosPorTreino(@PathVariable long idTreino){
        return ResponseEntity.ok(exercicioServico.listarExerciciosPorTreino(idTreino));
    }

    @GetMapping("/listar-exercicios-aluno/{idAluno}")
    public ResponseEntity<List<ExerciciosAlunoDTO>> listarExerciciosPorAluno(@PathVariable long idAluno) {
        return ResponseEntity.ok(exercicioServico.listarExerciciosPorAluno(idAluno));
    }

}
