package com.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeTable.Event;
import com.timeTable.TimeTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService{
        private final DataBaseController dataBaseController = new DataBaseController();
        ObjectMapper mapper = new ObjectMapper();


        public void addTimeTableInitial(List<TimeTable> listOfTimeTables) throws JsonProcessingException {
            StringBuilder sql;

            for(TimeTable t : listOfTimeTables){
                for(Event e : t.listOfEvents){
                    sql = new StringBuilder("Insert Into TimeTableInitial (TimeTable, day, start, TimeTableInitial.[end], discipline, type, teacher, room, capacity) Values (");
                    String TimeTableName = "'" + t.getNameOfTimeTable() + "', ";
                    sql.append(TimeTableName);
                    String day ="'" + e.getDayOfWeek() + "', ";
                    sql.append(day);
                    String start ="'" + e.getStartTime() + "', ";
                    sql.append(start);
                    String end ="'" + e.getEndTime() + "', ";
                    sql.append(end);
                    String discipline ="'" + e.getNameOfDiscipline() + "', ";
                    sql.append(discipline);
                    String type ="'" + e.getType() + "', ";
                    sql.append(type);
                    String teacher ="'" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e.getTeacher()) + "', ";
                    sql.append(teacher);
                    String room ="'" + "" + "', ";
                    sql.append(room);
                    String capacity = "'" + 0 + "');";
                    sql.append(capacity);

                    getDataBaseController().executeSQL(sql.toString());
                    System.out.println(sql);
                }

            }
        }

        public List<TimeTable> selectTimeTableInitial(){

            try {
                StringBuilder sql = new StringBuilder("Select TimeTable, day, start, TimeTableInitial.[end], discipline, type, teacher, room, capacity from TimeTableInitial;");
                List<TimeTable> listOfTimeTables = new ArrayList<>();
                ResultSet resultSet;
                resultSet = getDataBaseController().selectSQL(sql.toString());
                String currentTimeTableName = "";
               while (resultSet.next()) {
                   TimeTable timeTable = new TimeTable();
                   Event event = new Event();
                   currentTimeTableName = resultSet.getString(1);
                    listOfTimeTables.add(timeTable);
                    timeTable.setNameOfTimeTable(currentTimeTableName);
                    timeTable.listOfEvents.add(event);

                    event.setDayOfWeek(resultSet.getString(2));
                    event.setStartTime(resultSet.getString(3));
                    event.setEndTime(resultSet.getString(4));
                    event.setNameOfDiscipline(resultSet.getString(5));
                    event.setType(resultSet.getString(6));
                    event.setTeacher(mapper.readValue(resultSet.getString(7),List.class));
                    break;
               }

               int indexOfTimeTable = 0;
                TimeTable timeTable;
                while(resultSet.next()){
                    if(!resultSet.getString(1).equals(currentTimeTableName)){
                        currentTimeTableName = resultSet.getString(1);
                        timeTable = new TimeTable();
                        listOfTimeTables.add(timeTable);
                        timeTable.setNameOfTimeTable(currentTimeTableName);
                        indexOfTimeTable++;
                    }
                    else{
                        timeTable = listOfTimeTables.get(indexOfTimeTable);
                    }
                    Event event = new Event();
                    timeTable.listOfEvents.add(event);

                    event.setDayOfWeek(resultSet.getString(2));
                    event.setStartTime(resultSet.getString(3));
                    event.setEndTime(resultSet.getString(4));
                    event.setNameOfDiscipline(resultSet.getString(5));
                    event.setType(resultSet.getString(6));
                    event.setTeacher(mapper.readValue(resultSet.getString(7),List.class));
                    event.setRoom(null);

                }

                return listOfTimeTables;
            } catch (SQLException | JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }



    public DataBaseController getDataBaseController() {
        return dataBaseController;
    }
}
