package service;

import exceptions.UserAlreadyExists;
import exceptions.UserNotFound;
import model.User;
import repository.FileUserRepositoryImpl;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;


public class UserService {
    private final UserRepository userRepository;

    public UserService(FileUserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public void writeUser(User user) throws UserAlreadyExists {
        Optional<User> newUser = userRepository.getUser(user.getId());
        if (newUser.isPresent()) {
            throw new UserAlreadyExists();
        }
        userRepository.writeUser(user);
    }

    /***
     * should return user if exists by id or return null instead
     *
     * @param id user's id
     * @return object of user
     */
    public User getUserById(int id) throws UserNotFound {
        return userRepository
                .getUser(id)
                .orElseThrow(UserNotFound::new);

/*        if (user.isEmpty()) {
            throw new UserNotFound();
        }
        return user.get();*/
    }

    public boolean updateUser(int id, User user) {
        if (userRepository.getUser(id).isPresent()) {
            userRepository.updateUser(id, user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        if (userRepository.getUser(id).isPresent()) {
            userRepository.deleteUser(id);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        if (users.size() != 0) {
            return users;
        }
        System.err.println("No users. List is empty.");
        return null;
    }


}
