package com.mycompany.analysis.of.algorythms.a3;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraphView;
import com.mycompany.analysis.of.algorythms.a3.sdk.Aresta;
import com.mycompany.analysis.of.algorythms.a3.sdk.SimpleGraph;
import com.mycompany.analysis.of.algorythms.a3.sdk.Vertice;
import com.mycompany.analysis.of.algorythms.a3.sdk.WeightedEdge;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.event.KeyEvent.KEY_RELEASED;

public class GraphViewer {

    private final List<Aresta> listaAdj;

    public GraphViewer(List<Aresta> listaAdj) {
        this.listaAdj = listaAdj;
    }

    public void execute() {
        SimpleGraph<String, DefaultEdge> grafo = criarGrafo(listaAdj);

        JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(grafo);
        jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);

        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
        graphComponent.setConnectable(false);
        graphComponent.setAntiAlias(false);
        graphComponent.getGraph().setAllowDanglingEdges(false);
        graphComponent.getGraph().setCellsDisconnectable(false);
        graphComponent.getGraph().setCellsEditable(false);
        graphComponent.getGraph().setConnectableEdges(false);

        // reescalar para mostrar todo o grafo na tela
        mxGraphView view = graphComponent.getGraph().getView();
        double factor = 500 / view.getGraphBounds().getHeight();
        view.setScale(factor * view.getScale());

        JFrame frame = new JFrame("Visualizador de grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buildKeyboardManager(graphComponent);
    }

    private static void buildKeyboardManager(mxGraphComponent graphComponent) {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(e -> {
            if (e.getID() == KEY_RELEASED) {
                if (e.getKeyChar() == '+') {
                    double newScale = (double)((int)(graphComponent.getGraph().getView().getScale() * 100.0 * 1.5)) / 100.0;
                    graphComponent.zoomTo(newScale, false);
                }
                if (e.getKeyChar() == '-') {
                    double newScale = (double)((int)(graphComponent.getGraph().getView().getScale() * 100.0 * 0.8)) / 100.0;
                    graphComponent.zoomTo(newScale, false);
                }
            }
            return false;
        });
    }

    private static SimpleGraph<String, DefaultEdge> criarGrafo(List<Aresta> listaAdj) {
        SimpleGraph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);

        for (Aresta aresta : listaAdj) {
            Vertice vertice1 = aresta.getVertice1();
            Vertice vertice2 = aresta.getVertice2();
            if(!grafo.containsVertex(vertice1.getPalavra())) {
                grafo.addVertex(vertice1.getPalavra());
            }
            if(!grafo.containsVertex(vertice2.getPalavra())) {
                grafo.addVertex(vertice2.getPalavra());
            }

            if (!grafo.containsEdge(vertice1.getPalavra(), vertice2.getPalavra())) {
                WeightedEdge e = new WeightedEdge(String.valueOf(aresta.getPeso()));
                grafo.addEdge(vertice1.getPalavra(), vertice2.getPalavra(), e);
            }
        }
        return grafo;
    }
}
