package repository;

import model.User;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class FileUserRepositoryImplTest {


    //FileUserRepositoryImpl fileUserRepository = new FileUserRepositoryImpl("users.txt");

    @Test
    void createFile_shouldCreateFileIfNotExist(){
        //given
        List<User> newUsers = new ArrayList<>(List.of(
                new User(1,"a", "b"),
                new User(2, "c", "d")
        ));
        //when
       // when(fileUserRepository.getAllUsers().)


    }


}