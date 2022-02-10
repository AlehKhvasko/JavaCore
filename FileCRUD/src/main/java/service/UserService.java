package service;

import exceptions.UserAlreadyExists;
import exceptions.UserNotFound;
import model.User;
import repository.FileUserRepositoryImpl;
import repository.UserRepository;

import java.util.List;


public class UserService {
    private UserRepository userRepository;

    public UserService(FileUserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void writeUser(User user){
        try {
            if (checkUserExist(user)) {
                throw new UserAlreadyExists();
            } else {
                userRepository.writeUser(user);
            }
        }catch (UserAlreadyExists e){
            System.err.println("User already exists. ");
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
            System.out.println("User not found.");
        }
        return null;
    }

    public boolean updateUser(int id, User user){
        if (checkUserExistById(id)){
            userRepository.updateUser(id, user);
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

    public List<User> showUsers(){
        List<User> users = userRepository.getAllUsers();
        if (users.size() != 0){
             return users;
        }
        System.err.println("No users. List is empty.");
        return null;
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
