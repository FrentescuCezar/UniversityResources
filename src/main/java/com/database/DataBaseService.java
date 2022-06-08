package com.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeTable.Discipline;
import com.timeTable.Event;
import com.timeTable.TimeTable;
import com.timeTable.classes.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DataBaseService{
        private final DataBaseController dataBaseController = new DataBaseController();
        ObjectMapper mapper = new ObjectMapper();


        public void addTimeTableInitial(List<TimeTable> listOfTimeTables) throws JsonProcessingException {
          try {
              StringBuilder sql = new StringBuilder("Insert Into TimeTableInitial (TimeTable, day, start, TimeTableInitial.[end], discipline, type, teacher, room, capacity) " +
                      "Values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
              PreparedStatement statement = dataBaseController.getDataBaseConnection().getConnection().prepareStatement(String.valueOf(sql), Statement.RETURN_GENERATED_KEYS);


              for (TimeTable t : listOfTimeTables) {
                  for (Event e : t.listOfEvents) {
                      statement.setString(1,t.getNameOfTimeTable());
                      statement.setString(2,e.getDayOfWeek());
                      statement.setString(3,e.getStartTime());
                      statement.setString(4,e.getEndTime());
                      statement.setString(5,e.getDiscipline().getName());
                      statement.setString(6,e.getType());
                      statement.setString(7,mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e.getDiscipline().getTeacher()));
                      statement.setString(8,"");
                      statement.setInt(9,0);

                      statement.addBatch();

                      DataBaseController.executeSQL(statement);
                      statement.clearParameters();
                      System.out.println(sql);
                  }
                  statement.close();

              }
          }
          catch (SQLException e){
              e.printStackTrace();
          }
        }

        public List<TimeTable> selectTimeTableInitial(){

            try {
                String sql = "Select TimeTable, day, start, TimeTableInitial.[end], discipline, type, teacher, room, capacity from TimeTableInitial;";
                List<TimeTable> listOfTimeTables = new ArrayList<>();
                ResultSet resultSet;
                resultSet = getDataBaseController().selectSQL(sql);
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
                    Discipline discipline = new Discipline(resultSet.getString(5));
                    event.setDiscipline(discipline);
                    event.setType(resultSet.getString(6));
                    List<String> teachers = new ArrayList<>();
                    teachers = mapper.readValue(resultSet.getString(7),teachers.getClass());
                    discipline.setTeacher(teachers);
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
                    Discipline discipline = new Discipline(resultSet.getString(5));
                    event.setDiscipline(discipline);
                    event.setType(resultSet.getString(6));
                    List<String> teachers = new ArrayList<>();
                    teachers = mapper.readValue(resultSet.getString(7),teachers.getClass());
                    discipline.setTeacher(teachers);

                }
                return listOfTimeTables;
            } catch (SQLException | JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }


        public void addRoomsInitial(Set<Room> listOfRooms){
            StringBuilder sql;

            for(Room r : listOfRooms){
                    sql = new StringBuilder("Insert Into RoomsInitial (room, capacity, type) Values (");
                    String roomName = "'" + r.getName() + "', ";
                    sql.append(roomName);
                    String capacity ="'" + r.getCapacity() + "', ";
                    sql.append(capacity);
                    String type ="'" + r.getType() + "');";
                    sql.append(type);


                   // getDataBaseController().executeSQL();
                    System.out.println(sql);
                }

            }

            public void addDisciplines(List<TimeTable> listOfTimeTables){
            try{
                PreparedStatement statement = dataBaseController
                        .getDataBaseConnection()
                        .getConnection()
                        .prepareStatement(String.valueOf("Insert Into Disciplines (discipline, teacher) Values (?, ?);"), Statement.RETURN_GENERATED_KEYS);
                Set<Discipline> disciplineSet = new HashSet<>();

                for(TimeTable t : listOfTimeTables)
                    for(Event e : t.listOfEvents){
                        disciplineSet.add(e.getDiscipline());
                    }

                for(Discipline d : disciplineSet){
                    statement.setString(1, d.getName());
                    statement.setString(2, mapper.writeValueAsString(d.getTeacher()));

                    statement.addBatch();
                    getDataBaseController().executeSQL(statement);

                }
                statement.close();
            }

            catch (SQLException | JsonProcessingException e){
                e.printStackTrace();
            }



            }



    public DataBaseController getDataBaseController() {
        return dataBaseController;
    }
}
