package service;

import exceptions.UserAlreadyExists;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import repository.FileUserRepositoryImpl;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        class UserServiceTest {

            private UserService userService;
            private FileUserRepositoryImpl fileUserRepository;

            @BeforeEach
            void beforeEach() {
                fileUserRepository = Mockito.mock(FileUserRepositoryImpl.class);
                userService = new UserService(fileUserRepository);
            }
            @Test
            void writeUser_shouldWriteIfNotExist() throws UserAlreadyExists {
                User user = new User(2, "1", "1");

                Mockito.when(fileUserRepository.getUser(1))
                        .thenReturn(Optional.empty());

                userService.writeUser(user);

                Mockito.verify(fileUserRepository,
                        Mockito.times(1)).writeUser(user);
            }

            @Test
            void writeUser_shouldThrowExceptionIfExist() {
                User user = new User(2, "1", "1");

                Mockito.when(fileUserRepository.getUser(2))
                        .thenReturn(Optional.of(user));

                Exception exception = assertThrows(UserAlreadyExists.class, () -> {
                    userService.writeUser(user);
                });

                assertNotNull(exception);
            }

            @Test
            void writeUser_shouldThrowTest() {
                //given
                User user = new User(2, "1", "1");

                //when
                Mockito.when(fileUserRepository.getUser(2))
                        .thenThrow(UnknownError.class);

                Executable executable = ()->{
                    fileUserRepository.getUser(2);
                };

                //then
                assertThrows(UnknownError.class, executable);
            }
        }

