package com.projeto.academia.dao.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.academia.cmd.usuario.AtualizarUsuarioCmd;
import com.projeto.academia.cmd.usuario.CadastrarUsuarioCmd;
import com.projeto.academia.cmd.usuario.LoginCmd;
import com.projeto.academia.dao.usuario.criadordeclaracao.CadastrarUsuarioCriadorDeclaracao;
import com.projeto.academia.dto.usuario.UsuarioDTO;
import com.projeto.academia.dto.usuario.UsuarioLogadoDTO;
import com.projeto.academia.util.componente.dao.IDaoUtilComponente;

@Repository
@PropertySource("classpath:usuario/UsuarioDao.properties")
public class UsuarioDao {

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_CPF = "cpf";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_PERFIL = "perfil";
    private static final String COLUNA_DATA_NASCIMENTO = "data_nascimento";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDaoUtilComponente daoUtilComponente;

    @Value("${insert.usuarioDao.cadastrarUsuario}")
    private String sqlCadastrar;

    @Value("${select.usuarioDao.login}")
    private String sqlLogin;

    @Value("${select.usuarioDao.listarAlunos}")
    private String sqlListarAlunos;

    @Value("${update.usuarioDao.atualizarUsuario}")
    private String sqlAtualizar;

    @Value("${update.usuarioDao.atualizarSenha}")
    private String sqlAtualizarSenha;

    @Value("${delete.usuarioDao.excluirUsuario}")
    private String sqlExcluirUsuario;

    @Value("${select.usuarioDao.getPorId}")
    private String sqlGetPorId;

    @Value("${select.usuarioDao.getPorCpf}")
    private String sqlGetPorCpf;

    @Transactional
    public long cadastrarUsuario(CadastrarUsuarioCmd cmd) {
        return daoUtilComponente.insertRecuperandoId(jdbcTemplate,
                new CadastrarUsuarioCriadorDeclaracao(sqlCadastrar, cmd));
    }

    @Transactional
    public void atualizarUsuario(AtualizarUsuarioCmd cmd) {
        jdbcTemplate.update(sqlAtualizar, cmd.getNome(), cmd.getCpf(),
                java.sql.Date.valueOf(cmd.getDataNascimento()), cmd.getIdUsuario());
        if (cmd.getNovaSenha() != null && !cmd.getNovaSenha().isEmpty()) {
            jdbcTemplate.update(sqlAtualizarSenha, cmd.getNovaSenha(), cmd.getIdUsuario());
        }
    }

    @Transactional(readOnly = true)
    public UsuarioLogadoDTO autenticar(LoginCmd cmd) {
        return jdbcTemplate.query(sqlLogin, new Object[] { cmd.getCpf(), cmd.getSenha() },
                (rs, rowNum) -> new UsuarioLogadoDTO(rs.getLong(COLUNA_ID), rs.getString(COLUNA_NOME),
                        rs.getString(COLUNA_PERFIL)))
                .stream().findFirst().orElse(null);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarAlunos() {
        return jdbcTemplate.query(sqlListarAlunos,
                (rs, rowNum) -> new UsuarioDTO(rs.getLong(COLUNA_ID), rs.getString(COLUNA_NOME)
                        ,rs.getString(COLUNA_CPF) ,rs.getDate(COLUNA_DATA_NASCIMENTO).toLocalDate()
                        ,rs.getString(COLUNA_PERFIL)));
    }

    @Transactional
    public void excluirUsuario(long idUsuario) {
        jdbcTemplate.update(sqlExcluirUsuario, idUsuario);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO getUsuarioPorId(long id) {
        return jdbcTemplate.query(sqlGetPorId, new Object[] { id },
                (rs, rowNum) -> new UsuarioDTO(rs.getLong(COLUNA_ID), rs.getString(COLUNA_NOME)
                        ,rs.getString(COLUNA_CPF)
                        ,rs.getDate(COLUNA_DATA_NASCIMENTO).toLocalDate()
                        , rs.getString(COLUNA_PERFIL))).stream().findFirst().orElse(null);
    }

    public Long getUsuarioPorCpf(String cpf) {
        return jdbcTemplate.query(sqlGetPorCpf, new Object[] { cpf }, (rs, rowNum) -> rs.getLong(COLUNA_ID))
                .stream().findFirst().orElse(null);
    }
}
