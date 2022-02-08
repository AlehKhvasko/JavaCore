import exceptions.UserAlreadyExists;
import model.User;
import repository.FileUserRepositoryImpl;
import service.UserService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, UserAlreadyExists {
        UserService userService = new UserService(new FileUserRepositoryImpl());
        userService.writeUser(new User(6,"t","w"));
    }
}
