import exceptions.UserAlreadyExists;
import exceptions.UserNotFound;
import model.User;
import repository.FileUserRepositoryImpl;
import service.UserService;

import java.io.*;

public class App {
    public static void main(String[] args) throws UserNotFound, UserAlreadyExists, IOException {
        String path = "users.txt";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UserService userService = new UserService(
                new FileUserRepositoryImpl(new BufferedReader(new FileReader(file)),
                                            new BufferedWriter(new FileWriter(file))));

        userService.writeUser(new User(1,"Keith","Archibald"));
        System.out.println(userService.getAllUsers());
        userService.writeUser(new User(2,"Tom","Brown"));
        System.out.println(userService.getAllUsers());
        userService.writeUser(new User(3,"Nick","Jefferson"));
        userService.writeUser(new User(4,"Mack","Ronald"));
        //User user = new User("Elvis", "Presley");

        System.out.println(userService.getAllUsers());
        //System.out.println(userService.getUserById(4));
        //System.out.println(userService.updateUser(3,user));
        //System.out.println(userService.getUserById(4));
        //System.out.println(userService.deleteUser(3));
        //System.out.println(userService.getUserById(3));
    }

}
