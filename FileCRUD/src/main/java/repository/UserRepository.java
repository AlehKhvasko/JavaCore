package repository;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void writeUser(User user);

    Optional<User> getUser(int id);

    void updateUser(int id, User user);

    void deleteUser(int id);

    List<User> getAllUsers();
}
