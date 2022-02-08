package service;

import model.User;
import repository.FileUserRepositoryImpl;
import exceptions.UserAlreadyExists;
import java.io.IOException;
import java.util.List;
    /**
     * 1) Создать модель данных (User)
     * 2) Создать класс FileWorker для работы с файлом
     * 3) Создать класс UserService содержащий бизнес логику
     * 1) User get(id) (если пользователя нет, то кидаем кастомные Exception UserNotFoundException)
     * 2) void write(User user)
     * 3) boolean update(User user) - возвращает фолс если нет пользователя
     * 4) List<User> getAll()
     * 5) boolean delete(int id)
     */
        public class UserService {
            private final FileUserRepositoryImpl userRepository;

            public UserService(FileUserRepositoryImpl userRepository) throws IOException {
                this.userRepository = userRepository;
            }

            public void writeUser(User user) throws IOException, UserAlreadyExists {
                if (checkUserExist(user)) {
                    throw new UserAlreadyExists();
                } else {
                    userRepository.writeUser(user);
                }
            }

            private boolean checkUserExist(User user) throws IOException {
                List<User> users = userRepository.getAllUsers();
                for (User checkUser : users) {
                    if (checkUser.getId() == user.getId()) {
                        return true;
                    }
                }
                return false;
            }
        }
