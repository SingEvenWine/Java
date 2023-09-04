package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entity.Laboratory;

public class LaboratoryDAOlmpl implements LaboratoryDAO{
    String url = "jdbc:mysql://localhost:3306/lab_manager";
    String username = "root";
    String password = "l18338127060";

    @Override
    public void addLaboratory(Laboratory laboratory) {
        //添加实验室
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "insert into labs (name, location, maxnumber) values(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, laboratory.getName());
            statement.setString(2, laboratory.getLocation());
            statement.setInt(3, laboratory.getMaxNumberOfStudents());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted.");
            }
        }catch(SQLException e){
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        
    }

    @Override
    public void deleteLaboratory(Laboratory laboratory) {
        //删除实验室
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from labs where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, laboratory.getId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A row has been deleted.");
            }
        }catch(SQLException e){
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) {
        //更新实验室
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "update labs set name = ?, location = ?, maxnumber = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, laboratory.getName());
            statement.setString(2, laboratory.getLocation());
            statement.setInt(3, laboratory.getMaxNumberOfStudents());
            statement.setInt(4, laboratory.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing row has been updated.");
            }
        }catch(SQLException e){
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    @Override
    public Laboratory getLaboratory(int id) {
        //查询实验室
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM labs WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                String location = result.getString("location");
                int maxNumberOfStudents = result.getInt("maxnumber");
                Laboratory laboratory = new Laboratory(name, location, maxNumberOfStudents);
                laboratory.setId(id);
                return laboratory;
            } else {
                System.out.println("No laboratory found with id = " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Laboratory> getAllLaboratories() {
        //查询所有实验室
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");

            String sql = "SELECT * FROM labs";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            List<Laboratory> laboratories = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String location = result.getString("location");
                int maxNumberOfStudents = result.getInt("maxnumber");
                Laboratory laboratory = new Laboratory(name, location, maxNumberOfStudents);
                laboratory.setId(id);
                laboratories.add(laboratory);
            }
            return laboratories;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Laboratory> getAllLaboratoriesByLocation(String location) {
        //查询所有实验室
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");

            String sql = "SELECT * FROM labs WHERE location = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location);
            ResultSet result = statement.executeQuery();
            List<Laboratory> laboratories = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                location = result.getString("location");
                int maxNumberOfStudents = result.getInt("maxnumber");
                Laboratory laboratory = new Laboratory(name, location, maxNumberOfStudents);
                laboratory.setId(id);
                laboratories.add(laboratory);
            }
            return laboratories;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Laboratory getLaboratory(String name) {
        // 查询实验室
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM labs WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String location = result.getString("location");
                int maxNumberOfStudents = result.getInt("maxnumber");
                Laboratory laboratory = new Laboratory(name, location, maxNumberOfStudents);
                laboratory.setId(id);
                return laboratory;
            } else {
                System.out.println("No laboratory found with name = " + name + ".");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        LaboratoryDAOlmpl laboratoryDAOlmpl = new LaboratoryDAOlmpl();
        //laboratoryDAOlmpl.addLaboratory(new Laboratory("1号实验室","1号楼", 100));
        //laboratoryDAOlmpl.deleteLaboratory(laboratoryDAOlmpl.getLaboratory(1));
        // Laboratory laboratory = laboratoryDAOlmpl.getLaboratory(1);
        // laboratory.setName("1号实验室");
        // laboratory.setLocation("1号楼");
        // laboratory.setMaxNumberOfStudents(200);
        // laboratoryDAOlmpl.updateLaboratory(laboratory);
        // List<Laboratory> laboratories = laboratoryDAOlmpl.getAllLaboratories();
        // for (Laboratory laboratory : laboratories) {
        //     System.out.println(laboratory.getId() + " " + laboratory.getName() + " " + laboratory.getLocation() + " " + laboratory.getMaxNumberOfStudents());
        // }
        System.out.println(laboratoryDAOlmpl.getLaboratory(9).getName());
    }

    
}