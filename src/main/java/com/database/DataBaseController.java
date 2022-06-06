package com.database;

import com.timeTable.TimeTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController{
    private static final DataBaseConnection dataBaseConnection = new DataBaseConnection();

    public DataBaseConnection getDataBaseConnection() {
        return dataBaseConnection;
    }

    public void executeSQL(String sql){
        try{
            PreparedStatement statement = dataBaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = null;

            statement.execute();

            resultSet = statement.getGeneratedKeys();

            while(resultSet.next()){
                System.out.println("key : " + resultSet.getString(1));
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }



    }


}
