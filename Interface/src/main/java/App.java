import java.util.Random;

public class App {
    public static void main(String[] args) {
        int rand = new Random().nextInt(4);

        AndroidCallImpl android = new AndroidCallImpl();
        IPhoneCallImpl iPhoneCall = new IPhoneCallImpl();

        Caller caller;

        if (rand == 1) {
            caller = new Caller(android);
        } else if (rand == 2){
            caller = new Caller(iPhoneCall);
        } else if (rand == 3){
            caller = new Caller(phone -> System.out.print("Landline " + phone));
        } else {
            caller = new Caller(new Xiaomi());
        }

        caller.call();
    }
}
