package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        try {
            //check createUsersTable method
            userService.createUsersTable();
            userService.createUsersTable();
            //check saveUser method
            userService.saveUser("Billy", "Milligan", (byte) 5);
            userService.saveUser("Barbie", "Ken", (byte) 65);
            userService.saveUser("Lightning", "MCQueen", (byte) 57);
            userService.saveUser("IDK", "KMS", (byte) 12);
            //check getAllUsers method
            List<User> allUsers = userService.getAllUsers();
            for (User u : allUsers) {
                System.out.println(u.toString());
            }
            //check cleanUsersTable method
            userService.cleanUsersTable();
            allUsers = userService.getAllUsers();
            for (User u : allUsers) {
                System.out.println(u.toString());
            }
            //check dropUsersTable method
            userService.dropUsersTable();
            userService.dropUsersTable();

        } catch (Exception e) {
            System.out.println("Проризошла ошибка :(\n" + e.getMessage());
        }
    }
}

