package com.projeto.academia.dao.historicoTreino;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.cmd.historicoTreino.FinalizarTreinoCmd;
import com.projeto.academia.dao.historicoTreino.criadorDeclaracao.FinalizarTreinoCriadorDeclaracao;
import com.projeto.academia.dto.historicoTreino.HistoricoTreinoDTO;
import com.projeto.academia.util.componente.dao.IDaoUtilComponente;

@Repository
@PropertySource("classpath:historicoTreino/HistoricoTreinoDao.properties")
public class HistoricoTreinoDAO{


    private static final String COLUNA_DATA_FINALIZACAO = "data_conclusao";
    private static final String COLUNA_NOME_TREINO = "nomeTreino";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDaoUtilComponente daoUtilComponente;

    @Value("${insert.historicoTreinoDao.finalizarTreino}")
    private String sqlFinalizar;

    @Value("${select.historicoTreinoDao.buscarHistoricoPorAluno}")
    private String sqlBuscarPorAluno;

    @Transactional
    public long registrarFinalizacao(FinalizarTreinoCmd cmd) {
        return daoUtilComponente.insertRecuperandoId(jdbcTemplate,
                new FinalizarTreinoCriadorDeclaracao(sqlFinalizar, cmd));
    }

    @Transactional(readOnly = true)
    public List<HistoricoTreinoDTO> buscarHistoricoPorAluno(long idAluno) {
        return jdbcTemplate.query(sqlBuscarPorAluno, new Object[] { idAluno },
                (rs, rowNum) -> new HistoricoTreinoDTO(rs.getDate(COLUNA_DATA_FINALIZACAO).toLocalDate(),
                        rs.getString(COLUNA_NOME_TREINO)));
    }

}
