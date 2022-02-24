package service;

import exceptions.UserAlreadyExists;
import exceptions.UserNotFound;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.FileUserRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Mockito.when(fileUserRepository.getUser(1))
                .thenReturn(Optional.of(createUser()));

        Exception exception = assertThrows(UserAlreadyExists.class, () -> {
            userService.writeUser(createUser());
        });
        assertNotNull(exception);
    }

    @Test
    void getUserById_shouldReturnUserIfExist() throws UserNotFound{
        //given
        User user = new User(1, "a", "b");

        //when
        when(fileUserRepository
                .getUser(user.getId()))
                .thenReturn(Optional.of(user));
        //then
        User userById = userService.getUserById(user.getId());
        assertEquals(user, userById);
    }

    @Test
    void getUserById_shouldThrowsExceptionWhenUserNotExist() {
        assertThrows(UserNotFound.class, ()-> userService.getUserById(2));
    }

    @Test
    void updateUser_ShouldUpdateIfUserExist() {
        //given
        User updatedUser = new User(1, "Elvis", "Second");
        //when
        when(fileUserRepository
                .getUser(1))
                .thenReturn(Optional.of(createUser()));
        //then
        userService.updateUser(1, updatedUser);

        verify(fileUserRepository, Mockito.times(1))
                .updateUser(1, updatedUser);
    }

    @Test
    void updateUser_shouldReturnFalseIfUserNotExist(){
        //when
        when(fileUserRepository
                .getUser(1))
                .thenReturn(Optional.of(createUser()));
        //then
       assertFalse( userService.updateUser(2, createUser()));
    }

    @Test
    void deleteUser_ShouldDeleteUserIfExist() {
        //when
        when(fileUserRepository
                .getUser(1))
                .thenReturn(Optional.of(createUser()));
        //then
        assertTrue(userService.deleteUser(1));
        verify(fileUserRepository, Mockito.times(1))
               .getUser(1);
    }

    @Test
    void deleteUser_shouldReturnFalseIfNoUserToDelete(){
        //then
       assertFalse( userService.updateUser(2, createUser()));
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers(){
        //given
        List<User> newUsers = List.of(
                new User(1, "Donald", "Tramp"),
                new User(2,"Paris", "Hilton"));
        //when
        when(fileUserRepository.getAllUsers()).thenReturn(newUsers);
        //then
        assertEquals(newUsers, userService.getAllUsers());
    }

    @Test
    void getAllUsers_shouldReturnNullIfNoUsers(){
        assertNull(userService.getAllUsers());
    }

    private User createUser(){
        return new User(1, "Elvis", "Presley");
    }


}

