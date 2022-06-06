package com.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeTable.Event;
import com.timeTable.classes.Laboratory;
import com.timeTable.classes.Lecture;
import com.timeTable.classes.Room;
import com.timeTable.TimeTable;
import com.timeTable.classes.Seminary;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TimeTableScraper {

    private static final String url = "https://profs.info.uaic.ro/~orar/globale/orar_complet.html";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
    private static final String referer = "https://google.com";
    private static final List<String> daysOfWeek = new ArrayList<>(Arrays.asList("Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"));

    public String getReefer() {
        return referer;
    }

    public String getUrl() {
        return url;
    }

    public String getUserAgent() {
        return userAgent;
    }


    public void startScrape() {
        mainScrape();
    }


    private void nameOfTimeTableScrape(Document document, List<TimeTable> listOfTimeTables) {
        Elements nameOfTables = document.getElementsByTag("h2");
        for (Element name : nameOfTables) {
            TimeTable timeTable = new TimeTable();
            listOfTimeTables.add(timeTable);
            timeTable.setNameOfTimeTable(name.text());
        }
    }


    private Room createRoom(String info){
        Room room = new Lecture(0,0,0,0);
        if(info.contains("curs")){
            room = new Lecture(0,0,0,0);
        }
        else if(info.contains("seminar")){
            room = new Seminary(0,0,0,0);
        }

        else if(info.contains("Laboratoare")){
            room = new Laboratory(0,0,0,0);
        }

        return room;
    }

    private void room(Document document, List<TimeTable> listOfTimeTables, List<Room> listOfRooms) throws IOException, InterruptedException {
        Elements tables = document.getElementsByTag("table");

        int indexOfTimeTable = 0;
        int indexOfEvents;
        for (Element e : tables) {
            indexOfEvents = 0;
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                int count = 0;
                for(Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 6) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);

                        Room room;
                        String urlToConnectRoom = td.select("a").attr("href");

                        //System.out.println(urlToConnectRoom);
                        Thread.sleep(250);
                        Connection connectToClass = Jsoup.connect("https://profs.info.uaic.ro/~orar" + urlToConnectRoom.replaceFirst(".", "").replaceFirst(".", "")).userAgent(userAgent);
                        Document document1 = connectToClass.get();

                        Elements content = document1.select("body b");
                        String info = "";
                        boolean firstTime = true;
                        for(Element c : content){
                            if(firstTime){
                                firstTime = false;
                                continue;
                            }

                            info = c.text();
                            break;
                        }


                        if(td.text().equals("")) {
                            System.out.println(event);
                        }
                        else{
                            room = createRoom(info);

                            room.setCapacity(Integer.parseInt(info.replaceAll("[^0-9]+", "")));
                            room.setLinkToClass(td.select("a").attr("href"));
                            room.setName(td.text());
                            //TODO(Razvan) : Set capacity and resources
                            listOfRooms.add(room);
                            event.setRoom(room);

                            //System.out.println(td.text());
                            //System.out.println(info);
                            //System.out.println(room.getName());
                            //System.out.println(room.getCapacity());
                            //System.out.println(room);

                        }

                        //System.out.println(room.getCapacity());
                       // System.out.println(room.getName());


                    }
                    count++;
                }
                indexOfEvents++;
            }
            indexOfTimeTable++;
        }
    }


    private void nameOfTeacher(Document document, List<TimeTable> listOfTimeTables) {
        Elements tables = document.getElementsByTag("table");

        int indexOfTimeTable = 0;
        int indexOfEvents;
        for (Element e : tables) {
            indexOfEvents = 0;
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                int count = 0;
                for(Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 5) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);
                        List<String> teachers = new ArrayList<>();

                        Elements aType = td.select("a");

                        for(Element teacher : aType){
                            teachers.add(teacher.text());
                        }
                        event.setTeacher(teachers);
                    }
                    count++;
                }
                indexOfEvents++;
            }
            indexOfTimeTable++;
        }
    }


    private void type(Document document, List<TimeTable> listOfTimeTables) {
        Elements tables = document.getElementsByTag("table");

        int indexOfTimeTable = 0;
        int indexOfEvents;
        for (Element e : tables) {
            indexOfEvents = 0;
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                int count = 0;
                for(Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 4) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);
                        event.setType(td.text());

                    }
                    count++;
                }
                indexOfEvents++;
            }
            indexOfTimeTable++;
        }
    }


    private void nameOfDiscipline(Document document, List<TimeTable> listOfTimeTables) {
        Elements tables = document.getElementsByTag("table");

        int indexOfTimeTable = 0;
        int indexOfEvents;
        for (Element e : tables) {
            indexOfEvents = 0;
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                int count = 0;
                for(Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 3) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);
                        event.setNameOfDiscipline(td.select("a").text());

                    }
                    count++;
                }
                indexOfEvents++;
            }
            indexOfTimeTable++;
        }
    }


    private void endTime(Document document, List<TimeTable> listOfTimeTables) {
        Elements tables = document.getElementsByTag("table");

        int indexOfTimeTable = 0;
        int indexOfEvents;
        for (Element e : tables) {
            indexOfEvents = 0;
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                int count = 0;
                for(Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 1) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);
                        event.setEndTime(td.text());

                    }
                    count++;
                }
                indexOfEvents++;
            }
            indexOfTimeTable++;
        }
    }

    private void startTime(Document document, List<TimeTable> listOfTimeTables) {
        Elements tables = document.getElementsByTag("table");

        int indexOfTimeTable = 0;
        int indexOfEvents;
        for (Element e : tables) {
            indexOfEvents = 0;
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                boolean count = true;
                    for(Element td : tdTable) {
                        if (daysOfWeek.contains(td.text())) {
                            indexOfEvents--;

                        } else if (count) {
                            Event event = timeTable.listOfEvents.get(indexOfEvents);
                            event.setStartTime(td.text());
                            count = false;

                        }
                    }
                indexOfEvents++;
            }
            indexOfTimeTable++;
        }
    }


    private void daysOfWeek(Document document, List<TimeTable> listOfTimeTables){
        Elements tables = document.getElementsByTag("table");
        String dayOfWeek = "";

        int indexOfTimeTable = 0;
        for (Element e : tables) {
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements tdTable = e.getElementsByTag("tr");
            TimeTable timeTable = listOfTimeTables.get(indexOfTimeTable);

            for (Element tr : tdTable) {
                if (daysOfWeek.contains(tr.text())) {
                    dayOfWeek = tr.text();
                } else {
                    Event event = new Event();
                    timeTable.listOfEvents.add(event);
                    event.setDayOfWeek(dayOfWeek);

                }


            }
            indexOfTimeTable++;
        }


    }

    private void mainScrape() {
        try {
            Connection connectToTimeTable = Jsoup.connect(getUrl()).userAgent(getUserAgent()).referrer(getReefer());
            Document document = connectToTimeTable.get();

            List<TimeTable> listOfTimeTables = new ArrayList<>();
            List<Room> listOfRooms = new ArrayList<>();

            nameOfTimeTableScrape(document, listOfTimeTables);
            daysOfWeek(document, listOfTimeTables);
            startTime(document,listOfTimeTables);
            endTime(document,listOfTimeTables);
            nameOfDiscipline(document,listOfTimeTables);
            type(document,listOfTimeTables);
            nameOfTeacher(document,listOfTimeTables);
            room(document,listOfTimeTables,listOfRooms);


          printTimeTable(listOfTimeTables);


        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

private void printTimeTable(List<TimeTable> listOfTimeTables) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
    for (TimeTable t : listOfTimeTables) {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
        System.out.println(json);
    }
}


}

