package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();
    UserManager userManager = new UserManager();

    public List<Lesson> getLessons(int user_id) throws SQLException {
        String sql = "select * from lessons where user_id = '" + user_id + "'";
        List<Lesson> lessons = new ArrayList<>();
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int userId = resultSet.getInt("user_id");
                User user = userManager.getUserById(userId);

                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getInt("duration"))
                        .lecturer_name(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(user)
                        .build());
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public List<Lesson> getLessons(){
        String sql = "select * from lessons";
        List<Lesson> lessons = new ArrayList<>();
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int userId = resultSet.getInt("user_id");
                User user = userManager.getUserById(userId);

                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getInt("duration"))
                        .lecturer_name(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(user)
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
        String sql = "INSERT INTO lessons (name, duration, lecturer_name, price, user_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setInt(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturer_name());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getUser().getId());
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

    public Boolean lessonExists(String name) {
        String sql = "select * from lessons where name = '" + name + "'";
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                if(resultSet.getString("name").equals(name)){
                    return true;
                }else{
                    return false;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
}

