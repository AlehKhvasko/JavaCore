public class Caller {
    private Callable callable;

    public Caller(Callable callable) {
        this.callable = callable;
    }

    public void call() {
        callable.call("8080");
    }

}
