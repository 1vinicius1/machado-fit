package com.projeto.academia.servico.exercicio;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.academia.cmd.exercicio.AtualizarExercicioCmd;
import com.projeto.academia.cmd.exercicio.CadastrarExercicioCmd;
import com.projeto.academia.dao.exercicio.ExercicioDAO;
import com.projeto.academia.dto.exercicio.ExercicioDTO;
import com.projeto.academia.dto.exercicio.ExerciciosAlunoDTO;
import com.projeto.academia.util.ValidadorUtil;

@Service
public class ExercicioServico {

    private static final String LABEL_DADOS_CADASTRO = "Dados de cadastro";
    private static final String LABEL_DADOS_ATUALIZACAO = "Dados de atualização";
    private static final String LABEL_ID = "ID do Exercício";
    private static final String LABEL_NOME = "Nome do Exercício";
    private static final String LABEL_REPETICOES = "Quantidade de repetições";
    private static final String LABEL_CARGA = "Quantidade de carga";
    private static final String LABEL_TREINO_ID = "ID do treino";

    private static final String MSG_EXERCICIO_NAO_ENCONTRADO = "Exercício não encontrado";
    private static final String MSG_SUCESSO_CADASTRO = "Exercício cadastrado com sucesso.";
    private static final String MSG_SUCESSO_ATUALIZACAO = "Exercício atualizado com sucesso.";
    private static final String MSG_SUCESSO_EXCLUSAO = "Exercício removido com sucesso";

    @Autowired
    private ExercicioDAO exercicioDAO;


    /*
     * Método responsável pelo cadastro de um Exercício.
     */

    @Transactional
    public String cadastrarExercicio(@Valid CadastrarExercicioCmd cmd) {
        validarCmdCadastrar(cmd);
        exercicioDAO.cadastrarExercicio(cmd);
        return MSG_SUCESSO_CADASTRO;
    }

    /*
     * Método responsável pela atualização de um Exercício.
     */

    @Transactional
    public String atualizarExercicio (@Valid AtualizarExercicioCmd cmd) {
        validarCmdAtualizar(cmd);
        validarExistenciaExercicio(cmd.getIdExercicio());
        exercicioDAO.atualizarExercicio(cmd);
        return MSG_SUCESSO_ATUALIZACAO;
    }

    /*
     * Método responsável pela exclusão de um Exercícios
     */

    public String excluirExercicio(long id) {
        ValidadorUtil.validarIdPositivo(id, LABEL_ID);
        validarExistenciaExercicio(id);
        exercicioDAO.excluirExercicio(id);
        return MSG_SUCESSO_EXCLUSAO;
    }

    /*
     * Este método retorna um exercício a partir do id
     * do exercício desejado
     */

    public ExercicioDTO obterExercicio(long id) {
        ValidadorUtil.validarIdPositivo(id, LABEL_ID);
        ExercicioDTO exercicio = exercicioDAO.getExercicioPorId(id);
        validarExercicioEncontrado(exercicio);
        return exercicio;
    }

    /*
     * Este método retorna a lista dos exercícios
     * de um treino através do id deste treino
     */

    public List<ExercicioDTO> listarExerciciosPorTreino(long id) {
        return exercicioDAO.listarExerciciosPorTreino(id);
    }

    /*
     * Este método retorna a lista dos exercícios
     * de um aluno através do id deste aluno
     */

    public List<ExerciciosAlunoDTO> listarExerciciosPorAluno(long id) {
        return exercicioDAO.listarExerciciosPorAluno(id);
    }

    private static void validarCmdCadastrar(CadastrarExercicioCmd cmd) {
        ValidadorUtil.validarIdPositivo(cmd.getTreinoId(), LABEL_TREINO_ID);
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_CADASTRO);
        ValidadorUtil.validarTextoObrigatorio(cmd.getNome(), LABEL_NOME);
        ValidadorUtil.validarTextoObrigatorio(cmd.getRepeticoes(), LABEL_REPETICOES);
        ValidadorUtil.validarTextoObrigatorio(cmd.getCarga(), LABEL_CARGA);
    }

    private static void validarCmdAtualizar(AtualizarExercicioCmd cmd) {
        ValidadorUtil.validarIdPositivo(cmd.getIdExercicio(), LABEL_ID);
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_ATUALIZACAO);
        ValidadorUtil.validarTextoObrigatorio(cmd.getNome(), LABEL_NOME);
        ValidadorUtil.validarTextoObrigatorio(cmd.getRepeticoes(), LABEL_REPETICOES);
        ValidadorUtil.validarTextoObrigatorio(cmd.getCarga(), LABEL_CARGA);
    }

    private void validarExistenciaExercicio(long id) {
        if (exercicioDAO.getExercicioPorId(id) == null) {
            throw new IllegalArgumentException(MSG_EXERCICIO_NAO_ENCONTRADO);
        }
    }

    private static void validarExercicioEncontrado(ExercicioDTO exercicio) {
        if (exercicio == null) {
            throw new IllegalArgumentException(MSG_EXERCICIO_NAO_ENCONTRADO);
        }
    }
}

