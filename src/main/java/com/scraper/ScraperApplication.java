package com.scraper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeTable.Event;
import com.timeTable.algorithm.Edge;
import com.timeTable.algorithm.GraphEvent;
import com.timeTable.classes.Miscellaneous;
import com.timeTable.classes.Room;
import com.timeTable.classes.TypeOfRoom;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class ScraperApplication {
    public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {

        PrintStream fileOut = new PrintStream("src\\main\\java\\com\\scraper\\output.txt");
        System.setOut(fileOut);
        TimeTableScraper scraper = new TimeTableScraper();
        ObjectMapper mapper = new ObjectMapper();

        scraper.startScrape();

        Graph<Event, Edge> eventsGraph = GraphEvent.buildEmptySimpleGraph();

        for (int i = 0; i < scraper.listOfTimeTables.size(); ++i) {
            //Event aux = scraper.listOfTimeTables.get(i).listOfEvents.get(0);
            // eventsGraph.addVertex(aux);
            for (int j = 0; j < scraper.listOfTimeTables.get(i).listOfEvents.size(); ++j) {
                Event event = scraper.listOfTimeTables.get(i).listOfEvents.get(j);
                eventsGraph.addVertex(event);
                // aux = event1;
            }

        }


        Iterator<Event> iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();
            Iterator<Event> iter2 = new DepthFirstIterator<>(eventsGraph);
            while (iter2.hasNext()) {
                Event vertex2 = iter2.next();
                if (!vertex.equals(vertex2)) {
                    if (vertex.getDayOfWeek().equals(vertex2.getDayOfWeek())
                            && vertex.getStartTime().equals(vertex2.getStartTime())
                            && vertex.getEndTime().equals(vertex2.getEndTime())
                            && !vertex.equals(vertex2))
                        eventsGraph.addEdge(vertex, vertex2);
                }
            }
        }

        Map<List<String>, List<Room>> roomsAssignedMap = new HashMap<>();


        iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();

            roomsAssignedMap.put(new ArrayList<>(Arrays.asList(vertex.getDayOfWeek(), vertex.getStartTime(), vertex.getEndTime())), new ArrayList<>());
        }


        List<Room> allRooms = scraper.listOfRooms;
        iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();

            Set<Edge> edges = eventsGraph.edgesOf(vertex);

            List<Room> listOfRoomsAssigned = new ArrayList<>();

            for (Edge d : edges) {
                List<String> startTimeEndTime = new ArrayList<>(Arrays.asList(vertex.getDayOfWeek(), d.returnSource().getStartTime(), d.returnSource().getEndTime()));
                if (d.returnSource().getRoom() == null) {
                    for (Room r : allRooms) {
                        if (d.returnSource().getType().equals("C") && r.getType().equals(TypeOfRoom.LECTURE)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnSource().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }

                        }
                        if (d.returnSource().getType().equals("S") && r.getType().equals(TypeOfRoom.SEMINARY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnSource().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }

                        if (d.returnSource().getType().equals("L") && r.getType().equals(TypeOfRoom.LABORATORY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnSource().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }

                        if (d.returnSource().getType().equals("A") && r.getType().equals(TypeOfRoom.SEMINARY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnSource().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }
                        if (d.returnSource().getType().equals("PC") && r.getType().equals(TypeOfRoom.LABORATORY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnSource().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }
                    }
                } else if (d.returnTarget().getRoom() == null) {
                    for (Room r : allRooms) {
                        if (d.returnTarget().getType().equals("C") && r.getType().equals(TypeOfRoom.LECTURE)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnTarget().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }

                        }
                        if (d.returnTarget().getType().equals("S") && r.getType().equals(TypeOfRoom.SEMINARY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnTarget().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }

                        if (d.returnTarget().getType().equals("L") && r.getType().equals(TypeOfRoom.LABORATORY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnTarget().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }

                        if (d.returnTarget().getType().equals("A") && r.getType().equals(TypeOfRoom.SEMINARY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnTarget().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }
                        if (d.returnTarget().getType().equals("PC") && r.getType().equals(TypeOfRoom.LABORATORY)) {
                            if (!roomsAssignedMap.get(startTimeEndTime).contains(r)) {
                                d.returnTarget().setRoom(r);
                                roomsAssignedMap.put(startTimeEndTime, listOfRoomsAssigned);
                                listOfRoomsAssigned.add(r);
                                break;
                            }
                        }
                    }
                }
            }
        }


        Miscellaneous miscellaneous = Miscellaneous.getInstance();
        int nrLecture = 0;
        int nrSeminary = 0;
        int nrLaboratory = 0;
        for (Room room1 : allRooms) {
            switch (room1.getType()) {
                case LECTURE -> {
                    nrLecture++;
                }
                case SEMINARY -> {
                    nrSeminary++;
                }

                case LABORATORY -> {
                    nrLaboratory++;
                }

                default -> {
                }

            }
        }

        int totalNumberOfChalk = miscellaneous.getTotalNumberOfChalk();
        int totalNumberOfSponges = miscellaneous.getTotalNumberOfSponges();
        int totalNumberOfComputers = miscellaneous.getTotalNumberOfComputers();
        int totalNumberOfVideoProjectors = miscellaneous.getTotalNumberOfVideoProjectors();

        int lectureDistributionChalk = (totalNumberOfChalk / (Math.abs(nrLaboratory - nrSeminary))) / nrLecture;
        int lectureDistributionSponges = (totalNumberOfSponges / (Math.abs(nrLaboratory - nrSeminary))) / nrLecture;
        int laboratoryDistributionChalk = totalNumberOfChalk / nrSeminary;
        int laboratoryDistributionComputers = totalNumberOfComputers/nrLaboratory;
        int seminaryDistributionChalk = totalNumberOfChalk / nrLaboratory;
        int seminaryDistributionComputers = totalNumberOfComputers/nrSeminary;
        for (Room room : allRooms) {
            if (room.getType().equals(TypeOfRoom.LECTURE)) {
                if (lectureDistributionChalk < 10)
                    room.setNumberOfChalk(Miscellaneous.getInstance().minusNumberOfChalk(lectureDistributionChalk));
                else
                    room.setNumberOfChalk(Miscellaneous.getInstance().minusNumberOfChalk(5));
                room.setNumberOfSponges(Miscellaneous.getInstance().minusNumberOfSponges(lectureDistributionSponges));
                room.setNumberOfVideoProjectors(Miscellaneous.getInstance().minusNumberOfVideoProjectors(1));
                room.setNumberOfComputers(Miscellaneous.getInstance().minusNumberOfComputers(1));
            }
        }

        for (Room room : allRooms) {
            if (room.getType().equals(TypeOfRoom.SEMINARY)) {
                room.setNumberOfChalk(Miscellaneous.getInstance().minusNumberOfChalk(seminaryDistributionChalk));
                room.setNumberOfSponges(Miscellaneous.getInstance().minusNumberOfSponges(1));
                room.setNumberOfVideoProjectors(Miscellaneous.getInstance().minusNumberOfVideoProjectors(1));
                room.setNumberOfComputers(Miscellaneous.getInstance().minusNumberOfComputers(seminaryDistributionComputers));
            }
        }



        for (Room room : allRooms) {
            if (room.getType().equals(TypeOfRoom.LABORATORY)) {
                room.setNumberOfChalk(Miscellaneous.getInstance().minusNumberOfChalk(laboratoryDistributionChalk));
                room.setNumberOfSponges(Miscellaneous.getInstance().minusNumberOfSponges(1));
                room.setNumberOfVideoProjectors(Miscellaneous.getInstance().minusNumberOfVideoProjectors(1));
                room.setNumberOfComputers(Miscellaneous.getInstance().minusNumberOfComputers(laboratoryDistributionComputers));
            }
        }


/*
        iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();

            System.out.println("Source");
            System.out.println(vertex + "\n");

            Set<Edge> edges = eventsGraph.edgesOf(vertex);

            for(Edge e : edges)
                System.out.println("TARGET\n" + e.returnTarget() + "\n");
        }
        System.out.println("\n\n\n\n\n");


 */


        scraper.printTimeTable(scraper.listOfTimeTables);
    }
}