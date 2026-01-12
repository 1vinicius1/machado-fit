package com.projeto.academia.dao.treino.criadorDeclaracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.projeto.academia.cmd.treino.CadastrarTreinoCmd;

public class CadastrarTreinoCriadorDeclaracao implements PreparedStatementCreator {

    private static final String COLUNA_ID = "id";

    private String sql;
    private CadastrarTreinoCmd cmd;

    public CadastrarTreinoCriadorDeclaracao(String sql, CadastrarTreinoCmd cmd) {
        this.sql = sql;
        this.cmd = cmd;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, new String[] { COLUNA_ID });

        ps.setLong(1, cmd.getIdAluno());
        ps.setString(2, cmd.getNomeTreino());
        ps.setString(3, cmd.getDiaSemana().getTitulo());
        return ps;
    }
}
