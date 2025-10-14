package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {

        String sql =
                """
                        CREATE TABLE IF NOT EXISTS users (
                           name VARCHAR(50),
                           lastName VARCHAR(50),
                            age INT
                            )
                        """;


        try (Connection connection = Util.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы: " + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (name, lastname, age)";

        Connection connection = null;
        try {
            connection = Util.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                String s = "INSERT INTO users (name, lastname, age) VALUES '"
                        + name + "', '" + lastName + "', " + age + ")";
                statement.executeUpdate(sql);
                statement.executeUpdate("INSERT INTO logs VALUES ('User added')");
            }

            connection.commit();

        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении пользователя: " + e.getMessage());
            try {
                connection.rollback();
                System.out.println("Транзакция откатилась");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void removeUserById(long id) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = Util.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при получении пользователей: " + e.getMessage());
        }

        return users;
    }


    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        try (Connection connection = Util.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}




