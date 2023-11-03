/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analysis.of.algorythms.a3;

import java.util.List;

/**
 *
 * @author lhenr
 */
public class Artigo {
    String titulo;
    String resumo;
    String tags;
    String[] frases;
    List<Frase> frasesFiltradas;

    public Artigo(String titulo, String resumo, String tags) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.tags = tags;
        this.frases = resumo.split("[\\.]");
    }
}