package com.mycompany.analysis.of.algorythms.a3.sdk;

import org.jgrapht.graph.DefaultEdge;

public class WeightedEdge extends DefaultEdge {
        private String label;

        public WeightedEdge(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }