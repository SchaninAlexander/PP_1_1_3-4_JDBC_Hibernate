package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                       "CREATE TABLE IF NOT EXISTS User" +
                           "(id BIGINT AUTO_INCREMENT NOT NULL," +
                           "name VARCHAR(50)," +
                           "lastName VARCHAR(50)," +
                           "age TINYINT," +
                           "PRIMARY KEY (id))")) {
             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {

        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("DROP TABLE IF EXISTS User")) {
             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
       try (Connection connection = Util.getConnection();
       PreparedStatement pstmt =  connection.prepareStatement(
            "INSERT INTO User (name, lastName, age) VALUES(?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM User WHERE id = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM User")) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("TRUNCATE TABLE User")) {
             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}