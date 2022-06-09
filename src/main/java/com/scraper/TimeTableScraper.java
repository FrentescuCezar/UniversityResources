package com.scraper;

import com.database.DataBaseService;
import com.timeTable.Discipline;
import com.timeTable.Event;
import com.timeTable.classes.*;
import com.timeTable.TimeTable;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class  TimeTableScraper {

    private static final String url = "https://profs.info.uaic.ro/~orar/globale/orar_complet.html";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
    private static final String referer = "https://google.com";
    private static final List<String> daysOfWeek = new ArrayList<>(Arrays.asList("Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"));
    public List<TimeTable> listOfTimeTables = new ArrayList<>();
    public List<Room> listOfRooms = new ArrayList<>();

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


    private Room createRoom(String info) {
        Room room = new Lecture(0, 0, 0, 0);
        if (info.contains("curs")) {
            room = new Lecture(0, 0, 0, 0);
        } else if (info.contains("seminar")) {
            room = new Seminary(0, 0, 0, 0);
        } else if (info.contains("Laboratoare")) {
            room = new Laboratory(0, 0, 0, 0);
        }

        return room;
    }

    private void room(Document document, List<TimeTable> listOfTimeTables, Set<Room> listOfRooms) throws IOException, InterruptedException {
        Elements tables = document.getElementsByTag("table");
        Set<String> setOfUrl = new HashSet<>();

        for (Element e : tables) {
            if (!daysOfWeek.contains(Objects.requireNonNull(e.select("td").first()).text())) {
                continue;
            }

            Elements trTable = e.getElementsByTag("tr");
            for (Element tr : trTable) {
                Elements tdTable = tr.getElementsByTag("td");
                int count = 0;
                for (Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {

                    } else if (count == 6) {

                        Room room;
                        String urlToConnectRoom = td.select("a").attr("href");

                        Thread.sleep(500);

                        Connection connectToClass = Jsoup.connect("https://profs.info.uaic.ro/~orar" + urlToConnectRoom.replaceFirst(".", "").replaceFirst(".", "")).userAgent(getUserAgent());
                        Document document1 = connectToClass.get();

                        Elements content = document1.select("body b");
                        String info = "";
                        boolean firstTime = true;
                        for (Element c : content) {
                            if (firstTime) {
                                firstTime = false;
                                continue;
                            }

                            info = c.text();
                            break;
                        }


                        if (td.text().equals("")) {

                        } else {
                            room = createRoom(info);
                            if (info.replaceAll("[^0-9]+", "").equals("")) {
                                room.setCapacity(30);
                            } else {
                                room.setCapacity(Integer.parseInt(info.replaceAll("[^0-9]+", "")));
                            }
                            room.setName(td.text());
                            listOfRooms.add(room);

                            System.out.println(room.getName());
                            System.out.println(room.getCapacity());
                            System.out.println(room);

                        }
                    }
                    count++;
                }
            }
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
                for (Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 5) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);

                        Elements aType = td.select("a");

                        for (Element teacher : aType) {
                            event.getDiscipline().setTeacher(teacher.text());
                        }
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
                for (Element td : tdTable) {
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
                for (Element td : tdTable) {
                    if (daysOfWeek.contains(td.text())) {
                        indexOfEvents--;

                    } else if (count == 3) {
                        Event event = timeTable.listOfEvents.get(indexOfEvents);
                        event.setDiscipline(new Discipline(td.select("a").text()));

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
                for (Element td : tdTable) {
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
                for (Element td : tdTable) {
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


    private void daysOfWeek(Document document, List<TimeTable> listOfTimeTables) {
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
                    event.setTimeTableName(timeTable.getNameOfTimeTable());

                }


            }
            indexOfTimeTable++;
        }


    }

    private void mainScrape() {
        try {
            Connection connectToTimeTable = Jsoup.connect(getUrl()).userAgent(getUserAgent()).referrer(getReefer());
            Document document = connectToTimeTable.get();

            nameOfTimeTableScrape(document, listOfTimeTables);
            daysOfWeek(document, listOfTimeTables);
            startTime(document, listOfTimeTables);
            endTime(document, listOfTimeTables);
            nameOfDiscipline(document, listOfTimeTables);
            type(document, listOfTimeTables);
            nameOfTeacher(document, listOfTimeTables);
//
            DataBaseService dataBaseService = new DataBaseService();

            filterTimeTables();

            this.listOfRooms = dataBaseService.selectRoomInitial();



        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void filterTimeTables() {
        List<TimeTable> newListOfTimeTables = listOfTimeTables;
        newListOfTimeTables.removeIf(t -> t.checkNameTimeTableForFilter(t));
        this.listOfTimeTables = newListOfTimeTables;
    }
}

