package com.projeto.academia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.projeto.academia.dao.usuario.UsuarioDao;
import com.projeto.academia.dto.usuario.UsuarioDTO;

@Component
public class TesteConexao implements CommandLineRunner {

    @Autowired
    private UsuarioDao repositorio;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n>>> TESTANDO CONEXÃO COM O BANCO DE DADOS <<<");

        // Busca todos os usuários inseridos via SQL
        System.out.println("Buscando usuários...");

        for (UsuarioDTO u : repositorio.listarAlunos()) {
            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome()/* + " | Perfil: " + u.getPerfil()*/);
        }

        System.out.println(">>> FIM DO TESTE <<<\n");
    }
}