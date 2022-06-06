package com.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeTable.Event;
import com.timeTable.TimeTable;

import java.util.List;

public class DataBaseService{
        private DataBaseController dataBaseController = new DataBaseController();
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
                    String teacher ="'" + e.getTeacher() + "', ";
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

    public DataBaseController getDataBaseController() {
        return dataBaseController;
    }
}
