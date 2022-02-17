import exceptions.UserAlreadyExists;
import exceptions.UserNotFound;
import model.User;
import repository.FileUserRepositoryImpl;
import service.UserService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws UserNotFound, UserAlreadyExists {
        UserService userService = new UserService(new FileUserRepositoryImpl("users.txt"));
        userService.writeUser(new User(1,"Keith","Archibald"));
        userService.writeUser(new User(2,"Tom","Brown"));
        userService.writeUser(new User(3,"Nick","Jefferson"));
        userService.writeUser(new User(4,"Mack","Ronald"));
        User user = new User("Elvis", "presley");

        System.out.println(userService.getAllUsers());
        System.out.println(userService.getUserById(2));
        System.out.println(userService.updateUser(3,user));
        System.out.println(userService.getUserById(4));
        System.out.println(userService.deleteUser(3));
        System.out.println(userService.getUserById(3));

    }

}
