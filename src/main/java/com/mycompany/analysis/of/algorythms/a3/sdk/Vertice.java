package com.mycompany.analysis.of.algorythms.a3.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhenr
 */

public class Vertice {

    private Map<String, Integer> adjacentes = new HashMap<>();
    private String palavra;

    public Vertice(String palavra) {
        this.palavra = palavra;
    }

    public void addAdjacente(String a) {
        Integer pesoExistente = adjacentes.get(a);
        if(pesoExistente == null) {
            pesoExistente = 0;
        }
        pesoExistente++;
        adjacentes.put(a, pesoExistente);
    }

    public void delAdjacente(String a) {
        adjacentes.remove(a);
    }

    public boolean findAdjacente(String a) {
        return adjacentes.containsKey(a);
    }

    public Map<String, Integer> getAdjacentes() {
        return adjacentes;
    }

    public void setAdjacentes(Map<String, Integer> adjacentes) {
        this.adjacentes = adjacentes;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
}
