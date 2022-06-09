package com.timeTable.algorithm;

import com.timeTable.Event;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;


public class GraphEvent{

    public static Graph<Event, Edge> buildEmptySimpleGraph()
    {
        return GraphTypeBuilder
                .<Event, DefaultEdge> undirected().allowingMultipleEdges(false)
                .allowingSelfLoops(false).edgeClass(Edge.class).weighted(false).buildGraph();
    }
}
