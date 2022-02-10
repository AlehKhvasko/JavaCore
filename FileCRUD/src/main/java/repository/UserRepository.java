package repository;

import model.User;

import java.util.List;

public interface UserRepository {

    void writeUser(User user);

    User getUser(int id);

    void updateUser(int id, User user);

    void deleteUser(int id);

    List<User> getAllUsers();
}
