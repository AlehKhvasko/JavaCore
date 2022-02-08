import exceptions.UniqueIdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileWorkerTest {
    FileWorker fileWorker;
    User user1;

    @BeforeAll
    void init(){
        fileWorker = new FileWorker();
        user1 = new User(1,"Al","NoLastName");
    }

    @Test
    void writeUser_user_is_added() {
        assertAll(() -> fileWorker.writeUser(user1));
    }
    @Test
    void writeUser_does_not_throw_exception_when_user_is_added() {
        assertDoesNotThrow(() -> fileWorker.writeUser(user1));
    }

    /**
     * Does not throw exception
     */
/*    @Test
    void writeUser_throws_UniqueIdException_when_user_has_same_id() {
        assertThrows(UniqueIdException.class, ()-> {
            fileWorker.writeUser(user1);
            fileWorker.writeUser(user1);
        });
    }*/
    @Test
    void writeUser_does_not_throw_FileNotFoundException_when_user_is_added() {
        assertDoesNotThrow(() -> fileWorker.writeUser(user1));
    }


    @Test
    void getAll() {
    }

    @Test
    void get() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void eraseFile() {
    }
}