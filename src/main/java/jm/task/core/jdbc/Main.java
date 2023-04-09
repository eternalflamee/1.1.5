package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        /*userService.saveUser("name1", "lastname1", (byte) 17);
        userService.saveUser("name2", "lastname2", (byte) 27);
        userService.saveUser("name3", "lastname3", (byte) 37);
        userService.saveUser("name4", "lastname4", (byte) 47);

        List<User> list = userService.getAllUsers();
        for (User u:
             list) {
            System.out.println(u.toString());
        }*/

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
