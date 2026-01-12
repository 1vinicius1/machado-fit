package com.projeto.academia.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioLogadoDTO {

    private long id;
    private String nome;
    private String perfil;
}
