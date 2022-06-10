package com.timeTable.algorithm;

import com.timeTable.Event;
import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {



    public Event returnSource(){
        return (Event) getSource();
    }
    public Event returnTarget(){
        return (Event) getTarget();
    }
}
