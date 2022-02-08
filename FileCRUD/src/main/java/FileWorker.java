import exceptions.UniqueIdException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    final public String path = "C:\\Users\\alehk\\IdeaProjects\\JavaCore\\FileCRUD\\src\\main\\users.txt";


    public void writeUser(User user) {
        List<User> users = getAll();
        try {
            for (User checkUser : users) {
                if (checkUser.getId() == user.getId()) {
                    throw new UniqueIdException("Provide unique id");
                }
            }
            users.add(user);
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (User singleUser : users) {
                bw.write(singleUser.toString());
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UniqueIdException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((s = br.readLine()) != null) {
                String[] data = s.split(";");
                for (int i = 0; i < data.length; i += 3) {
                    users.add(new User(
                            Integer.parseInt(data[i]),
                            data[i + 1],
                            data[i + 2]));
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + path);
        } catch (IOException e) {
            System.out.println("Cannot read " + path);
        }
        return users;
    }

    public User get(int id) {
        List<User> users = getAll();
        return  checkIfExists(users, id);
    }

    public boolean updateUser(int id, String name, String lastName) {
        List<User> users = getAll();
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

    private User checkIfExists(List<User> users,int id){
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

