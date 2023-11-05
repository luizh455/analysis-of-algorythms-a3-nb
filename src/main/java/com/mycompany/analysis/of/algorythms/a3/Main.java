/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.analysis.of.algorythms.a3;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mycompany.analysis.of.algorythms.a3.sdk.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        InputRead inputRead = new InputRead();
        List<Artigo> artigos = inputRead.run();

        System.out.println("Digite o índice do resumo para visualizar: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        Artigo artigo = artigos.get(index);
        if(artigo == null) {
            throw new RuntimeException("Artigo com índice " + index + "não encontrado.");
        }

        List<Vertice> listaAdj = construirListaDeAdjacencia(artigo);
        MyGraph<String, DefaultEdge> grafo = criarGrafo(listaAdj);

        JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(grafo);

        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
        graphComponent.setConnectable(false);
        graphComponent.setAntiAlias(false);
        graphComponent.getGraph().setAllowDanglingEdges(false);
        graphComponent.getGraph().setCellsDisconnectable(false);
        graphComponent.getGraph().setCellsEditable(false);
        graphComponent.getGraph().setConnectableEdges(false);

        JFrame frame = new JFrame("Grafo de resumos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }

    private static MyGraph<String, DefaultEdge> criarGrafo(List<Vertice> listaAdj) {
        MyGraph<String, DefaultEdge> grafo = new MyGraph<>(DefaultEdge.class);
        // Adiciona todos os vertices ao
        for (Vertice vertice : listaAdj) {
            grafo.addVertex(vertice.getPalavra());
        }

        // Cria as arestas com respectivos pesos
        for (Vertice vertice : listaAdj) {
            for (Map.Entry<String, Integer> entry : vertice.getAdjacentes().entrySet()) {
                String palavraAdj = entry.getKey();
                if (!grafo.containsEdge(vertice.getPalavra(), palavraAdj)) {
                    WeightedEdge e = new WeightedEdge(entry.getValue().toString());
                    grafo.addEdge(vertice.getPalavra(), palavraAdj, e);
                }
            }
        }
        return grafo;
    }

    private static List<Vertice> construirListaDeAdjacencia(Artigo artigo) {
        List<Vertice> listaAdj = new ArrayList<>();
        for (Frase frase : artigo.getFrasesFiltradas()) {
            for (int i = 0; i < frase.getListaPalavras().size(); i++) {
                String palavra1 = frase.getListaPalavras().get(i);
                Vertice vertice = null;

                for (Vertice v : listaAdj) {
                    if (v.getPalavra().equals(palavra1)) {
                        vertice = v;
                    }
                }
                if (vertice == null) {
                    vertice = new Vertice(palavra1);
                    listaAdj.add(vertice);
                }

                // cria uma adjacencia da palavra atual com todas as outras palavras da frase
                for (int j = 0; j < frase.getListaPalavras().size(); j++) {
                    if (j == i) {
                        // ignora a propria palavra
                        continue;
                    }
                    String palavra2 = frase.getListaPalavras().get(j);

                    if (!palavra1.equals(palavra2)) {
                        vertice.addAdjacente(palavra2);
                    }
                }
            }
        }
        return listaAdj;
    }
}
