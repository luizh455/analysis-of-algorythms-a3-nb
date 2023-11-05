package com.mycompany.analysis.of.algorythms.a3.sdk;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultGraphType;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.util.SupplierUtil;

import java.util.function.Supplier;

public class MyGraph<V, E> extends AbstractBaseGraph<V, E> {
        private static final long serialVersionUID = 4607246833824317836L;

        public MyGraph(Class<? extends E> edgeClass) {
            super((Supplier) null, SupplierUtil.createSupplier(edgeClass), (new DefaultGraphType.Builder())
                    .undirected()
                    .allowMultipleEdges(false)
                    .allowSelfLoops(false)
                    .weighted(true)
                    .build());
        }
        
        public static <V, E> GraphBuilder<V, E, ? extends SimpleGraph<V, E>> createBuilder(Class<? extends E> edgeClass) {
            return new GraphBuilder(new org.jgrapht.graph.SimpleGraph(edgeClass));
        }

        public static <V, E> GraphBuilder<V, E, ? extends org.jgrapht.graph.SimpleGraph<V, E>> createBuilder(Supplier<E> edgeSupplier) {
            return new GraphBuilder(new org.jgrapht.graph.SimpleGraph((Supplier)null, edgeSupplier, false));
        }
    }