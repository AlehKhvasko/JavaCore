package service;

import exceptions.UserAlreadyExists;
import exceptions.UserNotFound;
import model.User;
import repository.FileUserRepositoryImpl;
import java.io.IOException;
import java.util.List;


public class UserService {
    private final FileUserRepositoryImpl userRepository;

    public UserService(FileUserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void writeUser(User user) throws IOException, UserAlreadyExists {
        if (checkUserExist(user)) {
            throw new UserAlreadyExists();
        } else {
            userRepository.writeUser(user);
        }
    }

    public User getUserById(int id){
        try{
            if (checkUserExistById(id)){
                return userRepository.getUser(id);
            }else {
                throw new UserNotFound();
            }
        }
        catch (UserNotFound e){
            System.out.println("User doesn't exists.");
            return null;
        }
    }

    public boolean updateUser(int id, String name, String lastName){
        if (checkUserExistById(id)){
            userRepository.updateUser(id,name,lastName);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id){
        if (checkUserExistById(id)){
            userRepository.deleteUser(id);
            return true;
        }
        return false;
    }

    private boolean checkUserExist(User user) {
        List<User> users = userRepository.getAllUsers();
        for (User checkUser : users) {
            if (checkUser.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkUserExistById(int id) {
        List<User> users = userRepository.getAllUsers();
        for (User checkUser : users) {
            if (checkUser.getId() == id) {
                return true;
            }
        }
        return false;
    }


}
