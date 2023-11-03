package com.mycompany.analysis.of.algorythms.a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhenr
 */

public class Vertice {

    Map<String, Integer> adjacentes = new HashMap<>();
    String word;

    public Vertice(String word) {
        this.word = word;
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
}
