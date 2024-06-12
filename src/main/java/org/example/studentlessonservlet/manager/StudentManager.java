package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.Student;
import org.example.studentlessonservlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private LessonManager lessonManager = new LessonManager();
    private UserManager userManager = new UserManager();

    public List<Student> getAll(){
        String sql = "select * from students";
        List<Student> students = new ArrayList<>();
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            User user = null;
            int userId = -1;
            while(resultSet.next()){
                userId = resultSet.getInt("user_id");
                user = userManager.getUserById(userId);
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                        .picName(resultSet.getString("pic_name"))
                        .user(user)
                        .build());
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return students;
    }

    public List<Student> getAll(int user_id){
        String sql = "select * from students where user_id = '" + user_id + "'";
        List<Student> students = new ArrayList<>();
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            User user = null;
            int userId = -1;
            while(resultSet.next()){
                userId = resultSet.getInt("user_id");
                user = userManager.getUserById(userId);
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                        .picName(resultSet.getString("pic_name"))
                        .user(user)
                        .build());
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return students;
    }

    public void add(Student student) {
        String sql = "INSERT INTO students (name, surname, email, age, lesson_id, pic_name, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setString(6, student.getPicName());
            preparedStatement.setInt(7, student.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                student.setId(id);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = " +id;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Boolean studentExists(String email) {
        String sql = "select * from students where email = '" + email + "'";
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                if(resultSet.getString("email").equals(email)){
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






/*
        public List<Employee> getByCompanyId(int companyId) {
            String sql = "select * from employee where company_id = " +companyId;
            List<Employee> employees = new ArrayList<>();
            try( Statement statement = connection.createStatement() ){
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    employees.add(Employee.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .surname(resultSet.getString("surname"))
                            .email(resultSet.getString("email"))
                            .company(companyManager.getCompanyById(resultSet.getInt("company_id")))
                            .build());
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            return employees;
        }*/
    }
