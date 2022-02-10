package repository;

import model.User;

public interface UserRepository {
    void writeUser(User user);

    User getUser(int id);

    void updateUser(int id, String name, String lastName);


}
