package repository;


import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileUserRepositoryImplTest {
    public static String path = "users.txt";
    FileUserRepositoryImpl fileUserRepository = new FileUserRepositoryImpl(
            path,
            new BufferedReader(new FileReader(path)),
            new BufferedWriter(new FileWriter(path))
    );

    FileUserRepositoryImplTest() throws IOException {
    }


    @Test
    void createFile_shouldCreateFileIfNotExist(){
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
        String fileName = "users.txt";
        Path pathToStore = fileSystem.getPath("");

        fileUserRepository.createFile(fileName);

        assertTrue(Files.exists(pathToStore.resolve(fileName)));


    }


}