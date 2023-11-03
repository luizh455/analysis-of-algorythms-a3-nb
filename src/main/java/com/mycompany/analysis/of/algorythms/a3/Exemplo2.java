/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.analysis.of.algorythms.a3;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Exemplo2 {

    public static void main(String[] args) {
        InputRead inputRead = new InputRead();
        List<Artigo> artigos = inputRead.run();

        SimpleWeightedGraph<String, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(
                DefaultWeightedEdge.class);

//        Graph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);

        List<Vertice> listaAdj = new ArrayList<>();

        Artigo artigo = artigos.get(0);
        for (Frase frase : artigo.frasesFiltradas) {
            for (int i = 0; i < frase.listaPalavras.size(); i++) {
                String palavra1 = frase.listaPalavras.get(i);
                Vertice vertice = null;

                for (Vertice v : listaAdj) {
                    if(v.word.equals(palavra1)) {
                        vertice = v;
                    }
                }
                if (vertice == null) {
                    vertice = new Vertice(palavra1);
                    listaAdj.add(vertice);
                }

                // cria uma adjacencia da palavra atual com todas as outras palavras da frase
                for (int j = 0; j < frase.listaPalavras.size(); j++) {
                    if(j == i) {
                        // ignora a propria palavra
                        continue;
                    }
                    String palavra2 = frase.listaPalavras.get(j);

                    if(!palavra1.equals(palavra2)) {
                        vertice.addAdjacente(palavra2);
                    }
                }
            }
        }

        for (Vertice vertice : listaAdj) {
            grafo.addVertex(vertice.word);
        }

        for (Vertice vertice : listaAdj) {
            for (Map.Entry<String, Integer> entry : vertice.adjacentes.entrySet()) {
                String palavraAdj = entry.getKey();
                if(!grafo.containsEdge(vertice.word, palavraAdj)) {
                    DefaultWeightedEdge defaultWeightedEdge = grafo.addEdge(vertice.word, palavraAdj);
                    grafo.setEdgeWeight(defaultWeightedEdge, entry.getValue());
                }
            }
        }


        JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter = new JGraphXAdapter<>(grafo);

        // Configure o layout do grafo
        mxIGraphLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

        // Crie um componente Swing para exibir o grafo
        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);

        // Crie uma janela para exibir o grafo
        JFrame frame = new JFrame("Exemplo JGraphT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);

        System.out.println();

    }
}
