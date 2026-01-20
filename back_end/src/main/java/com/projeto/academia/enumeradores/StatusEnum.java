package com.projeto.academia.enumeradores;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {

    ATIVO("Ativo", "A"),
    INATIVO("Inativo", "I");

    private String titulo;
    private String sigla;

    StatusEnum(String titulo, String sigla) {
        this.titulo = titulo;
        this.sigla = sigla;
    }

    @JsonValue
    public String getTitulo() {
        return titulo;
    }

    public String getSigla() {
        return sigla;
    }

    public static StatusEnum converter(String valor) {
        if (valor == null) {
            return null;
        }
        return Arrays.stream(StatusEnum.values())
                .filter(e -> e.titulo.equalsIgnoreCase(valor)).findFirst()
                .orElse(null);
    }

}
