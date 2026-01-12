package com.projeto.academia.dao.treino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.cmd.treino.AtualizarTreinoCmd;
import com.projeto.academia.cmd.treino.CadastrarTreinoCmd;
import com.projeto.academia.dao.treino.criadorDeclaracao.CadastrarTreinoCriadorDeclaracao;
import com.projeto.academia.dto.treino.TreinoDTO;
import com.projeto.academia.enumeradores.DiaSemanaEnum;
import com.projeto.academia.util.componente.dao.IDaoUtilComponente;

@Repository
@PropertySource("classpath:treino/TreinoDao.properties")
public class TreinoDAO {

    private static final String COLUNA_ID_TREINO = "id";
    private static final String COLUNA_DIA_SEMANA = "dia_semana";
    private static final String COLUNA_NOME_TREINO = "nomeTreino";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDaoUtilComponente daoUtilComponente;

    @Value("${insert.treinoDao.cadastrarTreino}")
    private String sqlCadastrar;

    @Value("${update.treinoDao.atualizarTreino}")
    private String sqlAtualizar;

    @Value("${delete.treinoDao.excluirTreino}")
    private String sqlExcluir;

    @Value("${select.treinoDao.getPorId}")
    private String sqlGetPorId;

    @Transactional
    public long cadastrarTreino (CadastrarTreinoCmd cmd) {
        return daoUtilComponente.insertRecuperandoId(jdbcTemplate,
                new CadastrarTreinoCriadorDeclaracao(sqlCadastrar, cmd));
    }

    @Transactional
    public void atualizarTreino (AtualizarTreinoCmd cmd) {
        jdbcTemplate.update(sqlAtualizar, cmd.getNomeTreino(), cmd.getDiaSemana().getTitulo(), cmd.getIdTreino());
    }

    @Transactional
    public void excluirTreino(long idTreino) {
        jdbcTemplate.update(sqlExcluir, idTreino);
    }

    @Transactional(readOnly = true)
    public TreinoDTO getTreinoPorId(long idTreino) {
        return jdbcTemplate.query(sqlGetPorId, new Object[] { idTreino },
                (rs, rowNum) -> new TreinoDTO(rs.getLong(COLUNA_ID_TREINO), rs.getString(COLUNA_NOME_TREINO),
                        DiaSemanaEnum.converter(rs.getString(COLUNA_DIA_SEMANA)))).stream().findFirst().orElse(null);
    }

}
