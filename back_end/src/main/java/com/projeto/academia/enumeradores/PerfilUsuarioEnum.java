package com.projeto.academia.enumeradores;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PerfilUsuarioEnum {

    PERSONAL("PERSONAL", "Personal Trainer"), ALUNO("ALUNO", "Aluno");

    private String titulo;
    private String descricao;

    PerfilUsuarioEnum(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @JsonValue
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static PerfilUsuarioEnum converter(String valor) {
        if (valor == null) {
            return null;
        }

        return Arrays.stream(PerfilUsuarioEnum.values())
                        .filter(e -> e.titulo.equalsIgnoreCase(valor)).findFirst()
                        .orElse(null);
    }
}
