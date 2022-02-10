package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepositoryImpl implements UserRepository {
    private final String path = "users.txt";

    @Override
    public void writeUser(User user) {
        BufferedWriter bw = null;
        try {
            List<User> users = getAllUsers();
            users.add(user);
            bw = new BufferedWriter(new FileWriter(path));
            for (User singleUser : users) {
                bw.write(singleUser.toString() + "\n");
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public User getUser(int id) {
        List<User> users = getAllUsers();
        for (User user:users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(int id, String name, String lastName){
        User user = getUser(id);
        user.setName(name);
        user.setLastName(lastName);
        setUser(user);
    }

    @Override
    public void deleteUser(int id){
        List<User> users = getAllUsers();
        int index = findIndex(getUser(id));
        users.remove(index);
        try {
            rewriteUsers(users);
        } catch (IOException e) {
            System.out.println("Can't overwrite users during deleting");
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

    private void rewriteUsers(List<User> users) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (User singleUser : users) {
                bw.write(singleUser.toString() + "\n");
            }
            bw.flush();
            bw.close();
    }

    private int findIndex(User user){
        List<User> users = getAllUsers();
        int index = 0;
        for (User singleUser: users) {
            if (singleUser.getId() == user.getId()){
                return index;
            }
            index++;
        }
        return -1;
    }

    private void setUser(User user){
        List<User> users = getAllUsers();
        int index = findIndex(user);
        users.set(index, user);
        try {
            rewriteUsers(users);
        } catch (IOException e) {
            System.out.println("Can't overwrite users");
        }
    }
}

