package com.projeto.academia.servico.historicoTreino;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.academia.cmd.historicoTreino.FinalizarTreinoCmd;
import com.projeto.academia.dao.historicoTreino.HistoricoTreinoDAO;
import com.projeto.academia.dao.usuario.UsuarioDao;
import com.projeto.academia.dto.historicoTreino.HistoricoTreinoDTO;
import com.projeto.academia.util.ValidadorUtil;

@Service
public class HistoricoTreinoServico {

    private static final String MSG_SUCESSO_FINALIZACAO = "Treino finalizado com sucesso";
    private static final String LABEL_DADOS_FINALIZACAO = "Dados da finalização";
    private static final String LABEL_ID_ALUNO = "ID do Aluno";
    private static final String LABEL_ID_TREINO = "ID do Treino";

    private static final String MSG_ERRO_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";

    @Autowired
    private HistoricoTreinoDAO historicoTreinoDAO;

    @Autowired
    private UsuarioDao usuarioDao;

    /*
     * Método responsável por finalizar treino,
     * subindo a data do treino e o nome do treino
     * para tabela HistoricoTreino.
     *
     */

    public String finalizarTreino(@Valid FinalizarTreinoCmd cmd) {
        validarCmdFinalizarTreino(cmd);
        validarExistenciaAluno(cmd.getIdAluno());
        return executarRegistroFinalizacao(cmd);
    }

    /*
     * Método responsável por obter todos os treinos finalizados
     * pelo aluno. O método espera o id do aluno desejado.
     */

    public List<HistoricoTreinoDTO> obterCalendarioAluno(long idAluno) {
        ValidadorUtil.validarIdPositivo(idAluno, LABEL_ID_ALUNO);
        validarExistenciaAluno(idAluno);
        return historicoTreinoDAO.buscarHistoricoPorAluno(idAluno);
    }

    private String executarRegistroFinalizacao(FinalizarTreinoCmd cmd) {
        try {
            historicoTreinoDAO.registrarFinalizacao(cmd);
            return MSG_SUCESSO_FINALIZACAO;

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(MSG_SUCESSO_FINALIZACAO, e);
        }
    }

    private static void validarCmdFinalizarTreino(FinalizarTreinoCmd cmd) {
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_FINALIZACAO);
        ValidadorUtil.validarIdPositivo(cmd.getIdAluno(), LABEL_ID_ALUNO);
        ValidadorUtil.validarIdPositivo(cmd.getIdTreino(), LABEL_ID_TREINO);
    }

    private void validarExistenciaAluno(long idAluno) {
        if (usuarioDao.getUsuarioPorId(idAluno) == null) {
            throw new IllegalArgumentException(MSG_ERRO_ALUNO_NAO_ENCONTRADO);
        }
    }



}
