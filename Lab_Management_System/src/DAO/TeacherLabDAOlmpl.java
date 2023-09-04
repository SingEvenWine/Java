package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Laboratory;
import Entity.Teacher;
import Entity.Teacher_lab;

public class TeacherLabDAOlmpl implements TeacherLabDAO {
    String url = "jdbc:mysql://localhost:3306/lab_manager";
    String username = "root";
    String password = "l18338127060";

    private TeacherDAOlmpl teacherDAOlmpl = new TeacherDAOlmpl();
    private LaboratoryDAOlmpl laboratoryDAOlmpl = new LaboratoryDAOlmpl();

    @Override
    public void addTeacherLab(Teacher teacher, Laboratory lab, String begin_time, String end_time) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement with parameters
            String sql = "INSERT INTO teacher_lab (teacher_id, lab_id, begin_time, end_time) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, teacher.getId());
            statement.setInt(2, lab.getId());
            statement.setString(3, begin_time);
            statement.setString(4, end_time);
            // Execute SQL statement
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteTeacherLab(int teacherId, int labId) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from teacher_lab where teacher_id = ? and lab_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teacherId);
            statement.setInt(2, labId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A row has been deleted.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeacherLab(int labId) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from teacher_lab where lab_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, labId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A row has been deleted.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateTeacherLab(Teacher_lab teacher_lab) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "update teacher_lab set teacher_id = ?, lab_id = ?, begin_time = ?, end_time = ?, is_agree = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher_lab.getTeacher_id());
            statement.setInt(2, teacher_lab.getLab_id());
            statement.setString(3, teacher_lab.getBegin_time());
            statement.setString(4, teacher_lab.getEnd_time());
            statement.setBoolean(5, teacher_lab.isIs_agree());
            statement.setInt(6, teacher_lab.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewTeacherLab() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teacher_lab";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(teacherDAOlmpl.getTeacher(result.getInt("teacher_id")).getName() + " "
                        + laboratoryDAOlmpl.getLaboratory(result.getInt("lab_id")).getName() + " "
                        + result.getString("begin_time") + " " + result.getString("end_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher_lab> getLabApply(Teacher teacher) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teacher_lab where teacher_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher.getId());
            ResultSet result = statement.executeQuery();
            List<Teacher_lab> teacher_labs = new ArrayList<>();
            while (result.next()) {
                int Id = result.getInt("id");
                int teacherId = result.getInt("teacher_id");
                int lab_id = result.getInt("lab_id");
                String begin_time = result.getString("begin_time");
                String end_time = result.getString("end_time");
                boolean is_agree = result.getBoolean("is_agree");
                Teacher_lab teacher_lab = new Teacher_lab(teacherId, lab_id, begin_time, end_time, is_agree);
                teacher_lab.setId(Id);
                teacher_labs.add(teacher_lab);
            }
            return teacher_labs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    public List<Teacher_lab> getAllTeacherLabs() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teacher_lab";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            List<Teacher_lab> teacher_labs = new ArrayList<>();
            while (result.next()) {
                int Id = result.getInt("id");
                int teacherId = result.getInt("teacher_id");
                int lab_id = result.getInt("lab_id");
                String begin_time = result.getString("begin_time");
                String end_time = result.getString("end_time");
                boolean is_agree = result.getBoolean("is_agree");
                Teacher_lab teacher_lab = new Teacher_lab(teacherId, lab_id, begin_time, end_time, is_agree);
                teacher_lab.setId(Id);
                teacher_labs.add(teacher_lab);
            }
            return teacher_labs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Teacher_lab getTeacherLab(int id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from teacher_lab where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int Id = result.getInt("id");
                int teacherId = result.getInt("teacher_id");
                int lab_id = result.getInt("lab_id");
                String begin_time = result.getString("begin_time");
                String end_time = result.getString("end_time");
                boolean is_agree = result.getBoolean("is_agree");
                Teacher_lab teacher_lab = new Teacher_lab(teacherId, lab_id, begin_time, end_time, is_agree);
                teacher_lab.setId(Id);
                return teacher_lab;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLabApply(Teacher_lab teacher_lab){
        boolean res = false;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select is_agree from teacher_lab where lab_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher_lab.getLab_id());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if(result.getBoolean("is_agree")){
                    res = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public boolean isLabApply(int lab_id){
        boolean res = false;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select is_agree from teacher_lab where lab_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lab_id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if(result.getBoolean("is_agree")){
                    res = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        TeacherLabDAOlmpl teacherLabDAOlmpl = new TeacherLabDAOlmpl();
        teacherLabDAOlmpl.deleteTeacherLab(4, 8);
    }
}
