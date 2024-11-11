package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Михаил", "Лермонтов", (byte) 26);
        userService.saveUser("Лев", "Толстой", (byte) 82);
        userService.saveUser("Николай", "Гоголь", (byte) 42);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
