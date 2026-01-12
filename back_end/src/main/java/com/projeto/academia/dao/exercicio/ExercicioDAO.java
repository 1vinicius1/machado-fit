package com.projeto.academia.dao.exercicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.cmd.exercicio.AtualizarExercicioCmd;
import com.projeto.academia.cmd.exercicio.CadastrarExercicioCmd;
import com.projeto.academia.dao.exercicio.criadorDeclaracao.CadastrarExercicioCriadorDeclaracao;
import com.projeto.academia.dto.exercicio.ExercicioDTO;
import com.projeto.academia.dto.exercicio.ExerciciosAlunoDTO;
import com.projeto.academia.enumeradores.DiaSemanaEnum;
import com.projeto.academia.util.componente.dao.IDaoUtilComponente;

@Repository
@PropertySource("classpath:exercicio/ExercicioDao.properties")
public class ExercicioDAO {

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_TREINO_ID = "treino_id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_REPETICOES = "repeticoes";
    private static final String COLUNA_CARGA = "carga";
    private static final String COLUNA_OBSERVACOES = "observacoes";
    private static final String COLUNA_NOME_TREINO = "nomeTreino";
    private static final String COLUNA_NOME_EXERCICIO = "nome";
    private static final String COLUNA_ID_ALUNO = "aluno_id";
    private static final String COLUNA_DIA_SEMANA = "dia_semana";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDaoUtilComponente daoUtilComponente;

    @Value("${insert.exercicioDao.cadastrarExercicio}")
    private String sqlCadastrar;

    @Value("${update.exercicioDao.atualizarExercicio}")
    private String sqlAtualizar;

    @Value("${delete.exercicioDao.excluirExercicio}")
    private String sqlExcluir;

    @Value("${select.exercicioDao.getPorId}")
    private String sqlGetPorId;

    @Value("${select.exercicioDao.listarExerciciosPorTreino}")
    private String sqlListarExerciciosPorTreino;

    @Value("${select.exercicioDao.listarExerciciosPorAluno}")
    private String sqlListarExerciciosPorAluno;

    @Transactional
    public long cadastrarExercicio(CadastrarExercicioCmd cmd) {
        return daoUtilComponente.insertRecuperandoId(jdbcTemplate,
        new CadastrarExercicioCriadorDeclaracao(sqlCadastrar, cmd));
    }

    @Transactional
    public void atualizarExercicio(AtualizarExercicioCmd cmd) {
        jdbcTemplate.update(sqlAtualizar, cmd.getNome(), cmd.getRepeticoes(),
                cmd.getCarga(), cmd.getObservacoes(), cmd.getIdExercicio());
    }

    @Transactional
    public void excluirExercicio(long idExercicio) {
        jdbcTemplate.update(sqlExcluir, idExercicio);
    }

    @Transactional(readOnly = true)
    public ExercicioDTO getExercicioPorId(long idExercicio) {
        return jdbcTemplate.query(sqlGetPorId, new Object[] { idExercicio },
                (rs, rowNum) -> new ExercicioDTO(rs.getLong(COLUNA_ID), rs.getString(COLUNA_NOME),
                        rs.getString(COLUNA_REPETICOES), rs.getString(COLUNA_CARGA), rs.getString(COLUNA_OBSERVACOES), rs.getLong(COLUNA_TREINO_ID)))
                .stream().findFirst().orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ExercicioDTO> listarExerciciosPorTreino(long idTreino) {
        return jdbcTemplate.query(sqlListarExerciciosPorTreino, new Object [] { idTreino },
                (rs, rowNum) -> new ExercicioDTO(rs.getLong(COLUNA_ID), rs.getString(COLUNA_NOME),
                        rs.getString(COLUNA_REPETICOES), rs.getString(COLUNA_CARGA), rs.getString(COLUNA_OBSERVACOES), rs.getLong(COLUNA_TREINO_ID)));
    }

    @Transactional(readOnly = true)
    public List<ExerciciosAlunoDTO> listarExerciciosPorAluno(long idAluno) {
        return jdbcTemplate.query(sqlListarExerciciosPorAluno, new Object [] { idAluno },
                (rs, rowNum) -> new ExerciciosAlunoDTO(rs.getLong(COLUNA_TREINO_ID), rs.getString(COLUNA_NOME_TREINO),
                        DiaSemanaEnum.converter(rs.getString(COLUNA_DIA_SEMANA)),
                        rs.getString(COLUNA_NOME_EXERCICIO),
                        rs.getString(COLUNA_REPETICOES), rs.getString(COLUNA_CARGA),
                        rs.getString(COLUNA_OBSERVACOES), rs.getLong(COLUNA_ID_ALUNO)));
        }
    }



