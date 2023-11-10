package com.mycompany.analysis.of.algorythms.a3;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraphView;
import com.mycompany.analysis.of.algorythms.a3.sdk.SimpleGraph;
import com.mycompany.analysis.of.algorythms.a3.sdk.Vertice;
import com.mycompany.analysis.of.algorythms.a3.sdk.WeightedEdge;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

import static java.awt.event.KeyEvent.KEY_RELEASED;

public class GraphViewer {

    private final List<Vertice> listaAdj;

    public GraphViewer(List<Vertice> listaAdj) {
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
                    double newScale = (double)((int)(graphComponent.getGraph().getView().getScale() * 100.0 * 1.8)) / 100.0;
                    graphComponent.zoomTo(newScale, true);
                }
                if (e.getKeyChar() == '-') {
                    double newScale = (double)((int)(graphComponent.getGraph().getView().getScale() * 100.0 * 0.8)) / 100.0;
                    graphComponent.zoomTo(newScale, true);
                }
            }
            return false;
        });
    }

    private static SimpleGraph<String, DefaultEdge> criarGrafo(List<Vertice> listaAdj) {
        SimpleGraph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
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
}
