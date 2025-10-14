package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Aolina", "Pavlova", (byte) 35);
        userService.saveUser("Petr", "Ivanow", (byte) 28);
        userService.saveUser("Maria", "Zarubina", (byte) 23);
        userService.saveUser("Oleg", "Sokolow", (byte) 41);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);

            userService.cleanUsersTable();
            userService.dropUsersTable();
        }
    }
}
