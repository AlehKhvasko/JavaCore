import exceptions.UserAlreadyExists;
import model.User;
import repository.FileUserRepositoryImpl;
import service.UserService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, UserAlreadyExists {
        UserService userService = new UserService(new FileUserRepositoryImpl());

        //userService.writeUser(new User(2,"t","w"));
        System.out.println(userService.getUserById(2));
        System.out.println(userService.updateUser(2,"t2ttt","ww2ww"));
        System.out.println(userService.getUserById(2));

    }
}
