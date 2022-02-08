package repository;

import exceptions.UserAlreadyExists;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepositoryImpl {
    private final String path;
    public FileUserRepositoryImpl(){
        path = "users.txt";
    }


    public void writeUser(User user) {
        List<User> users = getAllUsers();
        try {
            for (User checkUser : users) {
                if (checkUser.getId() == user.getId()) {
                    throw new UserAlreadyExists();
                }
            }
            users.add(user);
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (User singleUser : users) {
                bw.write(singleUser.toString());
            }
            bw.flush();
            bw.close();
        } catch (IOException | UserAlreadyExists e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        BufferedReader br = null;
        try {
            String s;
            br = new BufferedReader(new FileReader(path));
            while ((s = br.readLine()) != null) {
                String[] data = s.split(";");
                for (int i = 0; i < data.length; i += 3) {
                    users.add(new User(
                            Integer.parseInt(data[i]),
                            data[i + 1],
                            data[i + 2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public User get(int id) {
        List<User> users = getAllUsers();
        return  checkIfExists(users, id);
    }

    public boolean updateUser(int id, String name, String lastName) {
        List<User> users = getAllUsers();
        if (checkIfExists(users,id) != null) {
            int usersIndex = users.indexOf(checkIfExists(users, id));
            users.get(usersIndex).setName(name);
            users.get(usersIndex).setLastName(lastName);
            eraseFile();
            for (User user : users) {
                writeUser(user);
            }
            return true;
        }else {
            return false;
        }
    }

    private User checkIfExists(List<User> users, int id){
        for (User user:users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }


    public void eraseFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

