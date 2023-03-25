package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser("name1", "lastname1", (byte) 17);
        userServiceImpl.saveUser("name2", "lastname2", (byte) 27);
        userServiceImpl.saveUser("name3", "lastname3", (byte) 37);
        userServiceImpl.saveUser("name4", "lastname4", (byte) 47);

        List<User> list = userServiceImpl.getAllUsers();
        for (User u:
             list) {
            System.out.println(u.toString());
        }

        userServiceImpl.cleanUsersTable();

        userServiceImpl.dropUsersTable();
    }
}
