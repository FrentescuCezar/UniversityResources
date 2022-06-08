package com.scraper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeTable.Event;
import com.timeTable.GraphEvent;
import com.timeTable.TimeTable;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.sql.Time;
import java.util.Iterator;
import java.util.Objects;

public class ScraperApplication {
    public static void main(String[] args) throws JsonProcessingException {
        TimeTableScraper scraper = new TimeTableScraper();

        scraper.startScrape();


        Graph<Event, DefaultEdge> eventsGraph = GraphEvent.buildEmptySimpleGraph();

        eventsGraph.addVertex(scraper.listOfTimeTables.get(0).listOfEvents.get(0));
        for(int i = 0; i<scraper.listOfTimeTables.size(); ++i){
            Event aux = scraper.listOfTimeTables.get(0).listOfEvents.get(0);
            for(int j = 1; j<scraper.listOfTimeTables.get(i).listOfEvents.size(); ++j){
                Event event1 = scraper.listOfTimeTables.get(i).listOfEvents.get(j);
                eventsGraph.addVertex(event1);


                if(event1.getDayOfWeek().equals(aux.getDayOfWeek())
                        && event1.getStartTime().equals(aux.getStartTime())
                        && event1.getEndTime().equals(aux.getEndTime())
                        && !event1.getDiscipline().equals(aux.getDiscipline())
                        && !event1.getType().equals(aux.getType())
                        && !event1.getTimeTableName().equals(aux.getTimeTableName())
                ){
                    eventsGraph.addEdge(event1,aux);
                }

                aux = event1;

            }

        }

      ObjectMapper mapper = new ObjectMapper();
      Iterator<Event> iter = new DepthFirstIterator<>(eventsGraph);
      while (iter.hasNext()) {
          Event vertex = iter.next();
          System.out
                  .println(
                          "Vertex " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vertex) + " is connected to: "
                                  + eventsGraph.outgoingEdgesOf(vertex));
      }

    }
}
