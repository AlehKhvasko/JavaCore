package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        users.add(user);
        rewriteUsers(users);
    }

    @Override
    public Optional<User> getUser(int id) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(int id, User user) {

        getUser(id).ifPresent(user1 -> {
            user1.setName(user.getName());
            user1.setLastName(user.getLastName());

            List<User> users = getAllUsers();

            int index = users.indexOf(user1);
            users.set(index, user1);
            try {
                rewriteUsers(users);
                bw.flush();
            } catch (IOException e) {
                System.err.println("Can't update users");
            }
        });

/*        Optional<User> updateUserOptional = getUser(id);
        if (getUser(id).isPresent()) {
            User unwrappedUser = getUser(id).get();

            unwrappedUser.setName(user.getName());
            unwrappedUser.setLastName(user.getLastName());

            List<User> users = getAllUsers();

            int index = users.indexOf(unwrappedUser);
            users.set(index, unwrappedUser);
            try {
                rewriteUsers(users);
                bw.flush();
            } catch (IOException e) {
                System.err.println("Can't update users");
            }
        }*/

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

