package com.projeto.academia.util;

import java.util.Collection;

import org.springframework.util.Assert;

public final class ValidadorUtil {

    private ValidadorUtil(){}

    public static void validarObjetoNaoNulo(Object obj, String nomeObj) {
        Assert.notNull(obj, nomeObj + " não pode ser nulo(a).");
    }

    public static void validarIdPositivo(Long id, String nomeCampo) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(nomeCampo + " inválido: deve ser maior que zero.");
        }
    }

    public static void validarTextoObrigatorio(String texto, String nomeCampo) {
        Assert.hasText(texto, nomeCampo + " é obrigatório(a).");
    }

    public static void validarListaNaoVazia(Collection<?> colecao, String msgErro) {
        if (colecao == null || colecao.isEmpty()) {
            throw new IllegalArgumentException(msgErro);
        }
    }

    public static void validarTamanhoMaximoLista(Collection<?> colecao, int maximo, String msgErro) {
        if (colecao != null && colecao.size() > maximo) {
            throw new IllegalArgumentException(msgErro);
        }
    }
}
