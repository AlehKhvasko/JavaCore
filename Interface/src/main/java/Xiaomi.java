public class Xiaomi implements Callable{
    @Override
    public void call(String phone) {
        System.out.println("Xiaomi " + phone);
    }
}
