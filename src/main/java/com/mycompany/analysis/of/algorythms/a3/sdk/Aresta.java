package com.mycompany.analysis.of.algorythms.a3.sdk;

import java.util.Objects;

public class Aresta {
    private Vertice vertice1;
    private Vertice vertice2;

    private int peso;

    public Aresta(Vertice vertice1, Vertice vertice2, int peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public Vertice getVertice1() {
        return vertice1;
    }

    public void setVertice1(Vertice vertice1) {
        this.vertice1 = vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public void setVertice2(Vertice vertice2) {
        this.vertice2 = vertice2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;


        return (Objects.equals(vertice1, aresta.vertice1) && Objects.equals(vertice2, aresta.vertice2)) ||
                Objects.equals(vertice1, aresta.vertice2) && Objects.equals(vertice2, aresta.vertice1);
    }

    @Override
    public int hashCode() {
        int hashSum = vertice1.hashCode() + vertice2.hashCode();
        return 17 * hashSum;
    }
}
