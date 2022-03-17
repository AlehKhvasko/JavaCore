public class IPhoneCallImpl implements Callable {
    @Override
    public void call(String phone) {
        System.out.printf("IPhoneCallImpl " + phone);
    }
}
