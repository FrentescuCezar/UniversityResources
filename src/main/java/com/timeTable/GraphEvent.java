package com.timeTable;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import java.sql.Time;

public class GraphEvent {
    public static Graph<Event, DefaultEdge> buildEmptySimpleGraph()
    {
        return GraphTypeBuilder
                .<Event, DefaultEdge> undirected().allowingMultipleEdges(false)
                .allowingSelfLoops(false).edgeClass(DefaultEdge.class).weighted(false).buildGraph();
    }
}
