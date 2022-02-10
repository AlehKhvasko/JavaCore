package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepositoryImpl implements UserRepository {
    private BufferedWriter bw;
    private BufferedReader br;

    public FileUserRepositoryImpl(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            br = new BufferedReader(new FileReader(path));
            bw = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeUser(User user) {
        List<User> users = getAllUsers();
        //TODO figure out "user not found"
        users.add(user);
        rewriteUsers(users);
    }

    @Override
    public User getUser(int id) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = getUser(id);
        updateUser.setName(user.getName());
        updateUser.setLastName(user.getLastName());
        List<User> users = getAllUsers();

        int index = users.indexOf(user);
        users.set(index, user);

        try {
            rewriteUsers(users);
            bw.flush();
        } catch (IOException e) {
            System.err.println("Can't update users");
        }
    }

    @Override
    public void deleteUser(int id) {
        List<User> users = getAllUsers();
        User user = new User();
        user.setId(id);
        users.remove(user);
        rewriteUsers(users);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String s;

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
        }
        return users;
    }

    private void rewriteUsers(List<User> users) {
        try {
            for (User singleUser : users) {
                bw.write(singleUser.toString() + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

