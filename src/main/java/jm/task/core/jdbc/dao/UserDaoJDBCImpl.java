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

        try {
            Connection connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();

            String SQL = "CREATE TABLE IF NOT EXISTS newUsers " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY not NULL, " +
                    " name VARCHAR(50), " +
                    " lastname VARCHAR (50), " +
                    " age TINYINT )";

            statement.executeUpdate(SQL);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }

    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();

            String SQL = "DROP TABLE IF EXISTS newUsers";

            statement.executeUpdate(SQL);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }


    }

    public void saveUser(String n, String lastName, byte age) {
        try {
            Connection connection = Util.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO newUsers (name, lastName, age) VALUES(?,?,?)");
            statement.setString(1, n);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }

    }

    public void removeUserById(long id) {
        try {
            Connection connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();

            String SQL = "DELETE FROM newUsers WHERE id = " + id;

            statement.executeUpdate(SQL);
            connection.close();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            Connection connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();

            String sql = "Select id, name, lastname, age from newUsers";

            ResultSet rs = statement.executeQuery(sql);
            User user;
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastname = rs.getString(3);
                byte age = rs.getByte(4);
                user = new User(id, name, lastname, age);
                allUsers.add(user);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getMySQLConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM newUsers";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }

    }
}
