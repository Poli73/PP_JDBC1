package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao user = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        user.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        user.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        user.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        user.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers() {
        user.getAllUsers();
        return List.of();
    }

    @Override
    public void cleanUsersTable() {
        user.cleanUsersTable();

    }
}



