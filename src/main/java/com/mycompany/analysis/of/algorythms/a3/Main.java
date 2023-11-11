/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.analysis.of.algorythms.a3;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraphView;
import com.mycompany.analysis.of.algorythms.a3.sdk.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

import static java.awt.event.KeyEvent.KEY_RELEASED;


public class Main {

    public static void main(String[] args) {
        InputRead inputRead = new InputRead();
        List<Artigo> artigos = inputRead.run();

        System.out.println("Digite o índice do resumo para visualizar: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        Artigo artigo = artigos.get(index);
        if (artigo == null) {
            throw new RuntimeException("Artigo com índice " + index + "não encontrado.");
        }

        List<Aresta> listaDeAdjacencia = criarListaDeAdjacencias(artigo);

        GraphViewer graphViewer = new GraphViewer(listaDeAdjacencia);
        graphViewer.execute();
    }

    private static List<Aresta> criarListaDeAdjacencias(Artigo artigo) {
        Map<String, Vertice> vertices = new HashMap<>();
        Map<Integer, Aresta> arestasPorHashCode = new HashMap<>();

        for (Frase frase : artigo.getFrasesFiltradas()) {
            if(frase.getListaPalavras().isEmpty() || frase.getListaPalavras().size() == 1) continue;

            for (int i = 0; i < frase.getListaPalavras().size(); i++) {
                String palavra1 = frase.getListaPalavras().get(i);
                Vertice vertice1 = vertices.get(palavra1);
                if(vertice1 == null) {
                    vertice1 = new Vertice(palavra1, 1);
                    vertices.put(palavra1, vertice1);
                } else {
                    vertice1.setOcorrencias(vertice1.getOcorrencias() + 1);
                }

                for (int j = i + 1; j < frase.getListaPalavras().size(); j++) {
                    String palavra2 = frase.getListaPalavras().get(j);

                    if(palavra2.equals(palavra1)) continue;

                    Vertice vertice2 = vertices.get(palavra2);
                    if(vertice2 == null) {
                        vertice2 = new Vertice(palavra2, 0);
                        vertices.put(palavra2, vertice2);
                    }

                    Aresta novaAresta = new Aresta(vertice1, vertice2, 1);
                    Aresta arestaExistente = arestasPorHashCode.get(novaAresta.hashCode());
                    if(arestaExistente == null) {
                        arestasPorHashCode.put(novaAresta.hashCode(), novaAresta);
                    } else {
                        arestaExistente.setPeso(arestaExistente.getPeso() + 1);
                    }
                }
            }
        }

        return new ArrayList<>(arestasPorHashCode.values());
    }
}
