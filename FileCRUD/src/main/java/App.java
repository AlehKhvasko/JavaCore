import model.User;
import repository.FileUserRepositoryImpl;
import service.UserService;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserService(new FileUserRepositoryImpl());

        userService.writeUser(new User(2,"t","w"));
        System.out.println(userService.getUserById(2));
        System.out.println(userService.updateUser(3,"t2ttt","ww2ww"));
        System.out.println(userService.getUserById(2));
        System.out.println(userService.deleteUser(2));

    }
}
