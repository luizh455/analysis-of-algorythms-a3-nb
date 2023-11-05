/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.analysis.of.algorythms.a3;

/**
 *
 * @author lhenr
 */
import com.mycompany.analysis.of.algorythms.a3.sdk.Artigo;
import com.mycompany.analysis.of.algorythms.a3.sdk.Frase;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.ext.JGraphXAdapter;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import java.util.List;
import javax.swing.*;

public class ExemploJGraphT {
    public static void main(String[] args) {
       
    }
    
    public static void run(List<Artigo> artigos) {
         // Crie um grafo simples
        Graph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
        
        for ( Artigo artigo : artigos) {
           for (Frase frases : artigo.getFrasesFiltradas()) {
               
           }
        }
        for(String palavra : artigos.get(0).getFrasesFiltradas().get(0).getListaPalavras()) {
            grafo.addVertex(palavra);
        }
        
        

        // Adicione vértices
//        grafo.addVertex("A");
//        grafo.addVertex("B");
//        grafo.addVertex("C");
//
//        // Adicione arestas
//        grafo.addEdge("A", "B");
//        grafo.addEdge("B", "C");;

        // Crie uma representação JGraphX do grafo
        JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(grafo);

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
    }
    
        // Metodos print para facilitar a vida...
    public static void print(String[] msg) {
        for(String value: msg) {
            print(value);
        }
    }

    public static void print(List<String> msg) {
        if(msg == null) return;
        msg.forEach((v) -> print(v));
    }

    public static void printFrase(List<Frase> a) {
        if(a == null) return;
        a.forEach((v) -> print(v));
    }

    public static void printAsText(Frase frase) {
        frase.getListaPalavras().forEach((v) -> System.out.print(v + " "));
    }

    public static void print(Frase msg) {
        if(msg == null) return;
        print(msg.getListaPalavras());
    }

    public static void print(String msg) {
        System.out.println(msg);
    }
}