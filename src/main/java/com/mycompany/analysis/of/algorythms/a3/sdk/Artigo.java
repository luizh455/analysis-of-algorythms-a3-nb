/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analysis.of.algorythms.a3.sdk;

import java.util.List;

/**
 *
 * @author lhenr
 */
public class Artigo {
    private String titulo;
    private String resumo;
    private String tags;
    private String[] frases;
    private List<Frase> frasesFiltradas;

    public Artigo(String titulo, String resumo, String tags) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.tags = tags;
        this.frases = resumo.split("[\\.]");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String[] getFrases() {
        return frases;
    }

    public void setFrases(String[] frases) {
        this.frases = frases;
    }

    public List<Frase> getFrasesFiltradas() {
        return frasesFiltradas;
    }

    public void setFrasesFiltradas(List<Frase> frasesFiltradas) {
        this.frasesFiltradas = frasesFiltradas;
    }
}