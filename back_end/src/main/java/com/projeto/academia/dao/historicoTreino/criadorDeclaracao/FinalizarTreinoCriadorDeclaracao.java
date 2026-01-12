package com.projeto.academia.dao.historicoTreino.criadorDeclaracao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.projeto.academia.cmd.historicoTreino.FinalizarTreinoCmd;

public class FinalizarTreinoCriadorDeclaracao implements PreparedStatementCreator {

    private static final String COLUNA_ID = "id";

    private String sql;
    private FinalizarTreinoCmd cmd;

    public FinalizarTreinoCriadorDeclaracao(String sql, FinalizarTreinoCmd cmd) {
        this.sql = sql;
        this.cmd = cmd;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, new String[] { COLUNA_ID });

        Date dataSql = (cmd.getDataFinalizacao() != null) ? Date.valueOf(cmd.getDataFinalizacao())
                : new Date(System.currentTimeMillis());

        ps.setLong(1, cmd.getIdAluno());
        ps.setLong(2, cmd.getIdTreino());
        ps.setDate(3, dataSql);

        return ps;
    }

}
