package com.projeto.academia.servico.usuario;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projeto.academia.cmd.usuario.AtualizarUsuarioCmd;
import com.projeto.academia.cmd.usuario.CadastrarUsuarioCmd;
import com.projeto.academia.cmd.usuario.LoginCmd;
import com.projeto.academia.dao.usuario.UsuarioDao;
import com.projeto.academia.dto.usuario.UsuarioDTO;
import com.projeto.academia.dto.usuario.UsuarioLogadoDTO;
import com.projeto.academia.util.ValidadorUtil;

@Service
public class UsuarioServico {

    private static final String LABEL_ID = "ID do Usuário";
    private static final String LABEL_DADOS_CADASTRO = "Dados de cadastro";
    private static final String LABEL_DADOS_LOGIN = "Dados de login";
    private static final String LABEL_CPF = "CPF";
    private static final String LABEL_NOME = "Nome";
    private static final String LABEL_SENHA = "Senha";
    private static final String LABEL_DATA_NASCIMENTO = "Data de Nascimento";
    private static final String LABEL_PERFIL = "Perfil";
    private static final String LABEL_DADOS_ATUALIZACAO = "Dados de atualização";

    private static final String MSG_SUCESSO_CADASTRO = "Usuário cadastrado com sucesso.";
    private static final String MSG_SUCESSO_ATUALIZACAO = "Dados do usuário atualizados com sucesso";
    private static final String MSG_SUCESSO_EXCLUSAO = "Usuário e todos os seus dados vinculados foram removidos";

    private static final String MSG_ERRO_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    private static final String MSG_ERRO_CPF_JA_CADASTRADO = "CPF já consta no sistema";
    private static final String MSG_ERRO_CREDENCIAIS = "CPF ou Senha inválidos";
    private static final String MSG_ERRO_OP_USUARIO_NAO_ENCONTRADO = "Operação não realizada: Usuário não encontrado";


    @Autowired
    private UsuarioDao usuarioDAO;

    /*
     * Método responsável pelo cadastro de um Usuário
     */

    @Transactional
    public String cadastrarUsuario(@Valid CadastrarUsuarioCmd cmd) {
        validarCmdCadastrar(cmd);
        validarCpfJaCadastrado(cmd.getCpf());
        usuarioDAO.cadastrarUsuario(cmd);
        return MSG_SUCESSO_CADASTRO;
    }

    /*
     * Método responsável pelo login de um Usuário
     */

    public UsuarioLogadoDTO login(@Valid LoginCmd cmd) {
        validarCmdLogin(cmd);
        UsuarioLogadoDTO usuario = usuarioDAO.autenticar(cmd);
        Assert.notNull(usuario, MSG_ERRO_CREDENCIAIS);
        return usuario;
    }

    /*
     * Método retorna a lista
     * completa de Usuários com
     * perfil definido como "ALUNO"
     */

    public List<UsuarioDTO> listarAlunos() {
        return usuarioDAO.listarAlunos();
    }

    /*
     * Método responsável pela atualização de um Usuário
     */

    @Transactional
    public String atualizarUsuario(@Valid AtualizarUsuarioCmd cmd) {
        validarCmdAtualizar(cmd);
        validarExistenciaUsuario(cmd.getIdUsuario());
        usuarioDAO.atualizarUsuario(cmd);
        return MSG_SUCESSO_ATUALIZACAO;
    }

    /*
     * Método responsável pela exclusão de um Usuário
     */

    public String excluirUsuario(long id) {
        ValidadorUtil.validarIdPositivo(id, LABEL_ID);
        validarExistenciaUsuario(id);
        if (usuarioDAO.getHistoricoUsuario(id) != null) {
            usuarioDAO.excluirHistoricoUsuario(id);
        }
        usuarioDAO.excluirUsuario(id);
        return MSG_SUCESSO_EXCLUSAO;
    }

    /*
     * Este método retorna um usuário a partir do id
     * do usuário desejado
     */

    public UsuarioDTO obterPerfil(long id) {
        ValidadorUtil.validarIdPositivo(id, LABEL_ID);
        UsuarioDTO usuario = usuarioDAO.getUsuarioPorId(id);
        validarUsuarioEncontrado(usuario);
        return usuario;
    }

    private static void validarCmdCadastrar(CadastrarUsuarioCmd cmd) {
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_CADASTRO);
        ValidadorUtil.validarTextoObrigatorio(cmd.getCpf(), LABEL_CPF);
        ValidadorUtil.validarTextoObrigatorio(cmd.getNome(), LABEL_NOME);
        ValidadorUtil.validarTextoObrigatorio(cmd.getSenha(), LABEL_SENHA);
        ValidadorUtil.validarObjetoNaoNulo(cmd.getDataNascimento(), LABEL_DATA_NASCIMENTO);
        ValidadorUtil.validarObjetoNaoNulo(cmd.getPerfil(), LABEL_PERFIL);
    }

    private void validarCpfJaCadastrado(String cpf) {
        if (usuarioDAO.getUsuarioPorCpf(cpf) != null) {
            throw new IllegalArgumentException(MSG_ERRO_CPF_JA_CADASTRADO);
        }
    }

    private static void validarCmdLogin(LoginCmd cmd) {
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_LOGIN);
        ValidadorUtil.validarTextoObrigatorio(cmd.getCpf(), LABEL_CPF);
        ValidadorUtil.validarTextoObrigatorio(cmd.getSenha(), LABEL_SENHA);
    }

    private static void validarCmdAtualizar(AtualizarUsuarioCmd cmd) {
        ValidadorUtil.validarObjetoNaoNulo(cmd, LABEL_DADOS_ATUALIZACAO);
        ValidadorUtil.validarIdPositivo(cmd.getIdUsuario(), LABEL_ID);
        ValidadorUtil.validarTextoObrigatorio(cmd.getNome(), LABEL_NOME);
        ValidadorUtil.validarTextoObrigatorio(cmd.getCpf(), LABEL_CPF);
        ValidadorUtil.validarObjetoNaoNulo(cmd.getDataNascimento(), LABEL_DATA_NASCIMENTO);
    }

    private void validarExistenciaUsuario(long id) {
        if (usuarioDAO.getUsuarioPorId(id) == null) {
            throw new IllegalArgumentException(MSG_ERRO_OP_USUARIO_NAO_ENCONTRADO);
        }
    }

    private static void validarUsuarioEncontrado(UsuarioDTO usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException(MSG_ERRO_USUARIO_NAO_ENCONTRADO);
        }

    /*private static void validarDataNascimento(AtualizarUsuarioCmd cmd) {

        Date dataSql = new Date(System.currentTimeMillis());
        int anoAtual = dataSql.getYear();
        int anoNascimento = Date.valueOf(cmd.getDataNascimento()).getYear();

        if (anoNascimento > anoAtual) {
            throw new IllegalArgumentException("A data de nascimento é inválida!");
        }
    }*/

    }
}
