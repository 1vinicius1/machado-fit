package com.projeto.academia.cmd.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCmd {

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

}
