package com.projeto.academia.cmd.usuario;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.academia.enumeradores.PerfilUsuarioEnum;
import com.projeto.academia.enumeradores.StatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarUsuarioCmd {

    @NotNull(message = "ID do usuário é obrigatório")
    private Long idUsuario;

    @NotBlank(message = "O nome do usuário é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF do usuário é obrigatório")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotNull(message = "O status do usuário é obrigatório")
    private StatusEnum status;

    private String novaSenha;

    private PerfilUsuarioEnum perfil;


}
