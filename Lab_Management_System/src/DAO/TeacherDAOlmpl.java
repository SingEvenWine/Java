package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Teacher;

public class TeacherDAOlmpl implements TeacherDAO{
    String url = "jdbc:mysql://localhost:3306/lab_manager";
    String username = "root";
    String password = "l18338127060";

    @Override
    public void addTeacher(Teacher teacher) {
        // 添加老师
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement with parameters
            String sql = "INSERT INTO teachers (name, teacher_id, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getTeacherId());
            statement.setString(3, teacher.getPassword());
            // Execute SQL statement
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        //删除老师
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from teachers where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher.getId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A row has been deleted.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        //更新老师
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "update teachers set name = ?, teacher_id = ?, password = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getTeacherId());
            statement.setString(3, teacher.getPassword());
            statement.setInt(4, teacher.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing row has been updated.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacher(int id) {
        //查询老师
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teachers where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                Teacher teacher = new Teacher();
                teacher.setId(result.getInt("id"));
                teacher.setName(result.getString("name"));
                teacher.setTeacherId(result.getString("teacher_id"));
                teacher.setPassword(result.getString("password"));
                return teacher;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher getTeacher(String teacher_id) {
        //查询老师
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teachers where teacher_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teacher_id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                Teacher teacher = new Teacher();
                teacher.setId(result.getInt("id"));
                teacher.setName(result.getString("name"));
                teacher.setTeacherId(result.getString("teacher_id"));
                teacher.setPassword(result.getString("password"));
                return teacher;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Teacher> getAllTeachers() {
        //查询所有老师
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teachers";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            List<Teacher> teachers = new ArrayList<>();
            while(result.next()){
                Teacher teacher = new Teacher();
                teacher.setId(result.getInt("id"));
                teacher.setName(result.getString("name"));
                teacher.setTeacherId(result.getString("teacher_id"));
                teacher.setPassword(result.getString("password"));
                teachers.add(teacher);
                return teachers;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
