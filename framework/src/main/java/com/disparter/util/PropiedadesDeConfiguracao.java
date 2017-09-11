package com.disparter.util;

import java.util.HashMap;
import java.util.Map;

public final class PropiedadesDeConfiguracao {

    private static Map<String, String> variaveis = new HashMap<>();

    private PropiedadesDeConfiguracao() {
    }

    public static String getValor(String chave) {
        return variaveis.get(chave);
    }

    public static void adicionar(String chave, String valor) {
        variaveis.put(chave, valor);
    }

}
