public class AndroidCallImpl implements Callable {
    @Override
    public void call(String phone) {
        System.out.printf("Android " + phone);
    }
}
