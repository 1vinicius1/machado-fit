package com.projeto.academia.enumeradores;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DiaSemanaEnum {

    SEGUNDA("SEGUNDA", "Seg"), TERCA("TERCA", "TER"),
    QUARTA("QUARTA", "QUAR"), QUINTA("QUINTA", "QUIN"),
    SEXTA("SEXTA", "SEX"), SABADO("SABADO", "SAB"),
    DOMINGO("DOMINGO", "DOM");

    private String titulo;
    private String sigla;

    DiaSemanaEnum(String titulo, String sigla) {
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

    public static DiaSemanaEnum converter(String valor) {
        if (valor == null) {
            return null;
        }

        return Arrays.stream(DiaSemanaEnum.values())
                .filter(e -> e.titulo.equalsIgnoreCase(valor)).findFirst()
                .orElse(null);
    }
}
