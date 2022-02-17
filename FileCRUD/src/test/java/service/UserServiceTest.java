package service;

import exceptions.UserAlreadyExists;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import repository.FileUserRepositoryImpl;
import repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    public FileUserRepositoryImpl fileUserRepository;
    public UserService userService;

    @BeforeAll
    void beforeEach(){
        fileUserRepository = Mockito.mock(FileUserRepositoryImpl.class);
        userService = new UserService(fileUserRepository);
    }

    @Test
    void writeUser() throws UserAlreadyExists {
        User user = new User(1, "a", "b");

        Mockito.when(fileUserRepository
                .getUser(2))
                .thenReturn(Optional.empty());

        userService.writeUser(user);

        Mockito.verify(fileUserRepository,
                Mockito.times(1))
                .writeUser(user);
    }
}