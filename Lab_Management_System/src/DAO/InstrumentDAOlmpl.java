package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Entity.Instrument;

public class InstrumentDAOlmpl implements InstrumentDAO{
    String url = "jdbc:mysql://localhost:3306/lab_manager";
    String username = "root";
    String password = "l18338127060";
    @Override
    public void addInstrument(Instrument instrument) {
        // 添加仪器
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "insert into instruments (name, description, lab_id) values(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, instrument.getName());
            statement.setString(2, instrument.getDescription());
            statement.setInt(3, instrument.getLab_id());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInstrument(Instrument instrument) {
        //删除仪器
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from instruments where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, instrument.getId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A row has been deleted.");
            }
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstrument(Instrument instrument) {
        //更新仪器
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "update instruments set name = ?, description = ?, isdamaged = ?, lab_id = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, instrument.getName());
            statement.setString(2, instrument.getDescription());
            statement.setBoolean(3, instrument.isDamaged());
            statement.setInt(4, instrument.getLab_id());
            statement.setInt(5, instrument.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing row has been updated.");
            }
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    @Override
    public Instrument getInstrument(int id) {
        //读取仪器
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from instruments where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Instrument instrument = new Instrument(result.getString("name"), result.getString("description"), result.getInt("lab_id"));
                instrument.setId(result.getInt("id"));
                instrument.setDamaged(result.getBoolean("isdamaged"));
                return instrument;
            }
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Instrument> getInstrumentByLab_id(int lab_id) {
        //读取仪器
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from instruments where lab_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lab_id);
            ResultSet result = statement.executeQuery();
            List<Instrument> instruments = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                boolean isDamaged = result.getBoolean("isdamaged");
                lab_id = result.getInt("lab_id");
                Instrument instrument = new Instrument(name,description,lab_id);
                instrument.setId(id);
                instrument.setDamaged(isDamaged);
                instruments.add(instrument);
            }
            return instruments;
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Instrument> getInstrumentByName(String name) {
        //读取仪器
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from instruments where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            List<Instrument> instruments = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                name = result.getString("name");
                String description = result.getString("description");
                boolean isDamaged = result.getBoolean("isdamaged");
                int lab_id = result.getInt("lab_id");
                Instrument instrument = new Instrument(name,description,lab_id);
                instrument.setId(id);
                instrument.setDamaged(isDamaged);
                instruments.add(instrument);
            }
            return instruments;
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Instrument> getAllInstruments() {
        //读取所有仪器
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");

            String sql = "SELECT * FROM instruments";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            List<Instrument> instruments = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                boolean isDamaged = result.getBoolean("isdamaged");
                int lab_id = result.getInt("lab_id");
                Instrument instrument = new Instrument(name,description,lab_id);
                instrument.setId(id);
                instrument.setDamaged(isDamaged);
                instruments.add(instrument);
            }
            return instruments;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        InstrumentDAOlmpl instrumentDAOlmpl = new InstrumentDAOlmpl();
        //instrumentDAOlmpl.addInstrument(new Instrument("仪器1", "仪器1的描述"));
        //instrumentDAOlmpl.deleteInstrument(instrument);
        // Instrument instrument = instrumentDAOlmpl.getInstrument(1);
        // instrument.setName("仪器1");
        // instrument.setDescription("仪器1的描述");
        // instrument.setDamaged(true);
        // instrumentDAOlmpl.updateInstrument(instrument);
        List<Instrument> instruments = instrumentDAOlmpl.getAllInstruments();
        for (Instrument instrument : instruments) {
            System.out.println(instrument.getId() + " " + instrument.getName() + " " + instrument.getDescription() + " " + instrument.isDamaged());
        }
    }
}