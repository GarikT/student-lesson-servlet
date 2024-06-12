package org.example.studentlessonservlet.manager;
import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.User;

import java.sql.*;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public void add(User user) {
        String sql = "INSERT INTO user (name, surname, email, password) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id = " +id;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = '" + email + "'";
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }


    public User getUserById(int id) {
        String sql = "select * from user where id = " + id;
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
}
