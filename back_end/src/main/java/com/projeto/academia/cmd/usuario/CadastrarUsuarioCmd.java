package com.projeto.academia.cmd.usuario;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projeto.academia.enumeradores.PerfilUsuarioEnum;

import lombok.Getter;

@Getter
public class CadastrarUsuarioCmd {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

    @NotNull(message = "O perfil é obrigatório.")
    private PerfilUsuarioEnum perfil;

    @SuppressWarnings("unused")
    private CadastrarUsuarioCmd(){}

    public CadastrarUsuarioCmd(String nome, String cpf, String senha, LocalDate dataNascimento,
            PerfilUsuarioEnum perfil) {

        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.perfil = perfil;
    }




}
