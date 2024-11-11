package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT," +
                "name VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "age TINYINT," +
                "PRIMARY KEY (id));";
        try (Connection conn = Util.getConnection()) {
            conn.createStatement().execute(create);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection conn = Util.getConnection()) {
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = Util.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = Util.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection conn = Util.getConnection()) {
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
