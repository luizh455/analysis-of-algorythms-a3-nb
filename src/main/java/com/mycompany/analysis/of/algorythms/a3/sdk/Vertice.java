package com.mycompany.analysis.of.algorythms.a3.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lhenr
 */

public class Vertice {
    private String palavra;

    private int ocorrencias;

    public Vertice(String palavra, int ocorrencias) {
        this.palavra = palavra;
        this.ocorrencias = ocorrencias;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(int ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return Objects.equals(palavra, vertice.palavra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(palavra);
    }
}
