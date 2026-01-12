package com.projeto.academia.servico.treino;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.academia.cmd.treino.AtualizarTreinoCmd;
import com.projeto.academia.cmd.treino.CadastrarTreinoCmd;
import com.projeto.academia.dao.treino.TreinoDAO;
import com.projeto.academia.dto.treino.TreinoDTO;
import com.projeto.academia.util.ValidadorUtil;

@Service
public class TreinoServico {

    private static final String LABEL_DADOS_CADASTRO = "Dados de cadastro";
    private static final String LABEL_ID = "ID do Treino";
    private static final String LABEL_NOME = "Nome do Treino";
    private static final String LABEL_DADOS_ATUALIZACAO = "Dados de atualização";
    private static final String LABEL_ID_ALUNO = "ID do Aluno";

    private static final String MSG_ERRO_TREINO_NAO_ENCONTRADO = "Treino não encontrado";

    private static final String MSG_SUCESSO_CADASTRO = "Treino cadastrado com sucesso.";
    private static final String MSG_SUCESSO_ATUALIZACAO = "Dados do treino atualizados com sucesso";
    private static final String MSG_SUCESSO_EXCLUSAO = "Treino removido com sucesso";

    @Autowired
    private TreinoDAO treinoDAO;

    @Transactional
    public String cadastrarTreino(@Valid CadastrarTreinoCmd cmd) {
        validarCmdCadastrar(cmd);
        treinoDAO.cadastrarTreino(cmd);
        return MSG_SUCESSO_CADASTRO;
    }

    @Transactional
    public String atualizarTreino(@Valid AtualizarTreinoCmd cmd) {
        validarCmdAtualizar(cmd);
        validarExistenciaTreino(cmd.getIdTreino());
        treinoDAO.atualizarTreino(cmd);
        return MSG_SUCESSO_ATUALIZACAO;
    }

    public String excluirTreino(long id) {
        ValidadorUtil.validarIdPositivo(id, LABEL_ID);
        validarExistenciaTreino(id);
        treinoDAO.excluirTreino(id);
        return MSG_SUCESSO_EXCLUSAO;
    }

    public TreinoDTO obterTreino(long id) {
        ValidadorUtil.validarIdPositivo(id, LABEL_ID);
        TreinoDTO treino = treinoDAO.getTreinoPorId(id);
        validarTreinoEncontrado(treino);
        return treino;
    }

    private static void validarCmdCadastrar(CadastrarTreinoCmd cmd) {
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_CADASTRO);
        ValidadorUtil.validarTextoObrigatorio(cmd.getNomeTreino(), LABEL_NOME);
        ValidadorUtil.validarIdPositivo(cmd.getIdAluno(), LABEL_ID_ALUNO);
    }

    private static void validarCmdAtualizar(AtualizarTreinoCmd cmd) {
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_ATUALIZACAO);
        ValidadorUtil.validarIdPositivo(cmd.getIdTreino(), LABEL_ID);
        ValidadorUtil.validarTextoObrigatorio(cmd.getNomeTreino(), LABEL_NOME);
    }

    private void validarExistenciaTreino(long id) {
        if (treinoDAO.getTreinoPorId(id) == null) {
            throw new IllegalArgumentException(MSG_ERRO_TREINO_NAO_ENCONTRADO);
        }
    }

    private static void validarTreinoEncontrado(TreinoDTO treino) {
        if (treino == null) {
            throw new IllegalArgumentException(MSG_ERRO_TREINO_NAO_ENCONTRADO);
        }
    }
}