package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<Lesson> getLessons(){
        String sql = "select * from lessons";
        List<Lesson> lessons = new ArrayList<>();
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getInt("duration"))
                        .lecturer_name(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .build());
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public Lesson getLessonById(int id){
        String sql = "select * from lessons where id = " + id;
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getInt("duration"))
                        .lecturer_name(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .build();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lessons (name, duration, lecturer_name, price) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setInt(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturer_name());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM lessons WHERE id = " +id;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}

