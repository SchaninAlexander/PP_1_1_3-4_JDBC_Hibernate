package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {


        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Винни", "Пух", (byte) 40);
        userService.saveUser("Крошка", "Енот", (byte) 44);
        userService.saveUser("Ослик", "Иа-Иа", (byte) 66);
        userService.saveUser("Ёжик", "в тумане", (byte) 73);



        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}



