package com.projeto.academia.dao.exercicio.criadorDeclaracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.projeto.academia.cmd.exercicio.CadastrarExercicioCmd;

public class CadastrarExercicioCriadorDeclaracao implements PreparedStatementCreator {

    private static final String COLUNA_ID = "id";

    private String sql;
    private CadastrarExercicioCmd cmd;

    public CadastrarExercicioCriadorDeclaracao(String sql, CadastrarExercicioCmd cmd) {
        this.sql = sql;
        this.cmd = cmd;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, new String[] { COLUNA_ID });

        ps.setLong(1, cmd.getTreinoId());
        ps.setString(2, cmd.getNome());
        ps.setString(3, cmd.getRepeticoes());
        ps.setString(4, cmd.getCarga());
        ps.setString(5, cmd.getObservacoes());
        return ps;
    }

}
