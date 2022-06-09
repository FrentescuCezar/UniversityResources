package com.timeTable.algorithm;

import com.scraper.TimeTableScraper;
import com.timeTable.Event;
import com.timeTable.TimeTable;
import com.timeTable.classes.Miscellaneous;
import com.timeTable.classes.Room;
import com.timeTable.classes.TypeOfRoom;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class ResourcesAlgorithm {
        TimeTableScraper scraper = new TimeTableScraper();

    public Graph<Event, Edge> startAssignClasses() throws FileNotFoundException {
        PrintStream fileOut = new PrintStream("src\\main\\java\\com\\scraper\\output.txt");
        System.setOut(fileOut);


        scraper.startScrape();

        Graph<Event, Edge> eventsGraph;
        eventsGraph = addAllVertexes(scraper.listOfTimeTables);


        addAllEdges(eventsGraph);


        Map<List<String>, List<Room>> roomsAssignedMap ;
        roomsAssignedMap = createAssignedRoomsMap(eventsGraph);


        distributionOfClassesAlgorithm(roomsAssignedMap,eventsGraph,scraper.listOfRooms);


        printGraph(eventsGraph);

        return eventsGraph;

    }
    public void startAssignResources(){
        scraper.startScrape();
        distributionOfMiscellaneous(scraper.listOfRooms);
    }

    public void printGraph(Graph<Event, Edge> eventsGraph){
        Iterator<Event> iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();

            System.out.println("Source");
            System.out.println(vertex + "\n");

            Set<Edge> edges = eventsGraph.edgesOf(vertex);

            for(Edge e : edges)
                System.out.println("TARGET\n" + e.returnTarget() + "\n");
        }
        System.out.println("\n\n\n\n\n");
    }

    private Graph<Event, Edge> addAllVertexes(List<TimeTable> listOfTimeTables) {
        Graph<Event, Edge> eventsGraph = GraphEvent.buildEmptySimpleGraph();
        for (int i = 0; i < listOfTimeTables.size(); ++i) {
            for (int j = 0; j < listOfTimeTables.get(i).listOfEvents.size(); ++j) {
                Event event = listOfTimeTables.get(i).listOfEvents.get(j);
                eventsGraph.addVertex(event);
            }
        }

        return eventsGraph;
    }

    private Graph<Event, Edge> addAllEdges(Graph<Event, Edge> eventsGraph) {
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
        return eventsGraph;
    }

    private Map<List<String>, List<Room>> createAssignedRoomsMap(Graph<Event, Edge> eventsGraph) {
        Map<List<String>, List<Room>> roomsAssignedMap = new HashMap<>();
        Iterator<Event> iter = new DepthFirstIterator<>(eventsGraph);
        while (iter.hasNext()) {
            Event vertex = iter.next();

            roomsAssignedMap.put(new ArrayList<>(Arrays.asList(vertex.getDayOfWeek(), vertex.getStartTime(), vertex.getEndTime())), new ArrayList<>());
        }
        return roomsAssignedMap;
    }


    private Graph<Event, Edge> distributionOfClassesAlgorithm(Map<List<String>, List<Room>> roomsAssignedMap, Graph<Event, Edge> eventsGraph, List<Room> allRooms) {
        Iterator<Event> iter = new DepthFirstIterator<>(eventsGraph);
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
        return eventsGraph;
    }

    private void distributionOfMiscellaneous(List<Room> allRooms){
        Miscellaneous miscellaneous = Miscellaneous.getInstance();
        int nrLecture = 0;
        int nrSeminary = 0;
        int nrLaboratory = 0;
        for (Room room1 : allRooms) {
            if(room1.getType().equals(TypeOfRoom.LECTURE))
                nrLecture++;
            else if(room1.getType().equals(TypeOfRoom.SEMINARY))
                nrSeminary++;
            else if(room1.getType().equals(TypeOfRoom.LABORATORY))
                nrLaboratory++;
        }
        Miscellaneous.getInstance().setTotalNumberOfChalk(3000);
        Miscellaneous.getInstance().setTotalNumberOfComputers(500);
        Miscellaneous.getInstance().setTotalNumberOfSponges(30);
        Miscellaneous.getInstance().setTotalNumberOfVideoProjectors(30);

        int totalNumberOfChalk = miscellaneous.getTotalNumberOfChalk();
        int totalNumberOfSponges = miscellaneous.getTotalNumberOfSponges();
        int totalNumberOfComputers = miscellaneous.getTotalNumberOfComputers();

        int lectureDistributionChalk = (totalNumberOfChalk / (Math.abs(nrLaboratory - nrSeminary))) / nrLecture;
        int laboratoryDistributionChalk = totalNumberOfChalk / nrSeminary;
        int laboratoryDistributionComputers = totalNumberOfComputers/nrLaboratory;
        int seminaryDistributionChalk = totalNumberOfChalk / nrLaboratory;
        int seminaryDistributionComputers = totalNumberOfComputers/nrSeminary;
        for (Room room : allRooms) {
            if (room.getType().equals(TypeOfRoom.LECTURE)) {
                if (lectureDistributionChalk < 10){
                    room.setNumberOfChalk(lectureDistributionChalk);
                    Miscellaneous.getInstance().minusNumberOfChalk(lectureDistributionChalk);
                }

                else{
                    room.setNumberOfChalk(5);
                    Miscellaneous.getInstance().minusNumberOfChalk(5);
                }
                room.setNumberOfSponges(1);
                Miscellaneous.getInstance().minusNumberOfSponges(1);
                room.setNumberOfVideoProjectors(1);
                Miscellaneous.getInstance().minusNumberOfVideoProjectors(1);
                room.setNumberOfComputers(1);
                Miscellaneous.getInstance().minusNumberOfComputers(1);
            }
        }

        for (Room room : allRooms) {
            if (room.getType().equals(TypeOfRoom.SEMINARY)) {
                room.setNumberOfChalk(seminaryDistributionChalk);
                Miscellaneous.getInstance().minusNumberOfChalk(seminaryDistributionChalk);
                room.setNumberOfSponges(1);
                Miscellaneous.getInstance().minusNumberOfSponges(1);
                room.setNumberOfVideoProjectors(1);
                Miscellaneous.getInstance().minusNumberOfVideoProjectors(1);
                room.setNumberOfComputers(seminaryDistributionComputers);
                Miscellaneous.getInstance().minusNumberOfComputers(seminaryDistributionComputers);
            }
        }



        for (Room room : allRooms) {
            if (room.getType().equals(TypeOfRoom.LABORATORY)) {
                room.setNumberOfChalk(laboratoryDistributionChalk);
                Miscellaneous.getInstance().minusNumberOfChalk(laboratoryDistributionChalk);
                room.setNumberOfSponges(1);
                Miscellaneous.getInstance().minusNumberOfSponges(1);
                room.setNumberOfVideoProjectors(1);
                Miscellaneous.getInstance().minusNumberOfVideoProjectors(1);
                room.setNumberOfComputers(laboratoryDistributionComputers);
                Miscellaneous.getInstance().minusNumberOfComputers(laboratoryDistributionComputers);
            }
        }
    }




}

