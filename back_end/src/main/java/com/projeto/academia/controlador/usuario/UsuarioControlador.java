package com.projeto.academia.controlador.usuario;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.cmd.usuario.AtualizarUsuarioCmd;
import com.projeto.academia.cmd.usuario.CadastrarUsuarioCmd;
import com.projeto.academia.cmd.usuario.LoginCmd;
import com.projeto.academia.dto.usuario.UsuarioDTO;
import com.projeto.academia.dto.usuario.UsuarioLogadoDTO;
import com.projeto.academia.servico.usuario.UsuarioServico;

@RestController
@RequestMapping("usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar (@RequestBody @Valid CadastrarUsuarioCmd cmd) {
        return ResponseEntity.ok(usuarioServico.cadastrarUsuario(cmd));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLogadoDTO> login (@RequestBody @Valid LoginCmd cmd) {
        return ResponseEntity.ok(usuarioServico.login(cmd));
    }

    @GetMapping("/listar-alunos")
    public ResponseEntity<List<UsuarioDTO>> listarAlunos() {
        return ResponseEntity.ok(usuarioServico.listarAlunos());
    }

    @GetMapping("/listar-usuarios-inativos")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosInativos() {
        return ResponseEntity.ok(usuarioServico.listarUsuariosInativos());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarUsuarioCmd cmd) {
        return ResponseEntity.ok(usuarioServico.atualizarUsuario(cmd));
    }

    @DeleteMapping("/excluir/{idUsuario}")
    public ResponseEntity<String> excluir(@PathVariable long idUsuario) {
        return ResponseEntity.ok(usuarioServico.excluirUsuario(idUsuario));
    }

    @PutMapping("/ativar/{idUsuario}")
    public ResponseEntity<String> ativar(@PathVariable long idUsuario) {
        return ResponseEntity.ok(usuarioServico.ativarUsuario(idUsuario));
    }

    @PutMapping("/inativar/{idUsuario}")
    public ResponseEntity<String> inativar(@PathVariable long idUsuario) {
        return ResponseEntity.ok(usuarioServico.inativarUsuario(idUsuario));
    }

    @GetMapping("/perfil/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterPerfil(@PathVariable long idUsuario) {
        return ResponseEntity.ok(usuarioServico.obterPerfil(idUsuario));
    }

    @GetMapping("/papel/{idUsuario}")
    public ResponseEntity<Boolean> isPersonal(@PathVariable long idUsuario) {
        return ResponseEntity.ok(usuarioServico.isPersonal(idUsuario));

    }

}
