package com.projeto.academia.dao.usuario.criadordeclaracao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.projeto.academia.cmd.usuario.CadastrarUsuarioCmd;

public class CadastrarUsuarioCriadorDeclaracao implements PreparedStatementCreator {

    private static final String COLUNA_ID = "id";

    private String sql;
    private CadastrarUsuarioCmd cmd;

    public CadastrarUsuarioCriadorDeclaracao(String sql, CadastrarUsuarioCmd cmd) {
        this.sql = sql;
        this.cmd = cmd;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, new String[] { COLUNA_ID });

        ps.setString(1, cmd.getCpf());
        ps.setString(2, cmd.getSenha());
        ps.setString(3, cmd.getNome());
        ps.setDate(4, Date.valueOf(cmd.getDataNascimento()));
        ps.setString(5, cmd.getPerfil().getTitulo());
        return ps;

    }
}
