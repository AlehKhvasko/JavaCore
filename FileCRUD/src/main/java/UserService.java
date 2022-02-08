public class UserService {
    /**
     * 1) Создать модель данных (User)
     * 2) Создать класс FileWorker для работы с файлом
     * 3) Создать класс UserService содержащий бизнес логику
     * 1) User get(id) (если пользователя нет, то кидаем кастомные Exception UserNotFoundException)
     * 2) void write(User user)
     * 3) boolean update(User user) - возвращает фолс если нет пользователя
     * 4) List<User> getAll()
     * 5) boolean delete(int id)
     */
    public static void main(String[] args) {

        FileWorker fileWorker = new FileWorker();
        User firstUser = new User(1, "Antony", "Kalib");
        User secondUser = new User(2, "Ashley", "Rinalt");
        User thirdUser = new User(3, "Alex", "Zammans");
        //User fourthUser = new User(3, "Evan", "Laxy");
        fileWorker.writeUser(firstUser);
        fileWorker.writeUser(secondUser);
        fileWorker.writeUser(thirdUser);
        //fileWorker.writeUser(fourthUser);

        fileWorker.getAll();
        System.out.println(fileWorker.get(2));
        System.out.println(fileWorker.updateUser(2, "Carl", "Hopkins"));
    }
}
