package com.scraper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.timeTable.Event;
import com.timeTable.GraphEvent;
import com.timeTable.TimeTable;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Time;
import java.util.*;

public class ScraperApplication {
    public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {
        PrintStream fileOut = new PrintStream("src\\main\\java\\com\\scraper\\output.txt");
        System.setOut(fileOut);
        TimeTableScraper scraper = new TimeTableScraper();
        ObjectMapper mapper = new ObjectMapper();

        scraper.startScrape();

        Graph<Event, DefaultEdge> eventsGraph = GraphEvent.buildEmptySimpleGraph();

        for(int i = 0; i<scraper.listOfTimeTables.size(); ++i){
            //Event aux = scraper.listOfTimeTables.get(i).listOfEvents.get(0);
            // eventsGraph.addVertex(aux);
            for(int j = 0; j<scraper.listOfTimeTables.get(i).listOfEvents.size(); ++j){
                Event event = scraper.listOfTimeTables.get(i).listOfEvents.get(j);
                eventsGraph.addVertex(event);
                // aux = event1;
            }

        }


        Iterator<Event> iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();
            Iterator<Event> iter2 = new DepthFirstIterator<>(eventsGraph);
            while(iter2.hasNext()){
                Event vertex2 = iter2.next();
                if(!vertex.equals(vertex2)){
                    if(vertex.getDayOfWeek().equals(vertex2.getDayOfWeek())
                            && vertex.getStartTime().equals(vertex2.getStartTime())
                            && vertex.getEndTime().equals(vertex2.getEndTime())
                            && !vertex.equals(vertex2))
                        eventsGraph.addEdge(vertex,vertex2);
                }
            }
        }


        iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();
            //ystem.out
            //       .println(
            //               "Vertex " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vertex) + " is connected to: "
            //
            //
            //                     + eventsGraph.outgoingEdgesOf(vertex));
            System.out.println(vertex + "\n\n\n");
            Set<DefaultEdge> boss = eventsGraph.outgoingEdgesOf(vertex);

            for(DefaultEdge d : boss)
                System.out.println(d+"\n");

            System.out.println("\n\n\n\n\n");
        }



    }
}